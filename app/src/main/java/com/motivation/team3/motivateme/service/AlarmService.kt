package com.motivation.team3.motivateme.service

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.fragment.ToDoListFragment
import com.motivation.team3.motivateme.other.AppConstant

class AlarmService : IntentService("AlarmService") {
    private var alarmNotificationManager: NotificationManager? = null
    public override fun onHandleIntent(intent: Intent?) {
        sendNotification("Check out your To Do List")
    }

    private fun sendNotification(msg: String) {
        alarmNotificationManager = this
            .getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val contentIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, ToDoListFragment::class.java), 0
        )
        val alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val alamNotificationBuilder = NotificationCompat.Builder(
            this
        )
            .setContentTitle("Motivate Me")
            .setSmallIcon(R.mipmap.logo)
            .setAutoCancel(true)
            .setSound(alert)
            .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
            .setContentText(msg)
        val yesReceive = Intent()
        yesReceive.action = AppConstant.STOP_ACTION
        val pendingIntentYes =
            PendingIntent.getBroadcast(this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT)
        alamNotificationBuilder.addAction(R.drawable.ic_cancel, "Cancel", pendingIntentYes)
        val yesReceive2 = Intent()
        yesReceive2.action = AppConstant.YES_ACTION
        val pendingIntentYes2 =
            PendingIntent.getBroadcast(this, 12345, yesReceive2, PendingIntent.FLAG_UPDATE_CURRENT)
        alamNotificationBuilder.addAction(R.drawable.ic_done_white, "Done", pendingIntentYes2)
        alamNotificationBuilder.setVibrate(longArrayOf(500, 500, 500, 500))
        alamNotificationBuilder.setContentIntent(contentIntent)
        alarmNotificationManager!!.notify(1, alamNotificationBuilder.build())
        Log.d("AlarmService", "Notification sent.")
    }
}