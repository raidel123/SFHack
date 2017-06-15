package com.example.admin.sfhack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DocWebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doc_web);

        webView = (WebView)findViewById(R.id.docWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String url = "https://demo.docusign.net/signing/emails/v2-a913528fe56c4265bb3eff8ada1429fe7716a690028147d7971b564c428aeab66fbf6934c5974b0b9b0ff15c93c7a827";

        webView.loadUrl(url);


    }
}
