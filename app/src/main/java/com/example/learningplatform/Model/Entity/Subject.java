package com.example.learningplatform.Model.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Subject {

    public String subjectName;

    public Subject() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }
}