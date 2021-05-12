package com.motivation.team3.motivateme.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.motivation.team3.motivateme.MainActivity
import com.motivation.team3.motivateme.R

class SplashscreenActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.splashscreen_layout)
        Handler().postDelayed({
            val `in` = Intent(this@SplashscreenActivity, MainActivity::class.java)
            this@SplashscreenActivity.startActivity(`in`)
            finish()
        }, 500)
    }
}