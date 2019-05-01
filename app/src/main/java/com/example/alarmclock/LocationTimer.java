package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class LocationTimer extends TimerTask {


    int amount = 2;
    Location startingLoc;
    Location loc;
    Context c;

    LocationManager locationManager;
    LocationListener locationListener;

    public LocationTimer(Context context, int inputContent){

        amount = inputContent;
        c = context;
        startingLoc = LocationAlarm.currentLoc;
        loc = LocationAlarm.currentLoc;

    }

    @Override
    public void run() {

        loc = LocationAlarm.currentLoc;
        if(loc.getLatitude() == (startingLoc.getLatitude()) && loc.getLongitude() == (startingLoc.getLongitude())){
            Intent alarmIntent = new Intent(c.getApplicationContext(), AlarmReceiver.class);
            PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(c.getApplicationContext(), 1, alarmIntent, 0);
            AlarmManager alarmManager = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
            //Set the alarm
            AlarmReceiver.message = "Get up and move!";
            alarmManager.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + 1000, alarmPendingIntent);
            LocationAlarm.alarmActive = false;



        } else {
            //recursively create a new timer, logs location information to logcat under "warn" since there's not much going on there.
            Log.w("INFORMATION-DEBUG","\nOriginal Location = " + startingLoc.toString() + "\n Current Loc = " + loc.toString());
            Timer timer = new Timer();
            TimerTask recursiveTask = new LocationTimer(c,amount);
            timer.schedule(recursiveTask,amount);
            this.cancel();

        }

    }
}
