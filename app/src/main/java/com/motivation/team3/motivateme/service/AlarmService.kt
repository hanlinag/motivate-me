package com.motivation.team3.motivateme.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.fragment.ToDoListFragment;
import com.motivation.team3.motivateme.other.AppConstant;
public class AlarmService extends IntentService
{
    private NotificationManager alarmNotificationManager;
    public AlarmService() {
        super("AlarmService");
    }

    public void onHandleIntent(Intent intent)
    {
        sendNotification("Check out your To Do List");
    }

    private void sendNotification(String msg) {
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ToDoListFragment.class), 0);

        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this)
                .setContentTitle("Motivate Me")
                .setSmallIcon(R.mipmap.logo)
                .setAutoCancel(true)
                .setSound(alert)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        Intent yesReceive = new Intent();
        yesReceive.setAction(AppConstant.STOP_ACTION);
        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(this, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        alamNotificationBuilder.addAction(R.drawable.ic_cancel, "Cancel", pendingIntentYes);

        Intent yesReceive2 = new Intent();
        yesReceive2.setAction(AppConstant.YES_ACTION);
        PendingIntent pendingIntentYes2 = PendingIntent.getBroadcast(this, 12345, yesReceive2, PendingIntent.FLAG_UPDATE_CURRENT);
        alamNotificationBuilder.addAction(R.drawable.ic_done_white, "Done", pendingIntentYes2);

        alamNotificationBuilder.setVibrate(new long[]{500, 500, 500, 500});
        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}