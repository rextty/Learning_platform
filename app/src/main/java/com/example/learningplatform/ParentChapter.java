package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ParentChapter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_chapter);
    }

    public void ChooseChapter(View view) {
        Intent intent = new Intent(this, ParentCheck.class);
        startActivity(intent);
    }

    public void Prepage(View view) {
        Intent intent = new Intent(this, Parent.class);
        startActivity(intent);

    }
}