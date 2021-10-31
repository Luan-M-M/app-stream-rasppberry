package com.example.teste.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Utils {

    public void Snake(View v, String msg){
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }
}
