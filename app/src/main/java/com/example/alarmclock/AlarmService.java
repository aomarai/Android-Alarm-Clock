package com.example.alarmclock;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmService extends IntentService {
    NotificationManager notificationManager;

    public AlarmService(){
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //sendNotification("Alarm going off!");
    }


    private void addNotification(String message){
        /*
        notificationManager = (NotificationManager) this.getSystemService
                (Context.NOTIFICATION_SERVICE);

        PendingIntent conInt = PendingIntent.getActivity(this, 0,
                new Intent(this, AddAlarmActivity.class), 0);

        //Creating the actual notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder
                (this).setContentTitle("Alarm").setStyle
                (new NotificationCompat.BigTextStyle().bigText(message)).setContentText(message);

        //Build and send the notification
        notificationBuilder.setContentIntent(conInt);
        notificationManager.notify(1, notificationBuilder.build());
        */
    }

}
