package com.example.learningplatform.Model.Visitor;

import android.content.Context;

public interface ActivityComponent {

    Class<?> getActivity();
    Context getContext();
    void accept(IdentityVisitor visitor);
}
