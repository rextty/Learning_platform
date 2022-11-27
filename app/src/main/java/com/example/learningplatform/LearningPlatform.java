package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.example.learningplatform.Model.Entity.Material.Chapter;
import com.example.learningplatform.Model.Entity.Material.Question;
import com.example.learningplatform.Model.Entity.Material.Subject;
import com.example.learningplatform.Model.Entity.Material.Subsection;
import com.example.learningplatform.Model.TreeView.FileViewHolder;
import com.example.learningplatform.databinding.ActivityLearningplatformBinding;

import java.util.ArrayList;
import java.util.List;

public class LearningPlatform extends AppCompatActivity {

    private TreeViewAdapter treeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLearningplatformBinding binding = ActivityLearningplatformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Subject> subjects = new ArrayList<>();

        Subject mathSubject = new Subject("Class 10 Maths Chapters");

        Chapter mathChapter1 = new Chapter("Chapter 1 The Enchanted Pool");

        Subsection mathChapter1Subsection1 = new Subsection("1.1 Introduction to Real Numbers");
        Subsection mathChapter1Subsection2 = new Subsection("1.2 Euclid’s Division Lemma");
        Subsection mathChapter1Subsection3 = new Subsection("1.3 The Fundamental Theorem of Arithmetic");

        mathChapter1Subsection1.addQuestion(new Question("1 + 1 = ?"));
        mathChapter1Subsection1.addQuestion(new Question("1 + 2 = ?"));
        mathChapter1Subsection1.addQuestion(new Question("1 + 3 = ?"));
        mathChapter1Subsection1.addQuestion(new Question("1 + 4 = ?"));
        mathChapter1Subsection2.addQuestion(new Question("1 + 1 = ?"));
        mathChapter1Subsection3.addQuestion(new Question("1 + 1 = ?"));

        mathChapter1.addSubsection(mathChapter1Subsection1);
        mathChapter1.addSubsection(mathChapter1Subsection2);
        mathChapter1.addSubsection(mathChapter1Subsection3);

        Chapter mathChapter2 = new Chapter("Chapter 2 A Letter to God");

        Subsection mathChapter2Subsection1 = new Subsection("2.1 Introduction Polynomials");
        Subsection mathChapter2Subsection2 = new Subsection("2.2 Geometrical Meaning of the Zeroes of a Polynomial");
        Subsection mathChapter2Subsection3 = new Subsection("2.3 Relationship between Zeroes and Coefficients of a Polynomial");

        Question question1 = new Question("1 + 1 = ?");
        question1.setAnswerIndex(1);
        ArrayList<String> question1Options = new ArrayList<>();
        question1Options.add("2");
        question1Options.add("3");
        question1Options.add("4");
        question1Options.add("5");

        question1.setOptions(question1Options);

        mathChapter2Subsection1.addQuestion(question1);
        mathChapter2Subsection1.addQuestion(question1);
        mathChapter2Subsection1.addQuestion(question1);
        mathChapter2Subsection1.addQuestion(question1);
        mathChapter2Subsection2.addQuestion(question1);
        mathChapter2Subsection3.addQuestion(question1);

        mathChapter2.addSubsection(mathChapter2Subsection1);
        mathChapter2.addSubsection(mathChapter2Subsection2);
        mathChapter2.addSubsection(mathChapter2Subsection3);

        mathSubject.addChapter(mathChapter1);
        mathSubject.addChapter(mathChapter2);

        subjects.add(mathSubject);

        RecyclerView recyclerView = binding.filesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> new FileViewHolder(v);

        treeViewAdapter = new TreeViewAdapter(factory);
        treeViewAdapter.setTreeNodeClickListener((treeNode, view) -> {
            if (treeNode.getChildren().isEmpty()){
                subjects.forEach((subject -> {
                    subject.getChapters().forEach(chapter -> {
                        chapter.getSubsections().forEach(subsection -> {
                            if (subsection.getSubsectionName().equals(treeNode.getValue())) {
                                Intent intent = new Intent(this, ExamActivity.class);
                                intent.putExtra("subjectName", subject.getSubjectName());
                                intent.putExtra("chapterName", chapter.getChapterName());
                                intent.putExtra("subsectionName", subsection.getSubsectionName());
                                intent.putExtra("questions", subsection.getQuestions());
                                startActivity(intent);
                            }
                        });
                    });
                }));
            }
        });
        recyclerView.setAdapter(treeViewAdapter);

        List<TreeNode> fileRoots = new ArrayList<>();
        subjects.forEach((subject -> {
            TreeNode tempSubject = new TreeNode(subject.getSubjectName(), R.layout.list_item_file);
            subject.getChapters().forEach(chapter -> {
                TreeNode tempChapters = new TreeNode(chapter.getChapterName(), R.layout.list_item_file);
                chapter.getSubsections().forEach(subsection -> {
                    TreeNode tempSubsections = new TreeNode(subsection.getSubsectionName(), R.layout.list_item_file);
                    tempChapters.addChild(tempSubsections);
                });
                tempSubject.addChild(tempChapters);
            });
            fileRoots.add(tempSubject);
        }));

        treeViewAdapter.updateTreeNodes(fileRoots);
    }
}