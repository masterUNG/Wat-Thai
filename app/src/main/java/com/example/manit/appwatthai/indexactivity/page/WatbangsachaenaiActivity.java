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


public class WatbangsachaenaiActivity extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watbangsachaenai_layout);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        ImageButton btn_location8 = (ImageButton) findViewById(R.id.btn_location8);


        WebView webView = (WebView) findViewById(R.id.webViewn8);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://watthaiapp.6te.net/watbangsakaenai.html");

        //Go to location pagemap
        btn_location8.setOnClickListener(new View.OnClickListener() {

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
