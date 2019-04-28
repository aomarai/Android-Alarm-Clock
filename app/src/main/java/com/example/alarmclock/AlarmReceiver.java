package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"THE ALARM IS GOING OFF",Toast.LENGTH_LONG).show();

    }

    public static void setAlarm(Context c,View v, String time, Calendar calendar)
    {
        //sets an alarm based on the calendarview's information as well as the edittext.
        int hours;
        int minutes;
        AlarmManager am =(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        // this is not the static method, but rather converting from the gregoriancalendar class
        Calendar alarm = calendar.getInstance();

        String[] times = time.split(":");
        if(times.length > 1) {
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
        } else {
            //there was no ':', so we assume it's just minutes
            minutes = Integer.parseInt(times[0]);
            hours = 0;
        }
        alarm.add(Calendar.MINUTE,((60*hours) + (minutes)));

        Intent intent = new Intent(c,AddAlarmActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c,0,intent,0);
        am.set(AlarmManager.RTC_WAKEUP,alarm.getTimeInMillis(),pendingIntent);
        Toast.makeText(c.getApplicationContext(),"Alarm set to " + hours + ":" + minutes,Toast.LENGTH_LONG).show();

    }
}
