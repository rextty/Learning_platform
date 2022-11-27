package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.learningplatform.Model.Entity.User;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class learning extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedPreferencesHelper preferencesHelper;

    private final int RC_SIGN_IN = 6;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesHelper = new SharedPreferencesHelper(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            preferencesHelper.saveString("email", account.getEmail());
            preferencesHelper.saveString("username", account.getDisplayName());
            preferencesHelper.saveString("userid", account.getId());

            startActivity(new Intent(this, HomeActivity.class));
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
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

            String studentCode = String.valueOf(new Random().nextInt(999999));

            User user = new User();
            user.setUsername(account.getDisplayName());
            user.setEmail(account.getEmail());
            user.setIdentity("student");
            user.setStudentCode(studentCode);

            mDatabase.child("user").child(account.getId()).setValue(user);

            preferencesHelper.saveString("email", account.getEmail());
            preferencesHelper.saveString("username", account.getDisplayName());
            preferencesHelper.saveString("userid", account.getId());
            preferencesHelper.saveString("identity", "student");
            preferencesHelper.saveString("studentCode", studentCode);

            startActivity(new Intent(this, HomeActivity.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }
}