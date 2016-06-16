package com.inclass.raja.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Notes> notesList;
    DatabaseDataManager dm;
    NotesAdapter nAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        listView = (ListView) findViewById(R.id.listView_notes);
        dm = new DatabaseDataManager(NoteActivity.this,"citynotes");
        notesList= (ArrayList<Notes>) dm.getAllNotes();
        nAdapter = new NotesAdapter(NoteActivity.this,R.layout.notes_adapter,notesList);
        listView.setAdapter(nAdapter);
        nAdapter.setNotifyOnChange(true);

    }
}
