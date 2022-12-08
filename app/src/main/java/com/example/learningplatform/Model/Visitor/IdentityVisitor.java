package com.example.learningplatform.Model.Visitor;

// Visitor interface
public interface IdentityVisitor {

    void visit(StudentHome home);
    void visit(ParentHome home);
}
