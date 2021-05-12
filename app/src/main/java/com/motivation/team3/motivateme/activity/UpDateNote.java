package com.motivation.team3.motivateme.activity;

import android.content.Intent;
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


public class UpDateNote extends AppCompatActivity
{
    EditText title,body;
    String id,stitle,sbody,time,date;
    TaskDbHelper db;
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_add_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.note_appToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        title=(EditText)findViewById(R.id.note_title);
        body=(EditText)findViewById(R.id.note_body);

        db=new TaskDbHelper(this);

        Intent intent=getIntent();
        id=(String)intent.getSerializableExtra("ID");
        stitle=(String)intent.getSerializableExtra("TITLE");
        sbody=(String)intent.getSerializableExtra("BODY");
        time= (String)intent.getSerializableExtra("TIME");
        date=(String)intent.getSerializableExtra("DATE");

        title.setText(stitle);
        int textlength=title.getText().length();
        body.setText(sbody);
        int bodylength=body.getText().length();

        if(textlength!=0)
        {
            title.setSelection(textlength,textlength);
        }
        else if(textlength==0 && bodylength!=0)
        {
            body.setSelection(bodylength,bodylength);
        }

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
                String stext=title.getText().toString();
                String sbody=body.getText().toString();
                if(title.getText().toString().length()!=0 || body.getText().toString().length()!=0)
                {
                    Calendar now = Calendar.getInstance();
                    Date dt = new Date(0, 0, 0, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                    String timeText = sdf.format(dt);
                    String dateText = String.valueOf(now.get(Calendar.DAY_OF_MONTH)) + "/" + String.valueOf(now.get(Calendar.MONTH) + 1) + "/" + String.valueOf(now.get(Calendar.YEAR));

                    db.updateNoteData(Integer.parseInt(id), title.getText().toString(), body.getText().toString(), timeText,dateText);
                }
                else if(stext.length()==0 || sbody.length()==0)
                {
                    db.deleteNoteData(Integer.parseInt(id));
                }
                finish();
                return true;
        }
        finish();
        return true;
    }
}
