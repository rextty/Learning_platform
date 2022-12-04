package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learningplatform.databinding.ActivityParentHomeBinding;

public class ParentHomeActivity extends AppCompatActivity {

    private ActivityParentHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);

        binding = ActivityParentHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.learn.setOnClickListener(view -> {
            // TODO: learn
        });

        binding.graphical.setOnClickListener(view -> {
            // TODO: graphical
        });

        binding.btnBinding.setOnClickListener(view -> {
            Intent intent = new Intent(new Intent(this, BindingActivity.class));
            intent.putExtra("identity", "parent");
            startActivity(intent);
        });

        binding.systemBtn.setOnClickListener(view -> {
            startActivity(new Intent(this, System.class));
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "if you want logout, go to setting.", Toast.LENGTH_LONG).show();
    }
}