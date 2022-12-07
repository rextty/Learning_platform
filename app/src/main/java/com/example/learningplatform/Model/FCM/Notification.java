package com.example.learningplatform.Model.FCM;

import android.content.Context;

public class Notification {

    private Context context;
    private String topic;
    private String notification_title;
    private String notification_message;

    public Notification() {}

    public Notification(Context context) {
        this.context = context;
    }

    public Notification(String notification_title, String notification_message, String topic) {
        this.topic = topic;
        this.notification_title = notification_title;
        this.notification_message = notification_message;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public void setTopic(String topic) {
        this.topic = "/topics/" + topic;
    }

    public Context getContext() {
        return context;
    }

    public String getNotification_message() {
        return notification_message;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public String getTopic() {
        return topic;
    }
}
