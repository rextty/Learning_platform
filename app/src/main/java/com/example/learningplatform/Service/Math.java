package com.example.learningplatform.Service;

import android.util.Log;

import java.util.ArrayList;

public class Math implements ISubject {

    private String subjectName;

    private ArrayList<ISubject> maths = new ArrayList<>();

    public Math() {

    }

    public Math(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public void add(ISubject subject) {
        maths.add(subject);
    }

    @Override
    public void remove(ISubject subject) {
        maths.remove(subject);
    }

    @Override
    public void show() {
        if (maths.isEmpty()) {
            Log.e("TAG", this.subjectName);
        }else {
            Log.e("TAG", this.subjectName);
            for (ISubject eng:maths) {
                eng.show();
            }
        }
    }
}
