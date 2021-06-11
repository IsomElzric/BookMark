package com.turner.bookmark;

public class BookData {

    private String isbn;
    private String title;
    private String author;
    private String cover;

    public BookData(String isbn, String title, String author, String cover) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.cover = cover;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCover() {
        return cover;
    }

    public String toString() {
        return String.format("%s: %s by %s cover path: %s",
                isbn, title, author, cover);
    }
}
