package com.medha.inclass08;

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

        return db.insert(StoriesTable.TABLE_NAME, null, values);
    }

    public boolean update(Stories story) {
        ContentValues values = new ContentValues();
        values.put(StoriesTable.COLUMN_TITLE, story.getStoryTitle());
        values.put(StoriesTable.COLUMN_BYLINE, story.getByLine());
        values.put(StoriesTable.COLUMN_ABSTRACT, story.getAbstractNY());
        values.put(StoriesTable.CREATED_DATE, story.getDate());
        values.put(StoriesTable.THUMB_IMAGE_URL, story.getThumbnail());
        values.put(StoriesTable.NORMAL_IMAGE_URL, story.getImageURL());
        return db.update(StoriesTable.TABLE_NAME, values, StoriesTable.COLUMN_ID + "=?", new String[]{story.get_id() + ""}) > 0;
    }

    public boolean delete(Stories story) {
        return db.delete(StoriesTable.TABLE_NAME, StoriesTable.COLUMN_ID + "=?", new String[]{story.get_id() + ""}) > 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Stories get(long id) {

        Stories story = null;
        Cursor c = db.query(true, StoriesTable.TABLE_NAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_TITLE, StoriesTable.COLUMN_BYLINE,
                StoriesTable.COLUMN_ABSTRACT,StoriesTable.CREATED_DATE,StoriesTable.THUMB_IMAGE_URL,StoriesTable.NORMAL_IMAGE_URL},
                StoriesTable.COLUMN_ID + "=?", new String[]{id + ""}, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            story = buildNotefromCursor(c);
            if(!c.isClosed())
                c.close();
        }
        return story;
    }

    public List<Stories> getAll() {

        List<Stories> notes = new ArrayList<Stories>();
        Cursor c = db.query(true, StoriesTable.TABLE_NAME, new String[]{StoriesTable.COLUMN_ID, StoriesTable.COLUMN_TITLE, StoriesTable.COLUMN_BYLINE,
                        StoriesTable.COLUMN_ABSTRACT,StoriesTable.CREATED_DATE,StoriesTable.THUMB_IMAGE_URL,StoriesTable.NORMAL_IMAGE_URL},
                null, null, null, null, null, null);
        if(c != null && c.moveToFirst()){
            do{
                Stories story = buildNotefromCursor(c);
                if(story != null)
                    notes.add(story);
            }while (c.moveToNext());

            if(!c.isClosed()){
                c.close();
            }
        }

        return notes;
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
        }
        return story;

    }

}
