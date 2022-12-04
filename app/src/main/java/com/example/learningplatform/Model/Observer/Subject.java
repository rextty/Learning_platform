package com.example.learningplatform.Model.Observer;

public interface Subject {

    void add(Observer observer);
    void remove(Observer observer);
    void notifyUser();
}
