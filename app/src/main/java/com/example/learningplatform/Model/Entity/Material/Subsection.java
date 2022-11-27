package com.example.learningplatform.Model.Entity.Material;

import java.util.ArrayList;

public class Subsection {

    private String subsectionName;
    private ArrayList<Question> questions;

    public Subsection() {
        this.questions = new ArrayList<>();
    }

    public Subsection(String subsectionName) {
        this.subsectionName = subsectionName;
        this.questions = new ArrayList<>();
    }

    public Subsection(String subsectionName, ArrayList<Question> questions) {
        this.subsectionName = subsectionName;
        this.questions = questions;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }
}
