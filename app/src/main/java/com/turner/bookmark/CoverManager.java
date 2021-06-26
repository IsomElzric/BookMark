package com.turner.bookmark;

import android.util.Log;

import com.google.gson.Gson;
import java.io.File;

public class CoverManager extends Thread {

    private static final String TAG = "CoverManager";
    private final String baseUrl = "http://covers.openlibrary.org/b/isbn/";
    private String isbn;
    private HTTPHelper httpHelper;

    public CoverManager(String isbn) {
        this.isbn = isbn;
        httpHelper = new HTTPHelper();
    }

    private void downloadCover() {
        // Currently in progress

        String url = String.format("%s%s-M.jpg", baseUrl, isbn);

        Log.d(TAG, String.format("URL: %s", url));

        new File("app/src/main/res/books/" + isbn).mkdirs();
        httpHelper.downloadCover(url, String.format("main/res/books/%s/cover.jpg", isbn));
    }

    public String getCover(String isbn) {
        return String.format("app/src/main/res/books/%s/cover.jpg", isbn);
    }

    @Override
    public void run() {
        CoverManager manager = new CoverManager(isbn);
        manager.downloadCover();
    }
}
