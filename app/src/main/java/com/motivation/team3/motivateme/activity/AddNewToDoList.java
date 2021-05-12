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

public class AddNewToDoList extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener
{
    TaskDbHelper db;
    EditText title,body;
    Button time,date;
    String stitle,sbody,timeText,dateText;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calNow;
    Calendar calSet;
    int count;

    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newnote_layout);
        db=new TaskDbHelper(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calNow= Calendar.getInstance();
        calSet= (Calendar) calNow.clone();
        Calendar now = Calendar.getInstance();

        title = (EditText) findViewById(R.id.new_title);
        body=(EditText)findViewById(R.id.new_body) ;
        time=(Button)findViewById(R.id.new_time);
        date=(Button)findViewById(R.id.new_date);

        if(now.get(Calendar.HOUR_OF_DAY)<22)
        {
            calSet.set(Calendar.HOUR_OF_DAY,now.get(Calendar.HOUR_OF_DAY) );
            calSet.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
            Date dt = new Date(0,0,0, now.get(Calendar.HOUR_OF_DAY),now.get(Calendar.MINUTE), 0);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            timeText = sdf.format(dt);
            time.setText(timeText);

            calSet.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH));
            calSet.set(Calendar.MONTH, now.get(Calendar.MONTH));
            calSet.set(Calendar.YEAR, now.get(Calendar.YEAR));
            dateText= (now.get(Calendar.DAY_OF_MONTH))+ "/"+(now.get(Calendar.MONTH)+1)+"/"+now.get(Calendar.YEAR);
            date.setText(dateText);

        }
        else
        {
            calSet.set(Calendar.HOUR_OF_DAY,8 );
            calSet.set(Calendar.MINUTE, 0);
            Date dt = new Date(0,0,0, 8, 0, 0);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            timeText = sdf.format(dt);
            time.setText(timeText);

            calSet.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH)+1);
            calSet.set(Calendar.MONTH, now.get(Calendar.MONTH));
            calSet.set(Calendar.YEAR, now.get(Calendar.YEAR));
            dateText= (now.get(Calendar.DAY_OF_MONTH)+1)+ "/"+(now.get(Calendar.MONTH)+1)+"/"+now.get(Calendar.YEAR);
            date.setText(dateText);
        }

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openTimePickerDialog();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
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
                stitle=title.getText().toString();
                sbody=title.getText().toString();
                if(stitle.length()!=0)
                {
                    setAlarm(calSet);
                    db.insertData(count, stitle,sbody, timeText, dateText);
                }
               this.finish();
                return true;
        }
        finish();
        return true;
    }

    public void openTimePickerDialog()
    {
        Calendar now = Calendar.getInstance();
        TimePickerDialog timepickerdialog = TimePickerDialog.newInstance(
                AddNewToDoList.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE), false);
        timepickerdialog.setThemeDark(false);
        timepickerdialog.vibrate(true);
        timepickerdialog.dismissOnPause(true);
        timepickerdialog.enableSeconds(false);

        timepickerdialog.show(getFragmentManager(), "Timepickerdialog");
    }

    public void openDatePickerDialog()
    {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog= DatePickerDialog.newInstance(
                AddNewToDoList.this,
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
        dateText= dayOfMonth+ "/"+(monthOfYear+1)+"/"+year;
        date.setText(dateText);

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
        count=db.getCurrentId();
        if(count==-1)
            count=1;
        else count+=1;
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        intent.putExtra("Title",stitle);
        pendingIntent= PendingIntent.getBroadcast(getBaseContext(), count, intent, 0);
        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetcal.getTimeInMillis(), pendingIntent);
    }


    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second)
    {
        Date dt = new Date(0,0,0,hourOfDay,minute,second);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        timeText = sdf.format(dt);
        time.setText(timeText);

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
