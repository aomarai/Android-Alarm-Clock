package com.example.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        //TODO create Timer page
        Button addTimer = findViewById(R.id.TimerBtn);
        addTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTimerIntent = new Intent(getApplicationContext(), TimerActivity.class);
                startActivity(addTimerIntent);
            }
        });

        //TODO Make sure the previous page works
        Button addSingleAlarm = findViewById(R.id.SingleAlarm);
        addSingleAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAlarmIntent = new Intent(getApplicationContext(), AddAlarmActivity.class);
                startActivity(addAlarmIntent);
            }
        });

        //TODO create Recursive Alarm page
        Button addRecursiveAlarm = findViewById(R.id.RecursiveAlarmBtn);
        addRecursiveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recursiveAlarmIntent = new Intent(getApplicationContext(), RecursiveAlarm.class);
                startActivity(recursiveAlarmIntent);
            }
        });

        //TODO create location alarm and check if one exists already
        Button addLocationAlarm = findViewById(R.id.LocationAlarmButton);
        addLocationAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addLocationIntent = new Intent(getApplicationContext(), LocationAlarm.class);
                startActivity(addLocationIntent);
            }
        });


        //Toolbar toolbar = findViewById(R.id.alarmLabel);
       // setSupportActionBar(toolbar);


    }

}
