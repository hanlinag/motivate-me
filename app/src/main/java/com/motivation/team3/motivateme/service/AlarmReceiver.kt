package com.motivation.team3.motivateme.service

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.support.v4.content.WakefulBroadcastReceiver
import android.widget.Toast
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.motivation.team3.motivateme.other.AppConstant

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val db = TaskDbHelper(context)
        val action = intent.action
        if (AppConstant.YES_ACTION == action) {
            Toast.makeText(context, "Next Furture", Toast.LENGTH_SHORT).show()
        } else if (AppConstant.STOP_ACTION == action) {
            Toast.makeText(context, "Next Furture", Toast.LENGTH_SHORT).show()
        }
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)
        val comp = ComponentName(context.packageName, AlarmService::class.java.name)
        WakefulBroadcastReceiver.startWakefulService(context, intent.setComponent(comp))
        resultCode = Activity.RESULT_OK
    }
}