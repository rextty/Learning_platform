package com.example.learningplatform.Model.Entity.Material;

import java.util.ArrayList;

public class Chapter {

    private String chapterName;
    private ArrayList<Subsection> subsections;

    public Chapter() {
        this.subsections = new ArrayList<>();
    }

    public Chapter(String chapterName) {
        this.chapterName = chapterName;
        this.subsections = new ArrayList<>();
    }

    public Chapter(String chapterName, ArrayList<Subsection> subsections) {
        this.chapterName = chapterName;
        this.subsections = subsections;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public void setSubsections(ArrayList<Subsection> subsections) {
        this.subsections = subsections;
    }

    public String getChapterName() {
        return chapterName;
    }

    public ArrayList<Subsection> getSubsections() {
        return subsections;
    }

    public void addSubsection(Subsection subsection) {
        subsections.add(subsection);
    }

    public Subsection getSubsection(int index) {
        return subsections.get(index);
    }
}
