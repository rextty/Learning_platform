package com.example.learningplatform.Model.Visitor;

import android.content.Context;

import com.example.learningplatform.ParentHomeActivity;

public class ParentHome implements ActivityComponent {

    private Context context;

    public ParentHome(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public Class<?> getActivity() {
        return ParentHomeActivity.class;
    }

    @Override
    public void accept(IdentityVisitor visitor) {
        visitor.visit(this);
    }
}
