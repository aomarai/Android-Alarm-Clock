package com.example.alarmclock;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.content.Context;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

public class AddAlarmActivity extends AppCompatActivity {
    Calendar calendar;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        GregorianCalendar c = null;
        CalendarView v = findViewById(R.id.setAlarmDate);
        v.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                setCalendar(new GregorianCalendar(year, month, dayOfMonth));
            }

        });


        EditText hoursMinutes = findViewById(R.id.setAlarmTime);
        hoursMinutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                time = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Button addNewAlarm = findViewById(R.id.acceptAlarmBtn);
        addNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The alarm will be set based on the time inputted, if not should be 00:00
                AlarmReceiver.setAlarm(getApplicationContext(),v,time,calendar);



            }
        });
    }

    public void setCalendar(GregorianCalendar c){
        calendar = c;
    }
}
