package com.example.learningplatform.Model.Visitor;

public interface ActivityComponent {

    void accept(IdentityVisitor visitor);
}
