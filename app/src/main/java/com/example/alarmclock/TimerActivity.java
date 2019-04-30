package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    private AlarmManager alarmMgr;
    private PendingIntent alarmPendingIntent;
    private Button timerButton;
    private EditText timerEditTextField;
    private String inputTime;
    private int hours, minutes;
    private long timeInMilli;
    private Context context;
   // private AlarmReceiver alarmReceiver = new AlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timerButton = (Button) findViewById(R.id.startTimerBtn);
        timerEditTextField = (EditText) findViewById(R.id.TimerTextField);

        //Listens for button press
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checks to see if the time box is empty when button is pressed
                inputTime = timerEditTextField.getText().toString();
                if (inputTime.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please input a time.", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        //Parses the time input
                        DateFormat sdf = new SimpleDateFormat("hh:mm");
                        Date date = sdf.parse(inputTime);
                        hours = date.getHours();
                        minutes = date.getMinutes();
                        timeInMilli = (TimeUnit.HOURS.toMillis(hours)) + (TimeUnit.MINUTES.toMillis(minutes));

                        setAlarm();

                    }catch(ParseException e){
                    }

                }
            }
        });
        //Calendar cal = Calendar.getInstance();



    }

    protected void setAlarm(){
       Intent alarmIntent = new Intent(this, AlarmReceiver.class);
       AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
       alarmManager.set(AlarmManager.RTC_WAKEUP, new GregorianCalendar().getTimeInMillis()+timeInMilli,
               PendingIntent.getBroadcast(this, 1, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT));

        //Sets up new alarm with the time in milliseconds
       // alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + timeInMilli, alarmPendingIntent);
    }
}
