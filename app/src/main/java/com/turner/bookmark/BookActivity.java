package com.turner.bookmark;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        notes = new ArrayList<>();

        if (intent.hasExtra("savedNote")) {
            Bundle intentExtras = getIntent().getExtras();
            String note = intentExtras.getString("savedNote");
            Log.i("Intent Received", "Received intent with note");
            notes.add(note);
            Log.i("Note Saved", "Note added to notes array");
        } else if (intent.hasExtra("deletedNote")) {
            Bundle intentExtras = getIntent().getExtras();
            String note = intentExtras.getString("deletedNote");
            Log.i("Intent Received", "intent to delete note");
            notes.remove(notes.indexOf(note));
            Log.i("Note Deleted", "Note deleted from notes array");
        }

        //Set up the listView for notes
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, notes);
        ListView noteView = (ListView) findViewById(R.id.notes);
        noteView.setAdapter(adapter);

       //Manage click events for each note in the listView
        noteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                Bundle extras = new Bundle();
                extras.putString("note", itemValue);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        try {
            Log.d(TAG, String.format("Deserialized to %s", book.toString()));
        }
        catch (NullPointerException npe) {
            Log.d(TAG, Arrays.toString(npe.getStackTrace()));
        }

        author = book.getAuthor().get(0);
        bookTitle = book.getTitle();

        TextView titleView = findViewById(R.id.book_title);
        titleView.setText(String.format("%s\n%s", bookTitle, author));

        Log.d(TAG, String.format("Book: %s by %s", bookTitle, author));
    }

    public void createNote(View view) {
        Intent noteIntent = new Intent(this, NoteActivity.class);
        startActivity(noteIntent);
    }

}