package com.example.learningplatform.Model.Visitor;

import android.content.Context;
import android.content.Intent;

import com.example.learningplatform.HomeActivity;

public class Student implements IdentityVisitor {

    private Context context;

    public Student(Context context) {
        this.context = context;
    }

    @Override
    public void visit(StudentHome home) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    public void visit(ParentHome home) {

    }
}