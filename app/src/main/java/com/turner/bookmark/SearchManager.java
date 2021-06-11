package com.turner.bookmark;

import com.google.gson.Gson;

public class SearchManager extends Thread {

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
    }

    public SearchDocuments getBooks() {
        if (documents != null) {
            return documents;
        }
        return null;
    }
}
