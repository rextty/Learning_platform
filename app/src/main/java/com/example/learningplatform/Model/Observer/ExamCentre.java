package com.example.learningplatform.Model.Observer;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.learningplatform.Model.FCM.FCMEntity;
import com.example.learningplatform.Model.FCM.Notification;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class ExamCentre implements Observable {

    private ExamCentre() {}

    private static ExamCentre examCentre = new ExamCentre();
    private ArrayList<Observer> observers = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        FirebaseMessaging.getInstance().subscribeToTopic(observer.getTopic())
                .addOnCompleteListener(m_task -> {
                    String msg = "Subscribed";
                    if (!m_task.isSuccessful()) {
                        msg = "Subscribe failed";
                    }
                    Log.d(TAG, msg);
                });

        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(observer.getTopic())
                .addOnCompleteListener(m_task -> {
                    String msg = "Unsubscribed";
                    if (!m_task.isSuccessful()) {
                        msg = "Unsubscribe failed";
                    }
                    Log.d(TAG, msg);
                });

        observers.remove(observer);
    }

    @Override
    public ArrayList<Observer> getObservers() {
        return observers;
    }

    @Override
    public void notify(Notification notification) {
        FCMEntity fcmEntity = new FCMEntity(notification);
        fcmEntity.send();
    }

    public static ExamCentre getInstance() {
        return examCentre;
    }
}
