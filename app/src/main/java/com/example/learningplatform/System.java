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
import android.widget.Toast;

import com.example.learningplatform.Language.LocaleHelper;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivitySystemBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class System extends AppCompatActivity {
    
    private ActivitySystemBinding binding;
    
    private LocaleHelper localeHelper;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySystemBinding.inflate(getLayoutInflater());
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        setContentView(binding.getRoot());

        localeHelper = new LocaleHelper(this);
        preferencesHelper = new SharedPreferencesHelper(this);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            binding.blackSwitch.setChecked(true);

        binding.blackSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        binding.btnStudent.setOnClickListener(view -> {
            String userid = preferencesHelper.readString("userid");
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("user").child(userid).child("identity").setValue("student");
            preferencesHelper.saveString("identity", "student");

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
        });

        binding.btnParent.setOnClickListener(view -> {
            String userid = preferencesHelper.readString("userid");
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("user").child(userid).child("identity").setValue("parent");
            preferencesHelper.saveString("identity", "parent");

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, ParentHomeActivity.class));
        });

        binding.btnZhTw.setOnClickListener(view -> {
            localeHelper.setAppLocale(view.getContext(), "zh");
            localeHelper.saveLanguagePreference("lang", "zh");

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
        });

        binding.btnZhTw.setOnClickListener(view -> {
            localeHelper.setAppLocale(view.getContext(), "en");
            localeHelper.saveLanguagePreference("lang", "en");

            Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, HomeActivity.class));
        });
    }
}