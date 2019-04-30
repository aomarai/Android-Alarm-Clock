package com.example.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddAlarmActivity extends AppCompatActivity {
    private TimePicker timePicker1;
    private CalendarView calendarView;
    private String userMessage;
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
                //TODO: Set user message from input field once UI is updated
                AlarmReceiver.message="Date Message Test";

                setAlarm();
                //Toast.makeText(getApplicationContext(), "Minute " + minute, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //TODO: Fire alarm
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
        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmPendingIntent);



    }

}

