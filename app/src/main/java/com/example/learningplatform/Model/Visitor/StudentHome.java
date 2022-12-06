package com.example.learningplatform.Model.Visitor;

public class StudentHome implements ActivityComponent {

    @Override
    public void accept(IdentityVisitor visitor) {
        visitor.visit(this);
    }
}
