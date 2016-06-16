package com.example.tejakanchinadam.inclass07app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by tejakanchinadam on 3/14/16.
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

    public StoriesDAO getStoryDAO() {
        return this.storyDAO;
    }

    public long saveStory(Stories story) {
        return this.storyDAO.save(story);
    }

    public boolean updateStory(Stories story) {
        return this.storyDAO.update(story);
    }

    public boolean deleteStory(Stories story) {
        return this.storyDAO.delete(story);
    }

    public Stories getStory(String title) {
        return this.storyDAO.get(title);
    }

    public List<Stories> getAllStories() {
        return this.storyDAO.getAll();
    }

    public List<Stories> getAllStoriesByCategory(String section){

        return this.storyDAO.getStoriesByCategory(section);

    }

    public boolean deleteAll() {
        return this.storyDAO.deleteAll();
    }



}
