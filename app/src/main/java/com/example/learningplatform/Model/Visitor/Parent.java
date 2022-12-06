package com.example.learningplatform.Model.Visitor;

import android.content.Context;

public class Parent implements IdentityVisitor {

    private Context context;

    public Parent(Context context) {
        this.context = context;
    }

    @Override
    public void visit(StudentHome parentHome) {

    }

    @Override
    public void visit(ParentHome home) {

    }
}
