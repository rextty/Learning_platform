package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.example.learningplatform.Model.Composite.DataStructure;
import com.example.learningplatform.Model.Composite.Subsection;
import com.example.learningplatform.Model.Composite.Folder;
import com.example.learningplatform.Model.POJO.Question;
import com.example.learningplatform.Model.Iterator.Iterator;
import com.example.learningplatform.Model.TreeView.FileViewHolder;
import com.example.learningplatform.databinding.ActivityLearningplatformBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearningPlatform extends AppCompatActivity {

    private DataStructure subjects = new Folder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);
        ActivityLearningplatformBinding binding = ActivityLearningplatformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initQuiz();

        RecyclerView recyclerView = binding.filesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> new FileViewHolder(v);
        TreeViewAdapter treeViewAdapter = new TreeViewAdapter(factory);
        treeViewAdapter.setTreeNodeClickListener((treeNode, view) -> {
            if (treeNode.getChildren().isEmpty()){
                Iterator subjectsIterator = subjects.getIterator();
                while (subjectsIterator.hasNext()) {
                    DataStructure subject = subjectsIterator.next();
                    Iterator subjectIterator = subject.getIterator();
                    while (subjectIterator.hasNext()) {
                        DataStructure chapter = subjectIterator.next();
                        Iterator chapterIterator = chapter.getIterator();
                        while (chapterIterator.hasNext()) {
                            DataStructure subsection = chapterIterator.next();
                            if (subsection.getName().equals(treeNode.getValue())) {
                                Intent intent = new Intent(this, ExamActivity.class);
                                intent.putExtra("subjectName", subject.getName());
                                intent.putExtra("chapterName", chapter.getName());
                                intent.putExtra("subsectionName", subsection.getName());
                                intent.putExtra("questions", subsection.getQuestions());
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(treeViewAdapter);

        List<TreeNode> fileRoots = new ArrayList<>();

        Iterator subjectsIterator = subjects.getIterator();
        while (subjectsIterator.hasNext()) {
            DataStructure subject = subjectsIterator.next();
            TreeNode tempSubject = new TreeNode(subject.getName(), R.layout.list_item_file);
            Iterator subjectIterator = subject.getIterator();
            while (subjectIterator.hasNext()) {
                DataStructure chapter = subjectIterator.next();
                TreeNode tempChapters = new TreeNode(chapter.getName(), R.layout.list_item_file);
                Iterator chapterIterator = chapter.getIterator();
                while (chapterIterator.hasNext()) {
                    DataStructure subsection = chapterIterator.next();
                    TreeNode tempSubsections = new TreeNode(subsection.getName(), R.layout.list_item_file);
                    tempChapters.addChild(tempSubsections);
                }
                tempSubject.addChild(tempChapters);
            }
            fileRoots.add(tempSubject);
        }

        treeViewAdapter.updateTreeNodes(fileRoots);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void initQuiz() {
        // TODO: Get data from database
        DataStructure mathSubject = new Folder("Maths Chapters");
        DataStructure mathChapter1 = new Folder("Chapter 1 Basic Math");
        DataStructure mathChapter2 = new Folder("Chapter 2 Polynomials");

        Subsection mathChapter1Subsection1 = new Subsection("1.1 Addition and Subtraction");
        Subsection mathChapter1Subsection2 = new Subsection("1.2 Multiplication and Division");

        Subsection mathChapter2Subsection1 = new Subsection("2.1 Linear Equations In Two Variables");
        Subsection mathChapter2Subsection2 = new Subsection("2.2 Quadratic Equations");

        Question mathChapter1Subsection1Question1 = new Question("1 + 2 = ?");
        ArrayList<String> mathChapter1Subsection1Question1Option = new ArrayList<>(Arrays.asList(
                "1",
                "2",
                "3",
                "4"
        ));
        mathChapter1Subsection1Question1.setOptions(mathChapter1Subsection1Question1Option);
        mathChapter1Subsection1Question1.setAnswerIndex(2);
        mathChapter1Subsection1Question1.setPoint(20);

        Question mathChapter1Subsection1Question2 = new Question("2 + 3 = ?");
        ArrayList<String> mathChapter1Subsection1Question2Option = new ArrayList<>(Arrays.asList(
                "4",
                "5",
                "6",
                "7"
        ));
        mathChapter1Subsection1Question2.setOptions(mathChapter1Subsection1Question2Option);
        mathChapter1Subsection1Question2.setAnswerIndex(1);
        mathChapter1Subsection1Question2.setPoint(20);

        Question mathChapter1Subsection1Question3 = new Question("5 - 4 = ?");
        ArrayList<String> mathChapter1Subsection1Question3Option = new ArrayList<>(Arrays.asList(
                "2",
                "1",
                "3",
                "5"
        ));
        mathChapter1Subsection1Question3.setOptions(mathChapter1Subsection1Question3Option);
        mathChapter1Subsection1Question3.setAnswerIndex(1);
        mathChapter1Subsection1Question3.setPoint(20);

        Question mathChapter1Subsection1Question4 = new Question("5 - 6 = ?");
        ArrayList<String> mathChapter1Subsection1Question4Option = new ArrayList<>(Arrays.asList(
                "-1",
                "-2",
                "0",
                "-3"
        ));
        mathChapter1Subsection1Question4.setOptions(mathChapter1Subsection1Question4Option);
        mathChapter1Subsection1Question4.setAnswerIndex(0);
        mathChapter1Subsection1Question4.setPoint(20);

        Question mathChapter1Subsection1Question5 = new Question("10 - 1 = ?");
        ArrayList<String> mathChapter1Subsection1Question5Option = new ArrayList<>(Arrays.asList(
                "11",
                "8",
                "9",
                "10"
        ));
        mathChapter1Subsection1Question5.setOptions(mathChapter1Subsection1Question5Option);
        mathChapter1Subsection1Question5.setAnswerIndex(2);
        mathChapter1Subsection1Question5.setPoint(20);

        mathChapter1Subsection1.addQuestion(mathChapter1Subsection1Question1);
        mathChapter1Subsection1.addQuestion(mathChapter1Subsection1Question2);
        mathChapter1Subsection1.addQuestion(mathChapter1Subsection1Question3);
        mathChapter1Subsection1.addQuestion(mathChapter1Subsection1Question4);
        mathChapter1Subsection1.addQuestion(mathChapter1Subsection1Question5);

        mathChapter1.add(mathChapter1Subsection1);
        mathChapter1.add(mathChapter1Subsection2);

        mathChapter2.add(mathChapter2Subsection1);
        mathChapter2.add(mathChapter2Subsection2);

        mathSubject.add(mathChapter1);
        mathSubject.add(mathChapter2);

        DataStructure chineseSubject = new Folder("Chinese Chapters");
        DataStructure chineseChapter1 = new Folder("Chapter 1 Pronunciation");
        DataStructure chineseChapter2 = new Folder("Chapter 2 Word Recognition");

        Subsection chineseChapter1Subsection1 = new Subsection("1.1 逍遙遊 - Enjoyment in Untroubled Ease");
        Subsection chineseChapter1Subsection2 = new Subsection("1.2 齊物論 - The Adjustment of Controversies");

        Subsection chineseChapter2Subsection1 = new Subsection("2.1 養生主 - Nourishing the Lord of Life");
        Subsection chineseChapter2Subsection2 = new Subsection("2.2 人間世 - Man in the World, Associated with other Men");

        chineseChapter1.add(chineseChapter1Subsection1);
        chineseChapter1.add(chineseChapter1Subsection2);

        chineseChapter2.add(chineseChapter2Subsection1);
        chineseChapter2.add(chineseChapter2Subsection2);

        chineseSubject.add(chineseChapter1);
        chineseSubject.add(chineseChapter2);

        DataStructure englishSubject = new Folder("English Chapters");
        DataStructure englishChapter1 = new Folder("Chapter 1 The Enchanted Pool");
        DataStructure englishChapter2 = new Folder("Chapter 2 A Letter to God");

        Subsection englishChapter1Subsection1 = new Subsection("1.1 The Last Lesson");
        Subsection englishChapter1Subsection2 = new Subsection("1.2 Lost Spring");

        Subsection englishChapter2Subsection1 = new Subsection("2.1 Deep Water");
        Subsection englishChapter2Subsection2 = new Subsection("2.2 The Rattrap");

        englishChapter1.add(englishChapter1Subsection1);
        englishChapter1.add(englishChapter1Subsection2);

        englishChapter2.add(englishChapter2Subsection1);
        englishChapter2.add(englishChapter2Subsection2);

        englishSubject.add(englishChapter1);
        englishSubject.add(englishChapter2);

        subjects.add(mathSubject);
        subjects.add(chineseSubject);
        subjects.add(englishSubject);
    }
}