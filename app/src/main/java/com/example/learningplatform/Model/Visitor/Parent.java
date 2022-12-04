package com.example.learningplatform.Model.Visitor;

import android.content.Context;

public class Parent implements Visitor {

    private Context context;

    public Parent(Context context) {
        this.context = context;
    }

    @Override
    public void visit(StudentHome studentHome) {

    }

    @Override
    public void visit(ParentHome parentHome) {

    }
}
