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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.Calendar;

public class AddAlarmActivity extends AppCompatActivity {
    private TimePicker timePicker1;
    private CalendarView calendarView;
    private String alarmMessage;
    private EditText dialogMessage;
    int selYear, selMonth, selDayOfMonth, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        timePicker1 = findViewById(R.id.SingleTimePicker);
        calendarView = findViewById(R.id.setAlarmDate);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                selYear = year;
                selDayOfMonth = dayOfMonth;
                selMonth = month;
            }
        });


        final Button setAlarmButton = findViewById(R.id.SingleSetAlarm);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = timePicker1.getHour();
                minute = timePicker1.getMinute();


                dialogMessage = new EditText(getApplicationContext());

                new AlertDialog.Builder(AddAlarmActivity.this)
                        .setTitle("Alarm Message")
                        .setMessage("Type in a message for your alarm")
                        .setView(dialogMessage)
                        .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int x) {
                                alarmMessage = dialogMessage.getText().toString();
                                AlarmReceiver.message = alarmMessage;
                                setAlarm();
                                Toast.makeText(getApplicationContext(), "Date alarm set", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int x) {
                            }
                        }).show();
            }
        });
    }

    private void setAlarm(){
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 1, alarmIntent, 0);
        Calendar c = Calendar.getInstance();
        //Set time to the current system's time
        c.setTimeInMillis(System.currentTimeMillis());
        c.clear();
        c.set(selYear, selMonth, selDayOfMonth, hour, minute);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Set the alarm
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmPendingIntent);



    }

}
