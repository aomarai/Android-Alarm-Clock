package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    private Button timerButton;
    private EditText timerEditTextField;
    private String inputTime;
    private String alarmMessage;
    private int hours, minutes;
    private long timeInMilli;
    EditText dialogMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timerButton =  findViewById(R.id.startTimerBtn);
        timerEditTextField = findViewById(R.id.TimerTextField);


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

                        dialogMessage = new EditText(getApplicationContext());

                        new AlertDialog.Builder(TimerActivity.this)
                                .setTitle("Alarm Message")
                                .setMessage("Type in a message for your alarm")
                                .setView(dialogMessage)
                                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int x) {
                                        alarmMessage = dialogMessage.getText().toString();
                                        AlarmReceiver.message = alarmMessage;
                                        setAlarm();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int x) {
                                    }
                                }).show();


                    }catch(ParseException e){
                    }

                }

            }
        });

    }

    private void setAlarm(){
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Takes current date in milliseconds and adds the user input time that was converted to milliseconds to the current calendar time
        alarmManager.set(AlarmManager.RTC_WAKEUP, new GregorianCalendar().getTimeInMillis()+timeInMilli,
                PendingIntent.getBroadcast(this, 1, alarmIntent,PendingIntent.FLAG_UPDATE_CURRENT));

        //Sets up new alarm with the time in milliseconds
        // alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + timeInMilli, alarmPendingIntent);
    }
}
