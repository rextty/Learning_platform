package com.example.learningplatform.Model.Composite;

import com.example.learningplatform.Model.Iterator.Iterator;
import com.example.learningplatform.Model.POJO.Question;

import java.util.ArrayList;

// Composite component & Iterator aggregate interface
public interface DataStructure {

    void add(DataStructure structure);
    void remove(DataStructure structure);
    DataStructure get(int index);
    ArrayList<DataStructure> getList();
    ArrayList<Question> getQuestions();
    void operation();

    void setName(String name);
    String getName();

    Iterator getIterator();
}
