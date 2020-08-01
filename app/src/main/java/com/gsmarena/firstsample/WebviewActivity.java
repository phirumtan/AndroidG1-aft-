package com.gsmarena.firstsample;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebviewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = findViewById(R.id.webview);
        //mWebView.loadUrl("https://www.google.com/");
        // OR, you can also load from an HTML string:
        String summary = "<html><body>You scored <b>192</b> points.</body></html>";
        mWebView.loadData(summary, "text/html", null);
// ... although note that there are restrictions on what this HTML can do.
// See loadData(String, String, String) and loadDataWithBaseURL(String, String, String, String, String) for more info.
// Also see loadData(String, String, String) for information on encoding special
// characters.

    }
}
