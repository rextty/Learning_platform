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

import java.util.ArrayList;
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
        DataStructure mathSubject = new Folder("Class 10 Maths Chapters");
        DataStructure mathChapter1 = new Folder("Chapter 1 The Enchanted Pool");

        Subsection mathChapter1Subsection1 = new Subsection("1.1 Introduction to Real Numbers");
        Subsection mathChapter1Subsection2 = new Subsection("1.2 Euclid’s Division Lemma");
        Subsection mathChapter1Subsection3 = new Subsection("1.3 The Fundamental Theorem of Arithmetic");

        Question question = new Question();
        question.setTitle("1 + 2 = ?");
        ArrayList<String> questionOption = new ArrayList<>();
        questionOption.add("0");
        questionOption.add("1");
        questionOption.add("2");
        questionOption.add("3");
        question.setOptions(questionOption);
        question.setAnswerIndex(3);
        question.setPoint(20);

        mathChapter1Subsection1.addQuestion(question);
        mathChapter1Subsection1.addQuestion(question);
        mathChapter1Subsection1.addQuestion(question);
        mathChapter1Subsection1.addQuestion(question);
        mathChapter1Subsection1.addQuestion(question);

        mathChapter1Subsection2.addQuestion(question);
        mathChapter1Subsection2.addQuestion(question);
        mathChapter1Subsection2.addQuestion(question);
        mathChapter1Subsection2.addQuestion(question);
        mathChapter1Subsection2.addQuestion(question);

        mathChapter1Subsection3.addQuestion(question);
        mathChapter1Subsection3.addQuestion(question);
        mathChapter1Subsection3.addQuestion(question);
        mathChapter1Subsection3.addQuestion(question);
        mathChapter1Subsection3.addQuestion(question);

        mathChapter1.add(mathChapter1Subsection1);
        mathChapter1.add(mathChapter1Subsection2);
        mathChapter1.add(mathChapter1Subsection3);

        DataStructure mathChapter2 = new Folder("Chapter 2 A Letter to God");

        Subsection mathChapter2Subsection1 = new Subsection("2.1 Introduction Polynomials");
        Subsection mathChapter2Subsection2 = new Subsection("2.2 Geometrical Meaning of the Zeroes of a Polynomial");
        Subsection mathChapter2Subsection3 = new Subsection("2.3 Relationship between Zeroes and Coefficients of a Polynomial");

        mathChapter2Subsection1.addQuestion(question);
        mathChapter2Subsection2.addQuestion(question);
        mathChapter2Subsection3.addQuestion(question);

        mathChapter2.add(mathChapter2Subsection1);
        mathChapter2.add(mathChapter2Subsection2);
        mathChapter2.add(mathChapter2Subsection3);

        mathSubject.add(mathChapter1);
        mathSubject.add(mathChapter2);

        subjects.add(mathSubject);
    }
}