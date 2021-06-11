package com.turner.bookmark;

import com.google.gson.Gson;
import java.io.File;

public class CoverManager extends Thread {

    private final String baseUrl = "http://covers.openlibrary.org/b/isbn/";
    private String isbn;
    private Gson gson;
    private HTTPHelper httpHelper;

    public CoverManager(String isbn) {
        this.isbn = isbn;
        gson = new Gson();
        httpHelper = new HTTPHelper();
    }

    private void downloadCover() {
        // Currently in progress

        String url = String.format("%s%s-M.jpg", baseUrl, isbn);
        new File("main/res/books/" + isbn).mkdirs();
        httpHelper.downloadCover(url, String.format("main/res/books/%s/cover.jpg", isbn));
    }

    public String getCover(String isbn) {
        return String.format("main/res/books/%s/cover.jpg", isbn);
    }

    @Override
    public void run() {
        CoverManager manager = new CoverManager(isbn);
        manager.downloadCover();
    }
}
