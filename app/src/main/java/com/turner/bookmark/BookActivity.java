package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class BookActivity extends AppCompatActivity {

    public static final String TAG = "BookActivity";
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
        Gson gson = new Gson();
        Intent intent = getIntent();
        BookDocument book = gson.fromJson(intent.getStringExtra(MainActivity.EXTRA_MESSAGE),
                BookDocument.class);

        //ListView testing
        notes.add("note text");
        notes.add("blah blah blah");
        notes.add("third test string");
        ArrayAdapter noteAdapter = new ArrayAdapter<String>(this, R.layout.activity_book,
                notes);
        ListView listview = (ListView) findViewById(R.id.notes);
        listview.setAdapter(noteAdapter);

        try {
            Log.d(TAG, String.format("Deserialized to %s", book.toString()));
        }
        catch (NullPointerException npe) {
            Log.d(TAG, Arrays.toString(npe.getStackTrace()));
        }

        author = book.getAuthor().get(0);
        bookTitle = book.getTitle();

        Log.d(TAG, String.format("Book: %s by %s", bookTitle, author));
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