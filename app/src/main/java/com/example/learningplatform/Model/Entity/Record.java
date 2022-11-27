package com.example.learningplatform.Model.Entity;

import com.example.learningplatform.Model.Entity.Material.Question;

import java.util.ArrayList;

public class Record {

    private String subjectName;
    private String chapterName;
    private String subsectionName;
    private ArrayList<Integer> userAnswers;
    private ArrayList<Question> questions;

    public Record() {}

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setUserAnswers(ArrayList<Integer> userAnswers) {
        this.userAnswers = userAnswers;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public ArrayList<Integer> getUserAnswers() {
        return userAnswers;
    }
}
