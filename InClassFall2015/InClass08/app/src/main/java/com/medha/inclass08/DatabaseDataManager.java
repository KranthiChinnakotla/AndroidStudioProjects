package com.medha.inclass08;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Prathyusha on 3/14/16.
 */
public class DatabaseDataManager {

    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private StoriesDAO storyDAO;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        storyDAO = new StoriesDAO(db);
    }

    public void close() {
        if (db != null) {
            db.close();
        }
    }

    public StoriesDAO getNoteDAO() {
        return this.storyDAO;
    }

    public long saveNote(Stories story) {
        return this.storyDAO.save(story);
    }

    public boolean updateNote(Stories story) {
        return this.storyDAO.update(story);
    }

    public boolean deleteNote(Stories story) {
        return this.storyDAO.delete(story);
    }

    public Stories getNote(long id) {
        return this.storyDAO.get(id);
    }

    public List<Stories> getAllNotes() {
        return this.storyDAO.getAll();
    }
}
