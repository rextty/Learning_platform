package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.learningplatform.Model.Composite.Question;
import com.example.learningplatform.Model.POJO.Record;
import com.example.learningplatform.databinding.ActivityExamResultBinding;
import com.example.learningplatform.databinding.CardViewQuestionAnswerBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class ExamResultActivity extends AppCompatActivity {

    private ActivityExamResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);
        binding = ActivityExamResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        Record record = (Record) intent.getSerializableExtra("record");

        binding.textViewSubject.setText(record.getSubjectName());
        binding.textViewChapter.setText(record.getChapterName());
        binding.textViewSubsection.setText(record.getSubsectionName());

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int questionIndex = 0; questionIndex < record.getQuestions().size(); questionIndex++) {
            CardViewQuestionAnswerBinding question_binding = CardViewQuestionAnswerBinding.inflate(getLayoutInflater());
            ArrayList<RadioButton> radioButtonOptions = new ArrayList<>(Arrays.asList(
                    question_binding.radioButtonOption1,
                    question_binding.radioButtonOption2,
                    question_binding.radioButtonOption3,
                    question_binding.radioButtonOption4
            ));

            Question question = record.getQuestions().get(questionIndex);
            String question_title = questionIndex + 1 + ". " + question.getQuestionName();
            question_binding.textViewQuestion.setText(question_title);
            int user_choice = record.getUserAnswers().get(questionIndex);
            for (int i = 0; i < radioButtonOptions.size(); i++) {
                RadioButton radioButton = radioButtonOptions.get(i);
                radioButton.setText(question.getOption(i));
                if (i == user_choice)
                    radioButton.setChecked(true);
            }

            if (user_choice == question.getAnswerIndex()) {
                question_binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.check));
                radioButtonOptions.get(user_choice).setBackground(ContextCompat.getDrawable(this, R.drawable.border_green));
            }
            else {
                question_binding.imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cross));
                radioButtonOptions.get(question.getAnswerIndex()).setBackground(ContextCompat.getDrawable(this, R.drawable.border_green));
                if (user_choice != -1)
                    radioButtonOptions.get(user_choice).setBackground(ContextCompat.getDrawable(this, R.drawable.border_red));
            }

            linearLayout.addView(question_binding.getRoot());
        }
        binding.scrollView.addView(linearLayout);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, LearningPlatform.class));
    }
}