package com.example.learningplatform.Model.Observer;

import com.example.learningplatform.Model.FCM.Notification;

import java.util.ArrayList;

public interface Observable {

    void add(Observer observer);
    void remove(Observer observer);
    void notify(Notification notification);
    ArrayList<Observer> getObservers();
}
