package com.medha.sqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseDataManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseDataManager(this);
        dm.saveNote(new Note("Note 1","Note1 Text"));
        dm.saveNote(new Note("Note 2","Note2 Text"));
        dm.saveNote(new Note("Note 3","Note3 Text"));
        dm.updateNote(new Note("Note 3","updated !"));

        List<Note> notes = dm.getAllNotes();
        Log.d("demo",notes.toString());
    }

    @Override
    protected void onDestroy() {
        dm.close();
        super.onDestroy();
    }
}
