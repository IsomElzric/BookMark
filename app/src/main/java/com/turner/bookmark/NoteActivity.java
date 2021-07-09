package com.turner.bookmark;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.turner.bookmark.databinding.ActivityNoteBinding;

public class NoteActivity extends AppCompatActivity{
    public static final String NOTE = "com.turner.bookmark.NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
    }

    public void saveNote(View view) {
        Intent intent = new Intent(this, BookActivity.class);
        EditText note = (EditText) findViewById(R.id.note);
        intent.putExtra(NOTE, note.getText());
        startActivity(intent);
    }
}
