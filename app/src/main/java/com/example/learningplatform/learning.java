package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.learningplatform.Model.POJO.User;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.Service.FirebaseService;
import com.example.learningplatform.Service.GoogleSignInService;
import com.example.learningplatform.Service.LanguageService;
import com.example.learningplatform.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.Random;

public class learning extends AppCompatActivity {

    private final int RC_SIGN_IN = 6;
    private ActivityMainBinding binding;
    private LanguageService languageService;
    private SharedPreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            setTheme(R.style.Theme_Dark);
        else
            setTheme(R.style.Theme_Light);

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        languageService = new LanguageService(this);
        preferencesHelper = new SharedPreferencesHelper(this);

        languageService.initLanguage();

        GoogleSignInOptions gso = GoogleSignInService.getGSOInstance();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            preferencesHelper.saveString("email", account.getEmail());
            preferencesHelper.saveString("username", account.getDisplayName());
            preferencesHelper.saveString("userid", account.getId());

            String identity = preferencesHelper.readString("identity");
            if (identity.equals("student"))
                startActivity(new Intent(this, HomeActivity.class));
            else
                startActivity(new Intent(this, ParentHomeActivity.class));

            // TODO: rename activity and layout?
        }
        binding.signInButton.setSize(SignInButton.SIZE_STANDARD);
        binding.signInButton.setOnClickListener(view -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            DatabaseReference mDatabase = FirebaseService.getDBRInstance();

            String bindingCode = String.valueOf(new Random().nextInt(999999));

            User user = new User();
            user.setUsername(account.getDisplayName());
            user.setEmail(account.getEmail());
            user.setIdentity("student");
            user.setBindingCode(bindingCode);

            mDatabase.child("user").child(account.getId()).setValue(user);

            preferencesHelper.saveString("email", account.getEmail());
            preferencesHelper.saveString("username", account.getDisplayName());
            preferencesHelper.saveString("userid", account.getId());
            preferencesHelper.saveString("identity", "student");
            preferencesHelper.saveString("bindingCode", bindingCode);

            startActivity(new Intent(this, HomeActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
}