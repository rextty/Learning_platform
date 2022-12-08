package com.example.learningplatform.Model.Strategy;

import android.content.Context;

// Strategy interface
public interface LanguageStrategy {

    void switchLanguage(Context context);

    String getLanguage();
}
