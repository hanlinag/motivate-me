package com.motivation.team3.motivateme.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.motivation.team3.motivateme.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class HelpActivity extends AppCompatActivity
{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.help_appToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);

        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(Color.parseColor("#0288d1"));
        tintManager.setNavigationBarTintEnabled(true);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        finish();
        return true;
    }
}
