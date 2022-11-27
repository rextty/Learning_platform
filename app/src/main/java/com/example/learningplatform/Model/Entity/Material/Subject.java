package com.example.learningplatform.Model.Entity.Material;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class Subject {

    private String subjectName;
    private ArrayList<Chapter> chapters;

    public Subject() {
        this.chapters = new ArrayList<>();
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
        this.chapters = new ArrayList<>();
    }

    public Subject(String subjectName, ArrayList<Chapter> chapters) {
        this.subjectName = subjectName;
        this.chapters = chapters;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public ArrayList<Chapter> getChapters() {
        return chapters;
    }

    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    public Chapter getChapter(int index) {
        return chapters.get(index);
    }
}