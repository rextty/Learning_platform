package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup.LayoutParams;

import com.example.learningplatform.Model.Entity.Material.Question;
import com.example.learningplatform.Model.Entity.Record;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityExamBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class ExamActivity extends AppCompatActivity {

    private int index;
    private String subjectName;
    private String chapterName;
    private String subsectionName;
    private ArrayList<Question> questions;
    private ArrayList<Integer> answers;

    private ActivityExamBinding binding;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        index = 0;
        subjectName = intent.getStringExtra("subjectName");
        chapterName = intent.getStringExtra("chapterName");
        subsectionName = intent.getStringExtra("subsectionName");
        questions = (ArrayList<Question>) intent.getSerializableExtra("questions");
        answers = new ArrayList<>(Collections.nCopies(questions.size(), -1));
        preferencesHelper = new SharedPreferencesHelper(this);

        updateQuestion();

        binding.btnPresentation.setOnClickListener(view -> {
            if (index > 0) {
                index--;
            }

            if (index < questions.size()) {
                binding.btnDone.setVisibility(View.INVISIBLE);
            }

            updateQuestion();
        });

        binding.btnNext.setOnClickListener(view -> {
            if (index < questions.size() - 1)
                index++;

            if (index == questions.size() - 1)
                binding.btnDone.setVisibility(View.VISIBLE);

            updateQuestion();
        });

        binding.btnDone.setOnClickListener(view -> {
            Record record = new Record();
            record.setChapterName(chapterName);
            record.setSubjectName(subjectName);
            record.setSubsectionName(subsectionName);
            record.setUserAnswers(answers);
            record.setQuestions(questions);

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("record").child(preferencesHelper.readString("userid")).setValue(record);
        });

        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            answers.set(index, i);
        });
    }

    private void updateQuestion() {
        binding.radioGroup.removeAllViews();

        Question currentQuestion = questions.get(index);

        String title = index + 1 + ". " + currentQuestion.getQuestionName();
        binding.textViewQuestion.setText(title);

        int questionSize = currentQuestion.getOptions().size();
        for (int i = 0; i < questionSize; i++) {
            RadioButton temp = new RadioButton(this);
            temp.setId(i);
            temp.setText(currentQuestion.getOption(i));

            if (index < answers.size())
                if (answers.get(index) == i)
                    temp.setChecked(true);

            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(100, 0, 0, 0);
            temp.setLayoutParams(params);
            binding.radioGroup.addView(temp);
        }
    }
}