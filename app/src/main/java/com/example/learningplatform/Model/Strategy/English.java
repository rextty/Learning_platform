package com.example.learningplatform.Model.Strategy;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

// Change language method
public class English implements LanguageStrategy {

    // Change language method
    @Override
    public void switchLanguage(Context context) {
        Locale locale = new Locale("en");
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @Override
    public String getLanguage() {
        return "en";
    }
}
