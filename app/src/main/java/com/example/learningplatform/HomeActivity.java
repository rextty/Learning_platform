package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.learningplatform.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.learn.setOnClickListener(view -> {
            startActivity(new Intent(this, LearningPlatform.class));
        });

        binding.fight.setOnClickListener(view -> {
            startActivity(new Intent(this, Fight.class));
        });

        binding.systemBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, System.class));
        });

        binding.btnBinding.setOnClickListener(view -> {
            startActivity(new Intent(this, BindingActivity.class));
        });
    }
}