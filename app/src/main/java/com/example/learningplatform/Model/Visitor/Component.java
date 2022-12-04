package com.example.learningplatform.Model.Visitor;

import android.content.Context;

public interface Component {

    void accept(Visitor visitor);
}
