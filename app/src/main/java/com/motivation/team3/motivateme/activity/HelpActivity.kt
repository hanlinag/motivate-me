package com.motivation.team3.motivateme.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.motivation.team3.motivateme.R
import com.readystatesoftware.systembartint.SystemBarTintManager

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.help_layout)
        val toolbar = findViewById(R.id.help_appToolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        val tintManager = SystemBarTintManager(this)
        tintManager.isStatusBarTintEnabled = true
        tintManager.setStatusBarTintColor(Color.parseColor("#0288d1"))
        tintManager.setNavigationBarTintEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}