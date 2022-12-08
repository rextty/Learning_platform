package com.example.learningplatform.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesHelper {

    private final String PREFERENCE_FILE_KEY = "com.example.learningplatform.PREFERENCE_FILE_KEY";
    private final int MODE = Context.MODE_PRIVATE;

    private final SharedPreferences preferences;
    private final SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, MODE);
        editor = preferences.edit();
    }

    public String readString(String key) {
        return preferences.getString(key, null);
    }

    public Set<String> readStringSet(String key) {
        return preferences.getStringSet(key, null);
    }

    public void saveString(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void saveStringSet(String key, Set<String> set) {
        editor.putStringSet(key, set);
        editor.commit();
    }
}
