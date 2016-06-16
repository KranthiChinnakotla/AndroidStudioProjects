package com.medha.sqldemo;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathyusha on 3/14/16.
 */
public class NoteDAO {
    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Note note) {

        ContentValues values = new ContentValues();
        values.put(NoteTable.COLUMN_SUBJECT, note.getSubject());
        values.put(NoteTable.COLUMN_TEXT, note.getText());
        return db.insert(NoteTable.TABLE_NAME, null, values);
    }

    public boolean update(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteTable.COLUMN_SUBJECT, note.getSubject());
        values.put(NoteTable.COLUMN_TEXT, note.getText());
        return db.update(NoteTable.TABLE_NAME, values, NoteTable.COLUMN_ID + "=?", new String[]{note.getId() + ""}) > 0;
    }

    public boolean delete(Note note) {
        return db.delete(NoteTable.TABLE_NAME, NoteTable.COLUMN_ID + "=?", new String[]{note.getId() + ""}) > 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Note get(long id) {

        Note note = null;
        Cursor c = db.query(true, NoteTable.TABLE_NAME, new String[]{NoteTable.COLUMN_ID, NoteTable.COLUMN_SUBJECT, NoteTable.COLUMN_TEXT},
                NoteTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            note = buildNotefromCursor(c);
            if(!c.isClosed())
                c.close();
        }
        return note;
    }

    public List<Note> getAll() {

        List<Note> notes = new ArrayList<Note>();
        Cursor c = db.query(true, NoteTable.TABLE_NAME, new String[]{NoteTable.COLUMN_ID, NoteTable.COLUMN_SUBJECT, NoteTable.COLUMN_TEXT},
                null, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            do{
                Note note = buildNotefromCursor(c);
                if(note != null)
                    notes.add(note);
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }

        return notes;
    }

    private Note buildNotefromCursor(Cursor c) {
        Note note = null;
        if (c != null) {
            note = new Note();
            note.setId(c.getLong(0));
            note.setSubject(c.getString(1));
            note.setText(c.getString(2));
        }
        return note;

    }


}
