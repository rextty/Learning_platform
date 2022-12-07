package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.learningplatform.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.learn.setOnClickListener(view -> {
            startActivity(new Intent(this, LearningPlatform.class));
        });

        binding.fight.setOnClickListener(view -> {
            // TODO: Draw
        });

        binding.systemBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, System.class));
        });

        binding.btnBinding.setOnClickListener(view -> {
            startActivity(new Intent(this, BindingActivity.class));
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "if you want logout, go to setting.", Toast.LENGTH_SHORT).show();
    }
}