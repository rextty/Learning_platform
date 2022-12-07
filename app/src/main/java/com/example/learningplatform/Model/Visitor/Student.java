package com.example.learningplatform.Model.Visitor;

import android.content.Context;
import android.content.Intent;

import com.example.learningplatform.HomeActivity;
import com.example.learningplatform.ParentHomeActivity;

// Concrete visitor
public class Student implements IdentityVisitor {

    @Override
    public void visit(StudentHome home) {
        Context context = home.getContext();
        context.startActivity(new Intent(context, home.getActivity()));
    }

    @Override
    public void visit(ParentHome home) {}
}