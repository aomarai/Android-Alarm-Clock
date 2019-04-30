package com.example.alarmclock;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;


public class AlarmReceiver extends BroadcastReceiver {

    private static int notifID = 0;
    Ringtone ringtone;
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

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Makes the intent with the content for the notification, which launches the app to the main screen
        Intent contentIntent = new Intent(context, MainScreen.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, notifID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Create actual notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Alarm!")
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //Send notification
        notificationManager.notify(notifID, builder.build());

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



        //Sends the actual notification
        /*
        ComponentName componentName = new ComponentName(context.getPackageName(), AlarmService.class.getName());
        startWakefulService(context, intent.setComponent(componentName));
        setResultCode(Activity.RESULT_OK);
        */

        notifID++;
    }
}
