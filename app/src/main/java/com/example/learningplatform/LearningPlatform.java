package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.example.learningplatform.Model.FileViewHolder;
import com.example.learningplatform.databinding.ActivityLearningplatformBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LearningPlatform extends AppCompatActivity {

    private TreeViewAdapter treeViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLearningplatformBinding binding = ActivityLearningplatformBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView = binding.filesRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> new FileViewHolder(v);

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        TreeNode javaDirectory = new TreeNode("Java", R.layout.list_item_file);
        javaDirectory.addChild(new TreeNode("FileJava1.java", R.layout.list_item_file));
        javaDirectory.addChild(new TreeNode("FileJava2.java", R.layout.list_item_file));
        javaDirectory.addChild(new TreeNode("FileJava3.java", R.layout.list_item_file));

        TreeNode gradleDirectory = new TreeNode("Gradle", R.layout.list_item_file);
        gradleDirectory.addChild(new TreeNode("FileGradle1.gradle", R.layout.list_item_file));
        gradleDirectory.addChild(new TreeNode("FileGradle2.gradle", R.layout.list_item_file));
        gradleDirectory.addChild(new TreeNode("FileGradle3.gradle", R.layout.list_item_file));

        javaDirectory.addChild(gradleDirectory);

        TreeNode lowLevelRoot = new TreeNode("LowLevel", R.layout.list_item_file);

        TreeNode cDirectory = new TreeNode("C", R.layout.list_item_file);
        cDirectory.addChild(new TreeNode("FileC1.c", R.layout.list_item_file));
        cDirectory.addChild(new TreeNode("FileC2.c", R.layout.list_item_file));
        cDirectory.addChild(new TreeNode("FileC3.c", R.layout.list_item_file));

        TreeNode cppDirectory = new TreeNode("Cpp", R.layout.list_item_file);
        cppDirectory.addChild(new TreeNode("FileCpp1.cpp", R.layout.list_item_file));
        cppDirectory.addChild(new TreeNode("FileCpp2.cpp", R.layout.list_item_file));
        cppDirectory.addChild(new TreeNode("FileCpp3.cpp", R.layout.list_item_file));

        TreeNode goDirectory = new TreeNode("Go", R.layout.list_item_file);
        goDirectory.addChild(new TreeNode("FileGo1.go", R.layout.list_item_file));
        goDirectory.addChild(new TreeNode("FileGo2.go", R.layout.list_item_file));
        goDirectory.addChild(new TreeNode("FileGo3.go", R.layout.list_item_file));

        lowLevelRoot.addChild(cDirectory);
        lowLevelRoot.addChild(cppDirectory);
        lowLevelRoot.addChild(goDirectory);

        TreeNode cSharpDirectory = new TreeNode("C#", R.layout.list_item_file);
        cSharpDirectory.addChild(new TreeNode("FileCs1.cs", R.layout.list_item_file));
        cSharpDirectory.addChild(new TreeNode("FileCs2.cs", R.layout.list_item_file));
        cSharpDirectory.addChild(new TreeNode("FileCs3.cs", R.layout.list_item_file));

        TreeNode gitFolder = new TreeNode(".git", R.layout.list_item_file);

        List<TreeNode> fileRoots = new ArrayList<>();
        fileRoots.add(javaDirectory);
        fileRoots.add(lowLevelRoot);
        fileRoots.add(cSharpDirectory);
        fileRoots.add(gitFolder);

        treeViewAdapter.updateTreeNodes(fileRoots);
    }
}