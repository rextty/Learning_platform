package com.example.learningplatform.Model.FCM;

import static android.content.ContentValues.TAG;

import static com.example.learningplatform.BuildConfig.FIREBASE_SERVER_KEY;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FCMEntity {

    private final String uri = "https://fcm.googleapis.com/fcm/send";
    private final String contentType = "application/json";
    private final String key = "key=" + FIREBASE_SERVER_KEY;

    private Notification notification;

    public FCMEntity() {
        this.notification = new Notification();
    }

    public FCMEntity(Notification notification) {
        this.notification = notification;
    }

    public void send() {
        JSONObject jsonTopic = new JSONObject();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("title", notification.getNotification_title());
            jsonBody.put("message", notification.getNotification_message());

            jsonTopic.put("to", notification.getTopic());
            jsonTopic.put("data", jsonBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }

        sendNotification(jsonTopic);
    }

    private void sendNotification(JSONObject jNotification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri, jNotification,
                response -> {
                    // Nothing...
                },
                error -> {
                    // Handle error
                }){
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", key);
                params.put("Content-Type", contentType);
                return params;
            }
        };

        FCMService.getInstance(notification.getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Notification getNotification() {
        return notification;
    }
}
