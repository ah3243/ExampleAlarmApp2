package com.example.examplealarmapp2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    long diffMins =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timePicker);
        findViewById(R.id.buttonSetAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              Get the target and current times
                Calendar tarCalendar = Calendar.getInstance();
                Calendar curCalendar = Calendar.getInstance();

//              The Target time in milliseconds
                if(Build.VERSION.SDK_INT >=23){
                    tarCalendar.set(
                            tarCalendar.get(Calendar.YEAR),
                            tarCalendar.get(Calendar.MONTH),
                            tarCalendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                }else{
                    tarCalendar.set(
                            tarCalendar.get(Calendar.YEAR),
                            tarCalendar.get(Calendar.MONTH),
                            tarCalendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );
                }

//              The current time in milliseconds
                curCalendar.set(
                        curCalendar.get(Calendar.YEAR),
                        curCalendar.get(Calendar.MONTH),
                        curCalendar.get(Calendar.DAY_OF_MONTH),
                        curCalendar.get(Calendar.HOUR),
                        curCalendar.get(MINUTE),
                        0
                );

                long curMillis =  curCalendar.getTimeInMillis();
                long tarMillis =  tarCalendar.getTimeInMillis();

//              Set the target time as an alarm
                setAlarm(tarMillis);

//              Calculate the time until the alarm in hours and minutes
                diffMins = (tarMillis - curMillis)/(1000*60);
                int hours = (int) diffMins /(60);
                int mins = (int) diffMins - (hours*60);
                String timeVals = String.valueOf(hours) + " Hours " + String.valueOf(mins) + " Minutes";

//                System.out.println(" -Hours are: "  + hours + " -timeVals: " + timeVals + " -Diff " + diffMins);
//                System.out.println("The timepicker Hour " + curCalendar.get(HOUR_OF_DAY)  + " and Minute: "  + curCalendar.get(MINUTE));

                Toast.makeText(MainActivity.this, timeVals, Toast.LENGTH_LONG).show();

            }
        });
    }


    private void setAlarm(long timeInMillis){

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.setRepeating(alarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);

//        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();

    }
}
