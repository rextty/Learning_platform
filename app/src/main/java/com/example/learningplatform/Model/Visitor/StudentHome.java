package com.example.learningplatform.Model.Visitor;

import android.content.Context;

import com.example.learningplatform.HomeActivity;

// Concrete component
public class StudentHome implements ActivityComponent {

    private Context context;

    public StudentHome(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Class<?> getActivity() {
        return HomeActivity.class;
    }

    @Override
    public void accept(IdentityVisitor visitor) {
        visitor.visit(this);
    }
}
