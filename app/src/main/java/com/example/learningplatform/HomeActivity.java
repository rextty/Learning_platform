package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void learningplatform(View view) {
        Intent intent = new Intent(this, LearningPlatform.class);
        startActivity(intent);
    }

    public void Fight(View view) {
        Intent intent = new Intent(this, Fight.class);
        startActivity(intent);
    }

    public void Parent(View view) {
        Intent intent = new Intent(this, Parent.class);
        startActivity(intent);
    }

    public void System(View view) {
        Intent intent = new Intent(this, System.class);
        startActivity(intent);
    }
}