package com.example.tejakanchinadam.inclass07app;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejakanchinadam on 3/14/16.
 */
public class StoriesDAO {

    private SQLiteDatabase db;

    public StoriesDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Stories story) {

        ContentValues values = new ContentValues();
        values.put(StoriesTable.COLUMN_TITLE, story.getStoryTitle());
        values.put(StoriesTable.COLUMN_BYLINE, story.getByLine());
        values.put(StoriesTable.COLUMN_ABSTRACT, story.getAbstractNY());
        values.put(StoriesTable.CREATED_DATE, story.getDate());
        values.put(StoriesTable.THUMB_IMAGE_URL, story.getThumbnail());
        values.put(StoriesTable.NORMAL_IMAGE_URL, story.getImageURL());
        values.put(StoriesTable.CATEGORY, story.getCategory());

        return db.insert(StoriesTable.TABLE_NAME, null, values);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public List<Stories> getStoriesByCategory(String category ) {

        List<Stories> stories = new ArrayList<Stories>();
        Cursor c = db.query(true, StoriesTable.TABLE_NAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_TITLE, StoriesTable.COLUMN_BYLINE,
                        StoriesTable.COLUMN_ABSTRACT,StoriesTable.CREATED_DATE,StoriesTable.THUMB_IMAGE_URL,StoriesTable.NORMAL_IMAGE_URL, StoriesTable.CATEGORY},
                StoriesTable.CATEGORY + "=?", new String[]{category + ""}, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            do{
                Stories story = buildNotefromCursor(c);
                if(story != null)
                    stories.add(story);
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }

        return stories;
    }

    public boolean update(Stories story) {
        ContentValues values = new ContentValues();
        values.put(StoriesTable.COLUMN_TITLE, story.getStoryTitle());
        values.put(StoriesTable.COLUMN_BYLINE, story.getByLine());
        values.put(StoriesTable.COLUMN_ABSTRACT, story.getAbstractNY());
        values.put(StoriesTable.CREATED_DATE, story.getDate());
        values.put(StoriesTable.THUMB_IMAGE_URL, story.getThumbnail());
        values.put(StoriesTable.NORMAL_IMAGE_URL, story.getImageURL());
        values.put(StoriesTable.CATEGORY, story.getCategory());
        return db.update(StoriesTable.TABLE_NAME, values, StoriesTable.COLUMN_ID + "=?", new String[]{story.get_id() + ""}) > 0;
    }

    public boolean delete(Stories story) {
        return db.delete(StoriesTable.TABLE_NAME, StoriesTable.COLUMN_TITLE + "=?", new String[]{story.getStoryTitle() + ""}) > 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Stories get(String title) {

        Stories story = null;
        Cursor c = db.query(true, StoriesTable.TABLE_NAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_TITLE, StoriesTable.COLUMN_BYLINE,
                        StoriesTable.COLUMN_ABSTRACT,StoriesTable.CREATED_DATE,StoriesTable.THUMB_IMAGE_URL,StoriesTable.NORMAL_IMAGE_URL, StoriesTable.CATEGORY},
                StoriesTable.COLUMN_TITLE + "=?", new String[]{title + ""}, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            story = buildNotefromCursor(c);
            if(!c.isClosed())
                c.close();
        }
        return story;
    }

    public boolean deleteAll(){

       return db.delete(StoriesTable.TABLE_NAME,null,null) > 0;


    }

    public List<Stories> getAll() {

        List<Stories> storys = new ArrayList<Stories>();
        Cursor c = db.query(true, StoriesTable.TABLE_NAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_TITLE, StoriesTable.COLUMN_BYLINE,
                        StoriesTable.COLUMN_ABSTRACT,StoriesTable.CREATED_DATE,StoriesTable.THUMB_IMAGE_URL,StoriesTable.NORMAL_IMAGE_URL, StoriesTable.CATEGORY},
                null, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            do{
                Stories story = buildNotefromCursor(c);
                if(story != null)
                    storys.add(story);
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }

        return storys;
    }

    private Stories buildNotefromCursor(Cursor c) {
        Stories story = null;
        if (c != null) {
            story = new Stories();
            story.set_id(c.getLong(0));
            story.setStoryTitle(c.getString(1));
            story.setByLine(c.getString(2));
            story.setAbstractNY(c.getString(3));
            story.setDate(c.getString(4));
            story.setThumbnail(c.getString(5));
            story.setImageURL(c.getString(6));
            story.setCategory(c.getString(7));
        }
        return story;

    }



}
