package com.example.learningplatform.Model.Visitor;

import android.content.Context;

public class ParentHome implements Component {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
