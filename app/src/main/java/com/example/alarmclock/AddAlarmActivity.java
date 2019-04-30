package com.example.alarmclock;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.PendingIntent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddAlarmActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker timePicker1;
    private static AddAlarmActivity inst;
    private CalendarView calendarView;
    int year, month, dayOfMonth;


    @Override
    public void onStart(){
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        final Button setAlarmButton = findViewById(R.id.button);
        setAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                        int hour = timePicker1.getHour();
                        int minute = timePicker1.getMinute();
                    }
               });
            }
        });
    }

}

