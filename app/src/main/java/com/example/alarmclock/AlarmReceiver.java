package com.example.alarmclock;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver {

    private static int notifID = 0;
    public static String message;


    public AlarmReceiver(){

    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        createNotification(context, message);

        /*
        //Play an alert noise
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //Checks to see if there is an alarm ringtone,
        // otherwise uses default notification noise
        if (alarmUri == null)
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //Stops the ringtone after set amount of time in milliseconds
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ringtone.stop();
                }
            }, 2000);
         */

         //Increment the notification ID
         //notifID++;
    }

    public void createNotification(Context context, String message){
        PendingIntent notifIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainScreen.class), 0);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String CHANNEL_ID = "notifChannel1";
        CharSequence name = "notifChannel";
        String Description = "Notification Channel 1";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        //For APIS that are version 26 or newer
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Create the notification channel
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 100});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        //Create actual notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Alarm!")
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setChannelId(CHANNEL_ID);

        mBuilder.setContentIntent(notifIntent);
        notificationManager.notify(notifID, mBuilder.build());

    }
}
