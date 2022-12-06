package com.example.learningplatform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.Model.Strategy.Chinese;
import com.example.learningplatform.Model.Strategy.English;
import com.example.learningplatform.Service.FirebaseService;
import com.example.learningplatform.Service.GoogleSignInService;
import com.example.learningplatform.Service.LanguageService;
import com.example.learningplatform.databinding.ActivitySystemBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;

public class System extends AppCompatActivity {

    private ActivitySystemBinding binding;
    private LanguageService languageService;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);

        binding = ActivitySystemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        languageService = new LanguageService(this);
        preferencesHelper = new SharedPreferencesHelper(this);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            binding.blackSwitch.setChecked(true);

        // TODO: Language and color should use values
        binding.blackSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        });

        binding.btnStudent.setOnClickListener(view -> {
            String userid = preferencesHelper.readString("userid");
            DatabaseReference mDatabase = FirebaseService.getDBRInstance();
            mDatabase.child("user").child(userid).child("identity").setValue("student");
            preferencesHelper.saveString("identity", "student");

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        });

        binding.btnParent.setOnClickListener(view -> {
            String userid = preferencesHelper.readString("userid");
            DatabaseReference mDatabase = FirebaseService.getDBRInstance();
            mDatabase.child("user").child(userid).child("identity").setValue("parent");
            preferencesHelper.saveString("identity", "parent");

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ParentHomeActivity.class));
        });

        binding.btnZhTw.setOnClickListener(view -> {
            languageService.setStrategy(new Chinese());
            languageService.switchLanguage();

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        });

        binding.btnUsEn.setOnClickListener(view -> {
            languageService.setStrategy(new English());
            languageService.switchLanguage();

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, HomeActivity.class));
        });

        binding.btnLogout.setOnClickListener(view -> {
            GoogleSignInOptions gso = GoogleSignInService.getGSOInstance();
            GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
            mGoogleSignInClient.signOut();
            startActivity(new Intent(this, learning.class));
        });
    }
}