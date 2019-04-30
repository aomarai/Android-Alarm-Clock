package com.example.alarmclock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

public class RecursiveAlarm extends AppCompatActivity {
    private Button recurButton;
    private TimePicker recurPicker;
    private String userMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursive_alarm);
        recurButton = findViewById(R.id.button);
        recurPicker = findViewById(R.id.timePicker1);


    }
}
