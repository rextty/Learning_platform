package com.example.learningplatform.Service;

import android.util.Log;

import java.util.ArrayList;

public class English implements ISubject {

    private String subjectName;

    private ArrayList<ISubject> engs = new ArrayList<>();

    public English() {

    }

    public English(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public void add(ISubject subject) {
        engs.add(subject);
    }

    @Override
    public void remove(ISubject subject) {
        engs.remove(subject);
    }

    @Override
    public void show() {
        if (engs.isEmpty()) {
            Log.e("TAG", this.subjectName);
        }else {
            Log.e("TAG", this.subjectName);
            for (ISubject eng:engs) {
                eng.show();
            }
        }
    }
}