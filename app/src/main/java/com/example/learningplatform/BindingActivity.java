package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.learningplatform.Model.POJO.User;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityBindingBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BindingActivity extends AppCompatActivity {

    private ActivityBindingBinding binding;
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

        preferencesHelper = new SharedPreferencesHelper(this);

        String identity = preferencesHelper.readString("identity");

        if (identity.equals("student")) {
            binding.editStudentCode.setEnabled(false);
            binding.editStudentCode.setText(preferencesHelper.readString("bindingCode"));
        } else {
            binding.editParentCode.setEnabled(false);
            binding.editParentCode.setText(preferencesHelper.readString("bindingCode"));
        }

        String userid = preferencesHelper.readString("userid");
        binding.btnConfirm.setOnClickListener( view -> {
            String objectCode = binding.editParentCode.getText().toString();
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("user").get().addOnCompleteListener(task -> {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else {
                    // TODO: Maybe need unbinding to apply observer?
                    for (DataSnapshot child: task.getResult().getChildren()) {
                        User user = child.getValue(User.class);
                        String bindingCode = user.getBindingCode();
                        if (bindingCode.equals(objectCode)) {
                            if (user.getIdentity().equals(identity)) {
                                Toast.makeText(this, "You can't binding the same identity user!", Toast.LENGTH_LONG).show();
                                return;
                            }

                            if (identity.equals("parent"))
                                mDatabase.child("binding").child(userid).setValue(child.getKey());
                            else
                                mDatabase.child("binding").child(child.getKey()).setValue(userid);

                            Toast.makeText(this, "Binding Success!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(this, HomeActivity.class));
                        }
                    }
                    Toast.makeText(this, "Wrong Binding Code!", Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}