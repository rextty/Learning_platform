package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FightExam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_exam);
    }


    public void ExamScore(View view) {
        Intent intent = new Intent(this, ExamScore.class);
        startActivity(intent);
    }
}