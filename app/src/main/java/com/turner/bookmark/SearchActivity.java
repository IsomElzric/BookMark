package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private SearchDocuments documents;
    private BookList bookList;
    private final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Intent intent = getIntent();
        // bookList = (BookList) intent.getSerializableExtra(MainActivity.EXTRA_MESSAGE);
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
            // bookList.addCurrent(documents.getBook(position));
        });
    }
}