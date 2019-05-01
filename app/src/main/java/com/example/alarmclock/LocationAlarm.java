package com.example.alarmclock;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class LocationAlarm extends AppCompatActivity {


    LocationManager locationManager;
    LocationListener locationListener;
    static Location currentLoc;
    static boolean alarmActive;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_alarm);
        alarmActive = false;
        final int[] inputContent = {2};


        final TextView[] currentLocationDisplay = {findViewById(R.id.CurrentLocationDisplay)};


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // this means changed location
                currentLoc = location;
                currentLocationDisplay[0].setText(location.getLatitude() + ", " + location.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }





        };

        final EditText timeInput = findViewById(R.id.LocationAlarmSetField);
        timeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String input = charSequence.toString();
                int convertInput = 2;
                try {
                    convertInput = Integer.parseInt(input);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please enter a number",Toast.LENGTH_SHORT).show();

                    return;
                }
                inputContent[0] = convertInput;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Button setTimer = findViewById(R.id.LocationAlarmButton);
        setTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set timer
                int amount = inputContent[0]*60*1000;
                if(!alarmActive) {
                    Timer timer = new Timer();
                    LocationTimer task = new LocationTimer(getApplicationContext(), amount);
                    timer.schedule(task, amount);
                    alarmActive = true;
                } else {
                    Toast.makeText(getApplicationContext(),"A location alarm is already active.",Toast.LENGTH_SHORT).show();
                }

            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }




    }
}

