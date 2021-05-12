package com.motivation.team3.motivateme.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;
import com.motivation.team3.motivateme.database.TaskDbHelper;
import com.motivation.team3.motivateme.other.AppConstant;
import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReceiver extends BroadcastReceiver
{
    public void onReceive(Context context, Intent intent)
    {

        TaskDbHelper db=new TaskDbHelper(context);
        String action = intent.getAction();
        if (AppConstant.YES_ACTION.equals(action))
        {
            Toast.makeText(context,"Next Furture", Toast.LENGTH_SHORT).show();
        }
        else  if (AppConstant.STOP_ACTION.equals(action)) {
            Toast.makeText(context, "Next Furture", Toast.LENGTH_SHORT).show();
        }

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

        ComponentName comp = new ComponentName(context.getPackageName(), AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}