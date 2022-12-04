package com.example.learningplatform.Model.Observer;

import java.util.ArrayList;

public class ExamCentre implements Subject {

    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyUser() {
        observers.forEach(Observer::update);
    }
}
