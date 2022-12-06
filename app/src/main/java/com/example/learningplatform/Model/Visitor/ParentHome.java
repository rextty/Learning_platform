package com.example.learningplatform.Model.Visitor;

public class ParentHome implements ActivityComponent {

    @Override
    public void accept(IdentityVisitor visitor) {
        visitor.visit(this);
    }
}
