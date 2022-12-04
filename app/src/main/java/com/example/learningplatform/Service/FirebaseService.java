package com.example.learningplatform.Service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    private static FirebaseService instance = new FirebaseService();
    private static DatabaseReference mDatabase;

    private FirebaseService() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getDBRInstance() {
        return mDatabase;
    }

    public static FirebaseService getInstance() {
        return instance;
    }
}
