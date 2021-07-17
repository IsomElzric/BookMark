package com.turner.bookmark;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.turner.bookmark.databinding.ActivityNoteBinding;

public class NoteActivity extends AppCompatActivity{
    public static final String SAVED_NOTE = "com.turner.bookmark.SAVED_NOTE";
    public static final String DELETED_NOTE = "com.turner.bookmark.DELETED_NOTE";
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Intent intent = getIntent();

        if (intent.hasExtra("note")) {
            Bundle intentExtras = getIntent().getExtras();
            String note = intentExtras.getString("note");
            Log.i("Intent Received", "Received intent with the following note: " + note);
            EditText noteField = findViewById(R.id.note);
            noteField.setText(note);
        } else {
            EditText noteField = findViewById(R.id.note);
            noteField.setText("");
        }
    }

    public void saveNote(View view) {
        Intent saveIntent = new Intent(this, BookActivity.class);
        Bundle extras = new Bundle();
        EditText note = (EditText) findViewById(R.id.note);
        Editable savedNote = note.getText();
        extras.putString("savedNote", String.valueOf(savedNote));
        saveIntent.putExtras(extras);
        //saveIntent.putExtra(SAVED_NOTE, note.getText());
        Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        startActivity(saveIntent);
    }

    public void deleteNote(View view) {
        Intent deleteIntent = new Intent(this, BookActivity.class);
        Bundle extras = new Bundle();
        if (intent.hasExtra("note")) {
            Bundle intentExtras = getIntent().getExtras();
            String note = intentExtras.getString("note");
            extras.putString("deletedNote", note);
            deleteIntent.putExtras(extras);

        }
        startActivity(deleteIntent);
    }
}