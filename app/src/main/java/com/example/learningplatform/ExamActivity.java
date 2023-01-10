package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup.LayoutParams;
import android.widget.Toast;

import com.example.learningplatform.Model.POJO.Question;
import com.example.learningplatform.Model.POJO.Record;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityExamBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class ExamActivity extends AppCompatActivity {

    private int index;
    private String subjectName;
    private String chapterName;
    private String subsectionName;
    private ArrayList<Question> questions;
    private ArrayList<Integer> user_answers;

    private ActivityExamBinding binding;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);
        binding = ActivityExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();

        index = 0;
        subjectName = intent.getStringExtra("subjectName");
        chapterName = intent.getStringExtra("chapterName");
        subsectionName = intent.getStringExtra("subsectionName");
        questions = (ArrayList<Question>) intent.getSerializableExtra("questions");
        user_answers = new ArrayList<>(Collections.nCopies(questions.size(), -1));
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = dateFormat.format(Calendar.getInstance().getTime());

            Record record = new Record();
            record.setChapterName(chapterName);
            record.setSubjectName(subjectName);
            record.setSubsectionName(subsectionName);
            record.setUserAnswers(user_answers);
            record.setQuestions(questions);
            record.setDatetime(date);

            Intent result_intent = new Intent(this, ExamResultActivity.class);
            result_intent.putExtra("record", record);
            result_intent.putExtra("pre_page", "LearningPlatform");
            startActivity(result_intent);
        });

        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            user_answers.set(index, i);
        });
    }

    private void updateQuestion() {
        binding.radioGroup.removeAllViews();

        Question currentQuestion = questions.get(index);

        String title = index + 1 + ". " + currentQuestion.getTitle();
        binding.textViewQuestion.setText(title);

        int questionSize = currentQuestion.getOptions().size();
        for (int i = 0; i < questionSize; i++) {
            RadioButton temp = new RadioButton(this);
            temp.setId(i);
            temp.setText(currentQuestion.getOption(i));

            if (index < user_answers.size())
                if (user_answers.get(index) == i)
                    temp.setChecked(true);

            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(100, 0, 0, 0);
            temp.setLayoutParams(params);
            binding.radioGroup.addView(temp);
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Do not leave during the exam.", Toast.LENGTH_SHORT).show();
    }
}