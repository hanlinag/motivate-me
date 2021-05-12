package com.motivation.team3.motivateme.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.database.TaskDbHelper;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNote extends AppCompatActivity
{
    EditText text,body;
    String stext,sbody;
    TaskDbHelper db;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_add_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.note_appToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        text=(EditText)findViewById(R.id.note_title);
        body=(EditText)findViewById(R.id.note_body);

        db=new TaskDbHelper(this);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);

        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintColor(Color.parseColor("#0288d1"));
        tintManager.setNavigationBarTintEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add_task:
                stext=text.getText().toString();
                sbody=body.getText().toString();
                if(stext.length()!=0 || sbody.length()!=0)
                {
                    Calendar now = Calendar.getInstance();
                    Date dt = new Date(0,0,0, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String timeText = sdf.format(dt);
                    String dateText= (now.get(Calendar.DAY_OF_MONTH))+ "/"+(now.get(Calendar.MONTH)+1)+"/"+now.get(Calendar.YEAR);
                    db.noteInsertData(stext,sbody,timeText, dateText);
                }
                this.finish();
                return true;
        }
        finish();
        return true;
    }
}
