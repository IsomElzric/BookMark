package com.turner.bookmark;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReminderActivity extends AppCompatActivity {

    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reminder_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        SharedPreferences sharedpreferences = this.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String repeat = sharedpreferences.getString("repeat", null);
        String reminderTime = sharedpreferences.getString("reminderTime", null);
        String customMessage = sharedpreferences.getString("customReminder", null);
        String ampm = sharedpreferences.getString("ampm", null);

        Spinner dropdown = findViewById(R.id.repeat);
//create a list of items for the spinner.
        String[] items = new String[]{"Hourly", "Daily", "Weekly"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        if (repeat == "Hourly") {
            dropdown.setSelection(0);
        } else if (repeat == "Daily") {
            dropdown.setSelection(1);
        } else if (repeat == "Weekly") {
            dropdown.setSelection(2);
        }

        Spinner ampmDropdown = findViewById(R.id.ampm);
//create a list of items for the spinner.
        String[] timeOfDay = new String[]{"AM", "PM"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> ampmAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeOfDay);
//set the spinners adapter to the previously created one.
        ampmDropdown.setAdapter(ampmAdapter);

        if (ampm == "AM") {
            ampmDropdown.setSelection(0);
        } else if (ampm == "PM") {
            ampmDropdown.setSelection(1);
        }

        TextView time = findViewById(R.id.editTextTime);

        if (reminderTime == null) {
            reminderTime = "19:00";
        }
        time.setText(reminderTime);
        TextView customReminder = findViewById(R.id.editTextCustomReminder);
        customReminder.setText(customMessage);

    }

    public void cancelButtonOnClick(View view) {
        Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
    }

    public void saveButtonOnClick(View view) {
        TextView time = findViewById(R.id.editTextTime);

        String timeText = time.getText().toString();
        if (time.getText() == null || timeText.equals("")) {
            time.setError("This field is required");
            return;
        }
// Used regular expression to validate time
        String TIME12HOUR_PATTERN =
                "([1-9]|1[012]):[0-5][0-9]";
        Pattern pattern = Pattern.compile(TIME12HOUR_PATTERN);
        Matcher matcher = pattern.matcher(time.getText().toString());
        if (!matcher.matches()) {
            time.setError("Use correct time format");
            return;
        }

        Intent intent = new Intent(activity, MainActivity.class);

        TextView customReminder = findViewById(R.id.editTextCustomReminder);

        Spinner repeat = findViewById(R.id.repeat);
        Spinner ampmSpinner = findViewById(R.id.ampm);

        SharedPreferences sharedpreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("reminderTime", time.getText().toString());

        editor.putString("customReminder", customReminder.getText().toString());

        editor.putString("repeat", repeat.getSelectedItem().toString());
        editor.putString("ampm", ampmSpinner.getSelectedItem().toString());

        editor.commit();

        ReminderHelper.setNotification(this); //call setNotification method

        startActivity(intent);
    }

    public static class ReminderHelper {
        public static void setNotification(Context context) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

            Date currentTime = Calendar.getInstance().getTime();
            Integer hour = currentTime.getHours();
            Integer minutes = currentTime.getMinutes();

            Intent alarmIntent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            SharedPreferences sharedpreferences = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            String repeat = sharedpreferences.getString("repeat", null);
            String reminderTime = sharedpreferences.getString("reminderTime", null);
            String ampmReminderTime = sharedpreferences.getString("ampm", null);

            String[] splitString = reminderTime.split(":");

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
//            calendar.set(Calendar.HOUR_OF_DAY, currentTime.getHours());
//            calendar.set(Calendar.MINUTE, currentTime.getMinutes());

            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitString[0]));
            calendar.set(Calendar.MINUTE, Integer.parseInt(splitString[1]));
            //ternary operator
            calendar.set(Calendar.AM_PM, ampmReminderTime == "AM" ? 0 : 1);

            calendar.set(Calendar.SECOND, 0);

            long interval = 0;
            if (repeat == "Hourly") {
                interval = AlarmManager.INTERVAL_HOUR;
            } else if (repeat == "Daily") {
                interval = AlarmManager.INTERVAL_DAY;
            } else {
                interval = AlarmManager.INTERVAL_DAY * 7;
            }

            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    interval, pendingIntent);

        }
    }
}