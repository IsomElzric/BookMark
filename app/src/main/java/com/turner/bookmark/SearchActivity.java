package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.turner.bookmark.BOOK";
    private static final String TAG = "SearchActivity";
    private SearchDocuments documents;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void onClickSearch(View view) throws InterruptedException {
        EditText editTitle = findViewById(R.id.editTitle);
        EditText editAuthor = findViewById(R.id.editAuthor);

        String title = String.valueOf(editTitle.getText());
        String author = String.valueOf(editAuthor.getText());

        Log.d(TAG, String.format("Searching for %s by %s", title, author));

        SearchManager searchManager = new SearchManager(title, author, activity);

        Thread threadSearch = new Thread(searchManager);
        threadSearch.start();
        threadSearch.join();

        documents = searchManager.getResults();

        ArrayAdapter<BookDocument> documentAdapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_list_item_1,
                documents.getDocs());

        ListView listView = activity.findViewById(R.id.listViewResults);
        listView.setAdapter(documentAdapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            Log.d(TAG, String.format("Clicked on: %s", documents.getBook(position)));
            Gson gson = new Gson();
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra(EXTRA_MESSAGE, gson.toJson(documents.getBook(position)));
            startActivity(intent);
        });
    }
}