package com.example.learningplatform.Service;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSignInService {

    private static GoogleSignInService instance = new GoogleSignInService();
    private static GoogleSignInOptions gso;

    private GoogleSignInService() {
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                .requestEmail()
                .build();
    }

    public static GoogleSignInOptions getGSOInstance() {
        return gso;
    }

    public static GoogleSignInService getInstance() {
        return instance;
    }
}
