package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.turner.bookmark.BOOKLIST";
    private static final String TAG = "MainActivity";
    private List<BookDocument> list;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listViewBooks);
        BookList bookList = new BookList();
        if (bookList.getFullList() != null) {
            list = bookList.getFullList();
            ArrayAdapter<BookDocument> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_list_item_1, list);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener((parent, view, position, id) -> {
                Log.d(TAG, String.format("Clicked on: %s", list.get(position)));
            });
        }
    }

    public void onClickForTest2(View view) {
        Intent intent = new Intent(activity, SearchActivity.class);
        // intent.putExtra(EXTRA_MESSAGE, bookList);
        startActivity(intent);
    }
}