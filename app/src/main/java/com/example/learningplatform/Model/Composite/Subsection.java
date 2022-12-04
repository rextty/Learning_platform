package com.example.learningplatform.Model.Composite;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.learningplatform.Model.Iterator.DataStructureIterator;
import com.example.learningplatform.Model.Iterator.Iterator;

import java.util.ArrayList;

public class Subsection implements DataStructure {

    private String name;
    private ArrayList<Question> questions = new ArrayList<>();

    public Subsection() {}

    public Subsection(String name) {
        this.name = name;
    }

    @Override
    public void add(DataStructure structure) {}
    @Override
    public void remove(DataStructure structure) {}
    @Override
    public DataStructure get(int index) {
        return null;
    }
    @Override
    public ArrayList<DataStructure> getList() {
        return null;
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public void operation() {
        Log.e(TAG, name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterator getIterator() {
        return new DataStructureIterator(this);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }
}
