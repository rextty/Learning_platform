package com.example.learningplatform;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.learningplatform.Model.Observer.ExamCentre;
import com.example.learningplatform.Model.POJO.User;
import com.example.learningplatform.Model.SharedPreferencesHelper;
import com.example.learningplatform.Model.Visitor.Parent;
import com.example.learningplatform.Model.Visitor.ParentHome;
import com.example.learningplatform.Model.Visitor.Student;
import com.example.learningplatform.Model.Visitor.StudentHome;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class learning extends AppCompatActivity {

    private final int RC_SIGN_IN = 6;
    private ActivityMainBinding binding;
    private LanguageService languageService;
    private DatabaseReference mDatabase;
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

        mDatabase = FirebaseDatabase.getInstance().getReference();
        languageService = new LanguageService(this);
        preferencesHelper = new SharedPreferencesHelper(this);

        languageService.initLanguage();

        GoogleSignInOptions gso = GoogleSignInService.getGSOInstance();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        if (account != null) {
            Set<String> students = new HashSet<>();
            Set<String> bindingList = new HashSet<>();
            String identity = preferencesHelper.readString("identity");

            preferencesHelper.saveString("email", account.getEmail());
            preferencesHelper.saveString("username", account.getDisplayName());
            preferencesHelper.saveString("userid", account.getId());

            mDatabase.child("binding").get().addOnCompleteListener(task -> {
                if (!task.isSuccessful())
                    Log.e("firebase", "Error getting data", task.getException());
                else {
                    for (DataSnapshot child : task.getResult().getChildren()) {
                        String studentId = child.getKey();

                        child.getChildren().forEach((dataSnapshot -> {
                            String parentId = dataSnapshot.getValue(String.class);
                            if (identity.equals("parent")) {
                                if (account.getId().equals(parentId))
                                    bindingList.add(studentId);

                                if (account.getId().equals(parentId)) {
                                    students.add(studentId);
                                    ExamCentre.getInstance().add(new Parent(studentId));
//                                    FirebaseMessaging.getInstance().subscribeToTopic(studentId)
//                                            .addOnCompleteListener(m_task -> {
//                                                String msg = "Subscribed";
//                                                if (!m_task.isSuccessful()) {
//                                                    msg = "Subscribe failed";
//                                                }
//                                                Log.d(TAG, msg);
//                                            });
                                }
                            }else {
                                if (account.getId().equals(studentId))
                                    bindingList.add(parentId);
                            }
                        }));
                    }
                    preferencesHelper.saveStringSet("students", students);
                    preferencesHelper.saveStringSet("bindingList", bindingList);
                }
            });

            if (identity.equals("student"))
                new StudentHome(this).accept(new Student());
            else
                new ParentHome(this).accept(new Parent());

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
            String bindingCode = String.valueOf(new Random().nextInt(999999));

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        User user = new User();
                        user.setUsername(account.getDisplayName());
                        user.setEmail(account.getEmail());
                        user.setIdentity("student");
                        user.setBindingCode(bindingCode);
                        user.setNotification_token(token);

                        mDatabase.child("user").child(account.getId()).setValue(user);
                    });

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