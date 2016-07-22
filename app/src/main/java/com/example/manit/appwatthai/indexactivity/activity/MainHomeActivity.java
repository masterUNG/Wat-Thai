package com.example.manit.appwatthai.indexactivity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.manit.appwatthai.R;
import com.example.manit.appwatthai.indexactivity.map.MapsActivity;
import com.example.manit.appwatthai.indexactivity.page.WebSearchMapActivity;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button btn_map1 = (Button) findViewById(R.id.btn_map1);
        Button btn_wat = (Button) findViewById(R.id.btn_wat);
        Button btn_webViewmap = (Button) findViewById(R.id.btn_webViewmap);


        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_map1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        btn_wat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
            }
        });

        btn_webViewmap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WebSearchMapActivity.class);
                startActivity(i);
            }
        });

    }
}
