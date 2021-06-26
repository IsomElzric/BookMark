package com.turner.bookmark;

import android.app.Activity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

public class SearchManager extends Thread {

    private static final String TAG = "SearchManager";
    private final String baseUrl = "https://openlibrary.org/search.json?";
    private String title;
    private String author;
    private Gson gson;
    private HTTPHelper httpHelper;
    private SearchDocuments documents;

    public SearchManager(String title, String author) {
        gson = new Gson();
        httpHelper = new HTTPHelper();
        this.title = title;
        this.author = author;
    }

    private SearchDocuments searchBook() {
        String url = String.format("%stitle=%s&author=%s", baseUrl, title, author);
        String data = httpHelper.readHTTP(url);

        return gson.fromJson(data, SearchDocuments.class);
    }

    @Override
    public void run() {
        SearchManager manager = new SearchManager(title, author);
        documents = manager.searchBook();

        Log.d(TAG, String.format("Retrieved %s", documents));

        /*
        ArrayAdapter<BookDocument> documentAdapter =
                new ArrayAdapter<>(activity,
                        android.R.layout.simple_list_item_1,
                        documents.getDocs());

        ListView listView = activity.findViewById(R.id.listViewResults);

        activity.runOnUiThread(() -> listView.setAdapter(documentAdapter));
        */
    }

    public SearchDocuments getResults() {
        return documents;
    }
}
