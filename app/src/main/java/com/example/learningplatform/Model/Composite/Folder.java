package com.example.learningplatform.Model.Composite;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.learningplatform.Model.Iterator.DataStructureIterator;
import com.example.learningplatform.Model.Iterator.Iterator;

import java.util.ArrayList;

public class Folder implements DataStructure {

    private String name;
    private ArrayList<DataStructure> subsections = new ArrayList<>();

    public Folder () {}

    public Folder (String name) {
        this.name = name;
    }

    @Override
    public void add(DataStructure structure) {
        subsections.add(structure);
    }

    @Override
    public void remove(DataStructure structure) {
        subsections.remove(structure);
    }

    @Override
    public DataStructure get(int index) {
        return subsections.get(index);
    }

    @Override
    public ArrayList<DataStructure> getList() {
        return subsections;
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return null;
    }

    @Override
    public void operation() {
        Log.e(TAG, name + ": ");
        subsections.forEach((subsection) -> {
            Log.e(TAG, subsection.getName());
        });
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterator getIterator() {
        return new DataStructureIterator(this);
    }
}
