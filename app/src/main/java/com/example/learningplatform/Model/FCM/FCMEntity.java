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
    private Context context;

    private final String uri = "https://fcm.googleapis.com/fcm/send";
    private final String contentType = "application/json";
    private final String key = "key=" + FIREBASE_SERVER_KEY;

    private String notification_title;
    private String notification_message;
    private String topic;

    public FCMEntity() {}

    public FCMEntity(Context context) {
        this.context = context;
    }

    public FCMEntity(Context context, String notification_title, String notification_message, String topic) {
        this.context = context;
        this.notification_title = notification_title;
        this.notification_message = notification_message;
        this.topic = topic;
    }

    public void send() {
        JSONObject jsonTopic = new JSONObject();
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("title", notification_title);
            jsonBody.put("message", notification_message);

            jsonTopic.put("to", topic);
            jsonTopic.put("data", jsonBody);
        } catch (JSONException e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }

        sendNotification(jsonTopic);
    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri, notification,
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

        FCMService.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setTopic(String topic) {
        this.topic = "/topics/" + topic;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public void setNotification_message(String notification_message) {
        this.notification_message = notification_message;
    }
}
