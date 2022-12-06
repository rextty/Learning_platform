package com.example.learningplatform.Model.POJO;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String username;
    private String email;
    private String identity;
    private String bindingCode;
    private String notification_token;

    public User() {}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setBindingCode(String bindingCode) {
        this.bindingCode = bindingCode;
    }

    public void setNotification_token(String notification_token) {
        this.notification_token = notification_token;
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

    public String getBindingCode() {
        return bindingCode;
    }

    public String getNotification_token() {
        return notification_token;
    }
}