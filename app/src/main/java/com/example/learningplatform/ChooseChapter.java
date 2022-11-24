package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseChapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_chapter);
    }

    public void Prepage(View view) {
        Intent intent = new Intent(this, learningplatform.class);
        startActivity(intent);
    }
    public void Exam(View view) {
        Intent intent = new Intent(this, ChineseExam.class);
        startActivity(intent);
    }
}