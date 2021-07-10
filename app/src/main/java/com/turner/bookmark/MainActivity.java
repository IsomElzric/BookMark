package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.gson.Gson;
import java.util.Arrays;
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
        ReminderActivity.ReminderHelper.setNotification(this);

    }


    public void onClickForTest2(View view) {
        Intent intent = new Intent(activity, SearchActivity.class);
        startActivity(intent);
    }

    public void reminderButtonOnClick(View view) {
        Intent intent = new Intent(activity, ReminderActivity.class);
        startActivity(intent);
    }
}