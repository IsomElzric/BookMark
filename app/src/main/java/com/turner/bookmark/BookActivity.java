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

        StarRating starRating = new StarRating();

//        ListView testing
//        notes.add("note text");
//        notes.add("blah blah blah");
//        notes.add("third test string");
//        notes.add("note1");
//        notes.add("note2");
//        notes.add("note3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, notes);
        ListView listview = (ListView) findViewById(R.id.notes);
        listview.setAdapter(adapter);

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
}