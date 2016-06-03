package com.example.tianyunchen.js_with_native;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);

        webView.loadUrl("file:///android_asset/javascrip.html");
        webView.addJavascriptInterface(new JSinterface(this),"AndroidWebView");
        webView.setWebChromeClient(new WebChromeClient());

    }


    private class JSinterface{
        private Context context;
        public JSinterface(Context context){
            this.context = context;
        }
        @JavascriptInterface
        public void showInfoFromJs(String name)
        {
            Toast.makeText(context,name,Toast.LENGTH_LONG).show();

        }


    }


    public void sendInfoToJs(View view){
        String msg = ((EditText) findViewById(R.id.input_et)).getText().toString();
        webView.loadUrl("javascript:showInfoFromJava('"+msg+"')");
    }
}
