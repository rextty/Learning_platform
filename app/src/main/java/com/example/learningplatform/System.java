package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class System extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
    }

    public void MainActivity(View view) {
        Intent intent = new Intent(this, learning.class);
        startActivity(intent);
    }

    public void LearningChoose(View view) {
        Intent intent = new Intent(this, LearningChoose.class);
        startActivity(intent);
    }
}