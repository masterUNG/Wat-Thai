package com.example.manit.appwatthai.indexactivity.page;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;


import com.example.manit.appwatthai.R;

public class WebSearchMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_search_map);

        WebView webView = (WebView) findViewById(R.id.webViewmap);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://watthaiapp.6te.net/map.html");
    }
}
