package com.example.learningplatform.Model.FCM;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class FCMService {

    private Context context;
    private RequestQueue requestQueue;
    private static FCMService instance;

    private FCMService(Context context) {
        this.context = context;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    public static FCMService getInstance(Context context) {
        if (instance == null)
            instance = new FCMService(context);

        return instance;
    }
}
