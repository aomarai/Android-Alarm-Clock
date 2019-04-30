package com.example.alarmclock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.net.Uri;

import java.util.Calendar;

public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /*
        //Toast toast = Toast.makeText(context, "ALARM GOING OFF", 10);
        //toast.show();

        //Play an alert noise
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //Checks to see if there is an alarm ringtone,
        // otherwise uses default notification noise
        if (alarmUri == null)
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //Sends the actual notification
        ComponentName componentName = new ComponentName(context.getPackageName(), AlarmService.class.getName());
        startWakefulService(context, intent.setComponent(componentName));
        setResultCode(Activity.RESULT_OK);
        completeWakefulIntent(intent);
        */
    }
}
