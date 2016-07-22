package com.example.manit.appwatthai.indexactivity.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.manit.appwatthai.R;
import com.example.manit.appwatthai.indexactivity.map.MapsActivity;

public class WatmaiyueynuiActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watmaiyueynui_layout);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton btn_location15 = (ImageButton) findViewById(R.id.btn_location15);

        WebView webView = (WebView) findViewById(R.id.webViewn15);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://watthaiapp.6te.net/watmaiyueynui.html");

        //Go to location pagemap
        btn_location15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching Screen
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
