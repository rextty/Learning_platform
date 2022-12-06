package com.example.learningplatform.Service;

import android.content.Context;

import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.Model.Strategy.Chinese;
import com.example.learningplatform.Model.Strategy.English;
import com.example.learningplatform.Model.Strategy.LanguageStrategy;

public class LanguageService {

    private Context context;
    private LanguageStrategy strategy;
    private SharedPreferencesHelper preferencesHelper;
    private final String key = "lang";

    public LanguageService(Context context) {
        this.context = context;
        this.preferencesHelper = new SharedPreferencesHelper(context);
    }
    public LanguageService(LanguageStrategy strategy) {
        this.strategy = strategy;
    }
    public LanguageService(Context context, LanguageStrategy strategy) {
        this.context = context;
        this.strategy = strategy;
        this.preferencesHelper = new SharedPreferencesHelper(context);
    }

    public void switchLanguage() {
        if (strategy == null)
            strategy = new English();

        strategy.switchLanguage(context);
        preferencesHelper.saveString(key, strategy.getLanguage());
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setStrategy(LanguageStrategy strategy) {
        this.strategy = strategy;
    }

    public void initLanguage() {
        String language = preferencesHelper.readString(key);

        if (language == null)
            language = "en";

        if (language.equals("en"))
            setStrategy(new English());
        else if (language.equals("zh"))
            setStrategy(new Chinese());

        switchLanguage();
    }
}
