package com.example.learningplatform.Model.Visitor;

import android.content.Context;
import android.content.Intent;

import com.example.learningplatform.Model.FCM.FCMEntity;
import com.example.learningplatform.Model.FCM.Notification;
import com.example.learningplatform.Model.Observer.Observer;

// Concrete visitor and Concrete observer
public class Parent implements IdentityVisitor, Observer {

    private String topic;

    public Parent() {}

    public Parent(String topic) {
        this.topic = topic;
    }

    @Override
    public void visit(StudentHome parentHome) {}

    @Override
    public void visit(ParentHome home) {
        Context context = home.getContext();
        context.startActivity(new Intent(context, home.getActivity()));
    }

    @Override
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String getTopic() {
        return topic;
    }
}
