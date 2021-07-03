package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.turner.bookmark.BOOK";
    private List<BookDocument> list;
    private final Activity activity = this;
    private static final BookList bookList = new BookList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //InternalStorage pullStorage = new InternalStorage();
        //pullStorage.readFile();
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        ListView listView = findViewById(R.id.listViewBooks);

        Intent intent = getIntent();
        BookDocument book = gson.fromJson(intent.getStringExtra(SearchActivity.EXTRA_MESSAGE), BookDocument.class);

        try {
            Log.d(TAG, String.format("Deserialized to %s", book.toString()));

            bookList.addCurrent(book);
            Log.d(TAG, String.format("Book list contains %s", bookList.getFullList()));
        }
        catch (NullPointerException npe) {
            Log.d(TAG, Arrays.toString(npe.getStackTrace()));
        }

        if (bookList.getFullList() != null) {
            list = bookList.getFullList();
            ArrayAdapter<BookDocument> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                Log.d(TAG, String.format("Clicked on: %s", list.get(position)));
                Intent intent1 = new Intent(activity, BookActivity.class);
                intent1.putExtra(EXTRA_MESSAGE, gson.toJson(list.get(position)));
                startActivity(intent1);

                /*
                CoverManager coverManager = new CoverManager(list.get(position).getIsbn().get(0));
                Thread coverThread = new Thread(coverManager);
                coverThread.start();
                */
            });
        }

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

    public void onClickForTest2(View view) {
        Intent intent = new Intent(activity, SearchActivity.class);
        startActivity(intent);
    }


}