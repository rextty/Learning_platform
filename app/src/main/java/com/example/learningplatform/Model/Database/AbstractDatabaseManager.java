package com.example.learningplatform.Model.Database;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class AbstractDatabaseManager {

    private DatabaseReference mDatabase;

    public final Object execute() {
        connectDatabase();
        makeQuery();
        executeQuery();
        disconnectDatabase();
        processResult();
        return null;
    }



    private void connectDatabase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public abstract void makeQuery();

    private void executeQuery() {

    }

    private void disconnectDatabase() {

    }

    public abstract void processResult();
}
