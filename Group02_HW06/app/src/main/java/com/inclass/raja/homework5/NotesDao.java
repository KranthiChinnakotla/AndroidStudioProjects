package com.inclass.raja.homework5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathyusha on 3/17/16.
 */
public class NotesDao {

    private SQLiteDatabase db;

    public NotesDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Notes notes) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_DATE, notes.getDate());
        values.put(NotesTable.COLUMN_NOTES, notes.getNote());
        return db.insert(NotesTable.TABLE_NAME, null, values);
    }

    public boolean update(Notes notes) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_DATE, notes.getDate());
        values.put(NotesTable.COLUMN_NOTES, notes.getNote());
        return db.update(NotesTable.TABLE_NAME, values, NotesTable.COLUMN_DATE + "=?", new String[]{notes.getDate() + ""}) > 0;
    }

    public boolean delete(Notes notes) {
        return db.delete(NotesTable.TABLE_NAME, NotesTable.COLUMN_DATE + "=?", new String[]{notes.getDate() + ""}) > 0;
    }

    public Notes get(long cityID) {
        Notes notes = null;
        Cursor c = db.query(true, NotesTable.TABLE_NAME, new String[]{NotesTable.COLUMN_CITYID, NotesTable.COLUMN_DATE, NotesTable.COLUMN_DATE},
                NotesTable.COLUMN_CITYID + "=?", new String[]{cityID + ""}, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            notes = buildNotesfromCursor(c);
            if (!c.isClosed())
                c.close();
        }
        return notes;
    }

    public List<Notes> getAll() {
        List<Notes> notesList = new ArrayList<Notes>();
        Cursor c = db.query(true, NotesTable.TABLE_NAME, new String[]{NotesTable.COLUMN_CITYID, NotesTable.COLUMN_DATE, NotesTable.COLUMN_NOTES},
                null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                Notes notes = buildNotesfromCursor(c);
                if (notes != null)
                    notesList.add(notes);
            } while (c.moveToNext());
            if (!c.isClosed())
                c.close();
        }
        return notesList;
    }

    public boolean deleteAll() {
        return db.delete(NotesTable.TABLE_NAME, null, null) > 0;
    }

    private Notes buildNotesfromCursor(Cursor c) {
        Notes notes = null;
        if (c != null) {
            notes = new Notes();
            notes.setCityKey(c.getLong(0));
            notes.setDate(c.getString(1));
            notes.setNote(c.getString(2));
        }
        return notes;
    }
}
