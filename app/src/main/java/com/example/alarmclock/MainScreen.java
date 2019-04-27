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

        Button addAlarm = findViewById(R.id.addAlarmBtn);
        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addAlarmIntent = new Intent(getApplicationContext(), AddAlarmActivity.class);
                startActivity(addAlarmIntent);
            }
        });

        //Toolbar toolbar = findViewById(R.id.alarmLabel);
       // setSupportActionBar(toolbar);

    }

}
