package com.motivation.team3.motivateme.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.database.TaskDbHelper;
import com.motivation.team3.motivateme.service.AlarmReceiver;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateToDoList extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
    TaskDbHelper db;
    String id,title,body,stime,sdate;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calNow;
    Calendar calSet;
    int count,scount;
    EditText txtbody,txttitle;
    Button time,date;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnote_layout);

        Toolbar toolbar=(Toolbar)findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        db=new TaskDbHelper(this);

        calNow= Calendar.getInstance();
        calSet= (Calendar) calNow.clone();

        id=(String)intent.getSerializableExtra("ID") ;
        scount= Integer.parseInt((String)intent.getSerializableExtra("COUNT"));
        title=(String)intent.getSerializableExtra("NAME");
        body=(String)intent.getSerializableExtra("BODY") ;
        stime=(String)intent.getSerializableExtra("TIME");
        sdate=(String)intent.getSerializableExtra("DATE");


        txtbody=(EditText)findViewById(R.id.new_body);
        txttitle=(EditText) findViewById(R.id.new_title);
        time=(Button)findViewById(R.id.new_time) ;
        date=(Button) findViewById(R.id.new_date);
        txttitle.setText(title);
        txtbody.setText(body);
        time.setText(stime);
        date.setText(sdate);

        int textlength=txttitle.getText().length();
        txttitle.setSelection(textlength,textlength);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePickerDialog();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog();
            }
        });

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
                EditText txttitle=(EditText) findViewById(R.id.new_title);
                String title=txttitle.getText().toString();
                EditText txtbody=(EditText)findViewById(R.id.new_body);
                String body=txtbody.getText().toString();

                setAlarm(calSet);
                db.updateData(id,scount,title,body,stime,sdate);
                finish();
                return true;
        }
        finish();
        return true;
    }


    public void openTimePickerDialog()
    {
        Calendar now = Calendar.getInstance();
        TimePickerDialog timepickerdialog = TimePickerDialog.newInstance(
                UpdateToDoList.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), false);
        timepickerdialog.setThemeDark(false);
        timepickerdialog.vibrate(true);
        timepickerdialog.dismissOnPause(true);
        timepickerdialog.enableSeconds(false);

        timepickerdialog.show(getFragmentManager(), "Timepickerdialog"); //show time picker dialog
    }

    public void openDatePickerDialog()
    {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog= DatePickerDialog.newInstance(
                UpdateToDoList.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setThemeDark(false);
        datePickerDialog.vibrate(true);
        datePickerDialog.dismissOnPause(true);
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth)
    {
        sdate= dayOfMonth+ "/"+(monthOfYear+1)+"/"+year;
        date.setText(sdate);

        calSet.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calSet.set(Calendar.MONTH, monthOfYear);
        calSet.set(Calendar.YEAR, year);

        if(calSet.compareTo(calNow)<=0)
        {
            calSet.add(Calendar.DATE,1);
        }
    }

    private void setAlarm(Calendar targetcal)
    {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        pendingIntent= PendingIntent.getBroadcast(getBaseContext(), scount, intent, 0);
        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetcal.getTimeInMillis(), pendingIntent);
    }

    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second)
    {
        Date dt = new Date(0,0,0,hourOfDay,minute,second);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        stime = sdf.format(dt);
        time.setText(stime);

        Log.i("Hour :" ,String.valueOf(hourOfDay));
        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if(calSet.compareTo(calNow) <= 0)
        {
            calSet.add(Calendar.DATE, 1);
        }
    }
}
