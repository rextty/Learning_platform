package com.example.learningplatform.Model;

import com.example.learningplatform.R;

public class ExtensionTable {

    public static int getExtensionIcon(String extension) {
        switch (extension) {
            case ".c": return R.drawable.ic_c;
            case ".cpp": return R.drawable.ic_cpp;
            case ".cs": return R.drawable.ic_cs;
            case ".git": return R.drawable.ic_git;
            case ".go": return R.drawable.ic_go;
            case ".gradle": return R.drawable.ic_gradle;
            case ".java": return R.drawable.ic_java;
            default: return R.drawable.ic_file;
        }
    }
}
