package com.example.learningplatform.Model.Entity.Material;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    private String questionName;
    private int answerIndex;
    private ArrayList<String> options;

    public Question() {
        this.options = new ArrayList<>();
    }

    public Question(String questionName) {
        this.questionName = questionName;
        this.options = new ArrayList<>();
    }

    public Question(String questionName, int answerIndex, ArrayList<String> options) {
        this.questionName = questionName;
        this.answerIndex = answerIndex;
        this.options = options;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public String getQuestionName() {
        return questionName;
    }

    public String getOption(int index) {
        return options.get(index);
    }

    public void addOption(String option) {
        options.add(option);
    }
}
