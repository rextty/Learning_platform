package com.example.learningplatform.Model.POJO;

import java.io.Serializable;
import java.util.ArrayList;

public class Record implements Serializable {

    private String subjectName;
    private String chapterName;
    private String subsectionName;
    private ArrayList<Integer> userAnswers;
    private ArrayList<Question> questions;
    private int total_score;
    private int student_score;
    private String datetime;

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

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public void setStudent_score(int student_score) {
        this.student_score = student_score;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public int getTotal_score() {
        return total_score;
    }

    public int getStudent_score() {
        return student_score;
    }

    public String getDatetime() {
        return datetime;
    }
}
