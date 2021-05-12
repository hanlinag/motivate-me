package com.motivation.team3.motivateme.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.motivation.team3.motivateme.MainActivity;
import com.motivation.team3.motivateme.R;

public class SplashscreenActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.splashscreen_layout);

        new Handler().postDelayed(new Runnable() {

            public void run() {
                Intent in = new Intent(SplashscreenActivity.this, MainActivity.class);
                SplashscreenActivity.this.startActivity(in);
                SplashscreenActivity.this.finish();
            }
        },500);
    }
}
