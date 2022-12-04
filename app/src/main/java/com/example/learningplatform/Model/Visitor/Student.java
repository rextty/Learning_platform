package com.example.learningplatform.Model.Visitor;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.learningplatform.HomeActivity;

public class Student implements Visitor {

    private Context context;

    public Student(Context context) {
        this.context = context;
    }

    @Override
    public void visit(StudentHome studentHome) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    public void visit(ParentHome parentHome) {
        Toast.makeText(context, "You can't visit parent page.", Toast.LENGTH_SHORT).show();
    }
}