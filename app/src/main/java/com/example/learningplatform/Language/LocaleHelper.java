package com.example.learningplatform.Language;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleHelper {

    private final String PREFERENCE_FILE_KEY = "com.example.learningplatform.PREFERENCE_FILE_KEY";

    public void initLanguage(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String language = sharedPref.getString("lang", null);

        if (language != null)
            setAppLocale(context, language);
    }

    public void saveLanguagePreference(Context context, String language) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("lang", language);
        editor.apply();
    }

    public void setAppLocale(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
