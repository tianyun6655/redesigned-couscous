package com.example.tianyunchen.android_js;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        webView.setVerticalScrollbarOverlay(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
       // webView.getSettings().setDomStorageEnabled(true);
       // String url = "http://127.0.0.1/:63343/untitled/android_tester.html";
        //File file = new File(url);

       webView.loadUrl("file:///android_asset/android_tester.html");
       //webView.loadDataWithBaseURL(url,"html","text/html","UTF-8","");
        webView.addJavascriptInterface(new JsInterface(this),"AndroidWebView");

        webView.setWebChromeClient(new WebChromeClient());


    }
    private class JsInterface{
        private Context mContext;
        public JsInterface(Context context){
            this.mContext = context;

        }

        @JavascriptInterface
        //Js call androidWebView will call this function
        public void showInfoFromJs(String name){
            Toast.makeText(mContext,name,Toast.LENGTH_LONG).show();
            Log.d("MainActivity","From JS");
        }


    }

    public void sendInfoToJs(View view) {
        String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        //调用js中的函数：showInfoFromJava(msg)
        webView.loadUrl("javascript:showInfoFromJava('" + msg + "')");
    }}