package com.example.teste;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebView;

public class cam extends Activity{
    public void teste(){
    WebView web = (WebView) findViewById(R.id.web_cam);
    web.loadUrl("http://google.com");

    }
// public void Start(){
    // new Thread(new Runnable() {
    //  @Override
    //  public void run() {

    //      }
    // }).start();






}
