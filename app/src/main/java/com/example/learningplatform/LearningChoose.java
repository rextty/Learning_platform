package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearningChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_choose);
    }

    public void learningplatform(View view) {
        Intent intent = new Intent(this, learningplatform.class);
        startActivity(intent);
    }

    public void Fight(View view) {
        Intent intent = new Intent(this, Fight.class);
        startActivity(intent);
    }

    public void System(View view) {
        Intent intent = new Intent(this, System.class);
        startActivity(intent);
    }
}