package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.learningplatform.Language.LocaleHelper;
import com.example.learningplatform.databinding.ActivitySystemBinding;

import java.util.Locale;

public class System extends AppCompatActivity {

    private static final LocaleHelper localeHelper = new LocaleHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySystemBinding UI = ActivitySystemBinding.inflate(getLayoutInflater());
        setContentView(UI.getRoot());

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            UI.blackSwitch.setChecked(true);

        UI.blackSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        localeHelper.initLanguage(UI.getRoot().getContext());
    }

    public void MainActivity(View view) {
        Intent intent = new Intent(this, learning.class);
        startActivity(intent);
    }

    public void ChangeLangCN(View view) {
        localeHelper.setAppLocale(view.getContext(), "zh");
        localeHelper.saveLanguagePreference(view.getContext(), "zh");
    }

    public void ChangeLangEN(View view) {
        localeHelper.setAppLocale(view.getContext(), "en");
        localeHelper.saveLanguagePreference(view.getContext(), "en");
    }
}