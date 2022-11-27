package com.example.learningplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityBindingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class BindingActivity extends AppCompatActivity {

    private ActivityBindingBinding binding;

    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBindingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesHelper = new SharedPreferencesHelper(this);

        binding.editStudentCode.setText(preferencesHelper.readString("studentCode"));
        binding.editParentCode.setText(preferencesHelper.readString("parentCode"));

        binding.btnConfirm.setOnClickListener( view -> {
            String parent_code = binding.editParentCode.getText().toString();
            String student_code = binding.editStudentCode.getText().toString();

            if (student_code.isEmpty() || parent_code.isEmpty()) {
                Toast.makeText(this, "請輸入Code", Toast.LENGTH_LONG).show();
                return;
            }

            String userid = preferencesHelper.readString("userid");
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("user").child(userid).child("parentCode").setValue(parent_code);
            preferencesHelper.saveString("parentCode", parent_code);

            Toast.makeText(this, "成功", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
        });
    }
}