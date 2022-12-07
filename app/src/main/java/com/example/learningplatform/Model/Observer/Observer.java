package com.example.learningplatform.Model.Observer;

import com.example.learningplatform.Model.FCM.Notification;

public interface Observer {

    void setTopic(String topic);
    String getTopic();
}
