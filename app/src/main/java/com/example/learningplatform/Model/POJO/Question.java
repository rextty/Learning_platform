package com.example.learningplatform.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    private String title;
    private int answerIndex;
    private ArrayList<String> options;
    private int point;

    public Question() {
        this.options = new ArrayList<>();
    }

    public Question(String questionName) {
        this.title = questionName;
        this.options = new ArrayList<>();
    }

    public Question(String questionName, int answerIndex, ArrayList<String> options) {
        this.title = questionName;
        this.answerIndex = answerIndex;
        this.options = options;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public String getTitle() {
        return title;
    }

    public String getOption(int index) {
        return options.get(index);
    }

    public void addOption(String option) {
        options.add(option);
    }

    public int getPoint() {
        return point;
    }
}
