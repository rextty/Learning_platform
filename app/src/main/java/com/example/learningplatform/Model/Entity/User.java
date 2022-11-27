package com.example.learningplatform.Model.Entity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String identity;
    public String studentCode;
    public String parentCode;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getIdentity() {
        return identity;
    }

    public String getParentCode() {
        return parentCode;
    }

    public String getStudentCode() {
        return studentCode;
    }
}