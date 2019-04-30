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
    private TimePicker timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);


        Button setAlarm = findViewById(R.id.button);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

            }
       });
    }

}

