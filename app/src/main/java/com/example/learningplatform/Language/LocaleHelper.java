package com.example.learningplatform.Language;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.example.learningplatform.Model.SharedPreferencesHelper;

import java.util.Locale;

public class LocaleHelper {

    private final Context context;
    private final SharedPreferencesHelper preferencesHelper;

    public LocaleHelper(Context context) {
        this.context = context;
        preferencesHelper = new SharedPreferencesHelper(context);
    }

    public void initLanguage(String key) {
        String language = preferencesHelper.readString(key);

        if (language != null)
            setAppLocale(context, language);
    }

    public void saveLanguagePreference(String key, String language) {
        preferencesHelper.saveString(key, language);
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
