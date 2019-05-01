package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RecursiveAlarm extends AppCompatActivity {
    private Button recurButton;
    private TimePicker recurPicker;
    private String alarmMessage;
    private String inputTime;
    private int hours, minutes;
    private long timeInMilli;
    private CheckBox sundayBox;
    private CheckBox mondayBox;
    private CheckBox tuesdayBox;
    private CheckBox wednesBox;
    private CheckBox thursdayBox;
    private CheckBox fridayBox;
    private CheckBox saturdayBox;
    private EditText dialogMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursive_alarm);
        recurButton = findViewById(R.id.SingleSetAlarm);
        recurPicker = findViewById(R.id.SingleTimePicker);
        sundayBox = findViewById(R.id.SundayCheckBox);
        mondayBox = findViewById(R.id.MondayCheckBox);
        tuesdayBox = findViewById(R.id.TuesdayCheckBox);
        wednesBox = findViewById(R.id.WednesdayCheckBox);
        thursdayBox = findViewById(R.id.ThursdayCheckBox);
        fridayBox = findViewById(R.id.FridayCheckBox);
        saturdayBox = findViewById(R.id.SaturdayCheckBox);


        recurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //Parses the time input
                   hours = recurPicker.getHour();
                   minutes = recurPicker.getMinute();

                   timeInMilli = (TimeUnit.HOURS.toMillis(hours)) + (TimeUnit.MINUTES.toMillis(minutes));


                    dialogMessage = new EditText(getApplicationContext());

                    new AlertDialog.Builder(RecursiveAlarm.this)
                            .setTitle("Alarm Message")
                            .setMessage("Type in a message for your alarm")
                            .setView(dialogMessage)
                            .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int x) {
                                    alarmMessage = dialogMessage.getText().toString();
                                    AlarmReceiver.message = alarmMessage;
                                    scheduleAlarms();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int x) {
                                }
                            }).show();


                    scheduleAlarms();
                }
        });

    }

    private void setAlarm(int dayOfWeek){
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();

        //Check if it's being set to the past so it isn't fired instantly and sets it to the next week instead
        if(c.getTimeInMillis() < System.currentTimeMillis()){
            c.add(Calendar.DAY_OF_YEAR, 7);
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), (AlarmManager.INTERVAL_DAY * 7) + timeInMilli, alarmPendingIntent);
    }

    private void scheduleAlarms() {

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 1, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), (AlarmManager.INTERVAL_DAY * 7) + timeInMilli, alarmPendingIntent);

        if (mondayBox.isChecked()){
           // Toast.makeText(getApplicationContext(), "Monday Selected", Toast.LENGTH_SHORT).show();
            setAlarm(Calendar.MONDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);
        }
        else if(tuesdayBox.isChecked()){
            setAlarm(Calendar.TUESDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);

        }
        else if(wednesBox.isChecked()){
            setAlarm(Calendar.WEDNESDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);
        }
        else if(thursdayBox.isChecked()){
            setAlarm(Calendar.THURSDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);
        }
        else if(fridayBox.isChecked()){
            setAlarm(Calendar.FRIDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);
        }
        else if(saturdayBox.isChecked()){
            setAlarm(Calendar.SATURDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);
        }
        else if(sundayBox.isChecked()){
            setAlarm(Calendar.SUNDAY);
            // Reoccurring alarm that will go off every 60 seconds after the initial alarm is set
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, alarmPendingIntent);
        }
    }
}
