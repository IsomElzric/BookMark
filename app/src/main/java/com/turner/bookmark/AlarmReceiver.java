package com.turner.bookmark;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("tag", "AlarmReceiver.onReceive called!");
        // show toast
        Toast.makeText(context, "Alarm running", Toast.LENGTH_LONG).show();
    }
}
