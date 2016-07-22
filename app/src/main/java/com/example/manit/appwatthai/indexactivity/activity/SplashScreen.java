package com.example.manit.appwatthai.indexactivity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.manit.appwatthai.R;

public class SplashScreen extends Activity {
    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 2000L;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainStartActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}