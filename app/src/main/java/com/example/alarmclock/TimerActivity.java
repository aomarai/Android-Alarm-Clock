package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
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
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private Button timerButton;
    private EditText timerEditTextField;
    private String inputTime;
    private int hours, minutes;
    private long timeInMilli;

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


                    }catch(ParseException e){
                        e.printStackTrace();
                    }
                   // Toast.makeText(getApplicationContext(), "Hours: " + hours, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), "Minutes: " + minutes, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Calendar cal = Calendar.getInstance();



    }

    protected void setAlarm(Context context){
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        //Sets up new alarm with the time in milliseconds
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + timeInMilli, alarmIntent);
    }
}
