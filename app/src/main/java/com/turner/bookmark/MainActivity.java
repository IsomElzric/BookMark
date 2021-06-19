package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNotification();
    }

    private void setNotification() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Date currentTime = Calendar.getInstance().getTime();
        Integer hour = currentTime.getHours();
        Integer minutes = currentTime.getMinutes();

//        if (prefs.getBoolean("firstTime", false)) {

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, currentTime.getHours());
        calendar.set(Calendar.MINUTE, currentTime.getMinutes() + 1);
        calendar.set(Calendar.SECOND, 1);

//            Log.d("tag", "notification time: " + calendar.getTime().toString());

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.RTC /*AlarmManager.INTERVAL_DAY*/, pendingIntent);

//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putBoolean("firstTime", true);
//            editor.apply();
//        }

    }


}