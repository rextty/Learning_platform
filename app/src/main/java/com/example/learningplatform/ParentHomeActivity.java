package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learningplatform.databinding.ActivityParentHomeBinding;

public class ParentHomeActivity extends AppCompatActivity {

    private ActivityParentHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.learn.setOnClickListener(view -> {

        });

        binding.graphical.setOnClickListener(view -> {

        });

        binding.btnBinding.setOnClickListener(view -> {
            startActivity(new Intent(this, BindingActivity.class));
        });
    }

    public void System(View view) {
        startActivity(new Intent(this, System.class));
    }
}