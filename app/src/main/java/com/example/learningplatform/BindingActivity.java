package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningplatform.Model.POJO.User;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityBindingBinding;
import com.example.learningplatform.databinding.CardViewBindingListBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BindingActivity extends AppCompatActivity {

    private ActivityBindingBinding binding;
    private DatabaseReference mDatabase;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);
        binding = ActivityBindingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        preferencesHelper = new SharedPreferencesHelper(this);

        String identity = preferencesHelper.readString("identity");
        String userId = preferencesHelper.readString("userid");
        Set<String> students = preferencesHelper.readStringSet("students");
        Set<String> bindingList = preferencesHelper.readStringSet("bindingList");

        if (identity.equals("student")) {
            binding.textViewScrollTitle.setText("Parents");
            binding.editStudentCode.setEnabled(false);
            binding.editStudentCode.setText(preferencesHelper.readString("bindingCode"));
        } else {
            binding.textViewScrollTitle.setText("Students");
            binding.editParentCode.setEnabled(false);
            binding.editParentCode.setText(preferencesHelper.readString("bindingCode"));
        }

        binding.btnConfirm.setOnClickListener(view -> {
            String objectCode;

            if (identity.equals("student"))
                objectCode = binding.editParentCode.getText().toString();
            else
                objectCode = binding.editStudentCode.getText().toString();

            mDatabase.child("user").get().addOnCompleteListener(task -> {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else {
                    for (DataSnapshot child : task.getResult().getChildren()) {
                        User user = child.getValue(User.class);
                        String bindingCode = user.getBindingCode();

                        if (bindingCode.equals(objectCode)) {
                            if (user.getIdentity().equals(identity)) {
                                Toast.makeText(this, "You can't binding the same identity user!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (identity.equals("parent"))
                                mDatabase.child("binding").child(child.getKey()).push().setValue(userId);
                            else
                                mDatabase.child("binding").child(userId).push().setValue(child.getKey());

                            mDatabase.child("binding").get().addOnCompleteListener(m_task -> {
                                if (!m_task.isSuccessful())
                                    Log.e("firebase", "Error getting data", m_task.getException());
                                else {
                                    for (DataSnapshot m_child : m_task.getResult().getChildren()) {
                                        String studentId = m_child.getKey();

                                        m_child.getChildren().forEach(dataSnapshot -> {
                                            String parentId = dataSnapshot.getValue(String.class);
                                            if (identity.equals("parent")) {
                                                if (userId.equals(parentId))
                                                    bindingList.add(studentId);

                                                if (userId.equals(parentId)) {
                                                    students.add(studentId);
                                                    FirebaseMessaging.getInstance().subscribeToTopic(studentId)
                                                            .addOnCompleteListener(n_task -> {
                                                                String msg = "Subscribed";
                                                                if (!n_task.isSuccessful()) {
                                                                    msg = "Subscribe failed";
                                                                }
                                                                Log.d(TAG, msg);
                                                            });
                                                }
                                            } else {
                                                if (userId.equals(studentId))
                                                    bindingList.add(parentId);
                                            }
                                        });
                                    }
                                    preferencesHelper.saveStringSet("students", students);
                                    preferencesHelper.saveStringSet("bindingList", bindingList);
                                }
                            });

                            Toast.makeText(this, "Binding Success!", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(this, "Wrong Binding Code!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        bindingList.forEach((m_userId) -> {
            mDatabase.child("user").child(m_userId).get().addOnCompleteListener(task -> {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else {
                    String key = task.getResult().getKey();
                    User user = task.getResult().getValue(User.class);

                    String specificId;
                    if (identity.equals("student"))
                        specificId = userId;
                    else
                        specificId = key;

                    CardViewBindingListBinding listBinding = CardViewBindingListBinding.inflate(getLayoutInflater());
                    listBinding.textViewName.setText(user.getUsername());
                    listBinding.buttonUnbinding.setOnClickListener(v -> {
                        mDatabase.child("binding").child(specificId).get().addOnCompleteListener(m_task -> {
                            if (!m_task.isSuccessful())
                                Log.e("firebase", "Error getting data", m_task.getException());
                            else {
                                m_task.getResult().getChildren().forEach(dataSnapshot -> {
                                    if (identity.equals("student")) {
                                        if (dataSnapshot.getValue().equals(key)) {
                                            dataSnapshot.getRef().removeValue();
                                            linearLayout.removeView(listBinding.getRoot());
                                            bindingList.remove(userId);
                                            preferencesHelper.saveStringSet("bindingList", bindingList);
                                            Toast.makeText(this, "Unbinding Success", Toast.LENGTH_SHORT).show();
                                        }
                                    }else {
                                        if (dataSnapshot.getValue().equals(userId)) {
                                            dataSnapshot.getRef().removeValue();
                                            linearLayout.removeView(listBinding.getRoot());
                                            students.remove(key);
                                            bindingList.remove(key);
                                            preferencesHelper.saveStringSet("bindingList", bindingList);
                                            Toast.makeText(this, "Unbinding Success", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                    });
                    linearLayout.addView(listBinding.getRoot());
                }
            });
        });
        binding.scrollView.addView(linearLayout);
    }
}