package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.example.learningplatform.Model.Composite.DataStructure;
import com.example.learningplatform.Model.Composite.Folder;
import com.example.learningplatform.Model.Iterator.Iterator;
import com.example.learningplatform.Model.Observer.ExamCentre;
import com.example.learningplatform.Model.POJO.Record;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.Model.TreeView.FileViewHolder;
import com.example.learningplatform.Model.Visitor.Parent;
import com.example.learningplatform.databinding.ActivityLearnedBinding;
import com.example.learningplatform.databinding.CardViewExamResultBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LearnedActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLearnedBinding binding = ActivityLearnedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mDatabase = FirebaseDatabase.getInstance().getReference();
        preferencesHelper = new SharedPreferencesHelper(this);

        // TODO: split different student result
        getRecordsList(records -> {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(30, 20, 30, 0);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            records.forEach(record -> {
                CardViewExamResultBinding resultBinding = CardViewExamResultBinding.inflate(getLayoutInflater());
                resultBinding.textViewSubsection.setText(record.getSubsectionName());
                resultBinding.textViewScore.setText(String.valueOf(record.getStudent_score()));
                resultBinding.textViewDatetime.setText(record.getDatetime());

                resultBinding.getRoot().setOnClickListener(v -> {
                    Intent intent = new Intent(this, ExamResultActivity.class);
                    intent.putExtra("record", record);
                    intent.putExtra("pre_page", "LearnedActivity");
                    startActivity(intent);
                });

                linearLayout.addView(resultBinding.getRoot());
                linearLayout.addView(new TextView(this));
            });

            binding.scrollView.addView(linearLayout);
        });
    }

    interface OnRecordListReceivedListener {
        void OnRecordListReceived(ArrayList<Record> records);
    }

    private void getRecordsList(OnRecordListReceivedListener listener) {
        ArrayList<Record> records = new ArrayList<>();
        Set<String> students = new HashSet<>();

        String identity = preferencesHelper.readString("identity");
        String userId = preferencesHelper.readString("userid");

        mDatabase.child("binding").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful())
                Log.e("firebase", "Error getting data", task.getException());
            else {
                for (DataSnapshot child : task.getResult().getChildren()) {
                    String studentId = child.getKey();

                    child.getChildren().forEach((dataSnapshot -> {
                        String parentId = dataSnapshot.getValue(String.class);
                        if (identity.equals("parent")) {
                            if (userId.equals(parentId))
                                students.add(studentId);
                        }
                    }));
                }

                mDatabase.child("record").get().addOnCompleteListener(task2 -> {
                    if (!task2.isSuccessful())
                        Log.e("firebase", "Error getting data", task2.getException());
                    else {
                        for (DataSnapshot child : task2.getResult().getChildren()) {
                            String childId = child.getKey();
                            child.getChildren().forEach(dataSnapshot -> {
                                students.forEach((studentId) -> {
                                    if (childId.equals(studentId)) {
                                        Record record = dataSnapshot.getValue(Record.class);
                                        records.add(record);
                                    }
                                    // TODO: Edit to Map
                                });
                            });
                        }
                        listener.OnRecordListReceived(records);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ParentHomeActivity.class));
    }
}