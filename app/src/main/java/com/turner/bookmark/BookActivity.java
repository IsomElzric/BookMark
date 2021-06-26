package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public String author;
    public String bookTitle;
    public String description;
    public int rating;
    public ArrayList<String> notes;
    public String bookCover;
    public static final String NOTE = "com.turner.bookmark.NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }

    public void createNote(View view) {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivity(intent);
    }

    public void removeNote(View view) {
        notes.remove("note");
    }

    //Getters and setters
    public String getAuthor() {
        return author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.bookTitle = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(int rating) {
        if (rating > 5){
            this.rating = 5;
        } else if (rating < 0) {
            this.rating = 0;
        } else {
            this.rating = 2;
        }
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }
}