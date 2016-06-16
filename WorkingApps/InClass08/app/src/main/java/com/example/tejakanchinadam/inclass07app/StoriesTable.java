package com.example.tejakanchinadam.inclass07app;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tejakanchinadam on 3/14/16.
 */
public class StoriesTable {

    static final String TABLE_NAME = "stories";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_TITLE = "title";
    static final String COLUMN_BYLINE = "byline";
    static final String COLUMN_ABSTRACT = "abstract";
    static final String CREATED_DATE = "date";
    static final String THUMB_IMAGE_URL = "thumburl";
    static final String NORMAL_IMAGE_URL = "normalurl";
    static final String CATEGORY = "category";

    static public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLE_NAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_TITLE + " text not null, ");
        sb.append(COLUMN_BYLINE + " text not null, ");
        sb.append(COLUMN_ABSTRACT + " text not null, ");
        sb.append(CREATED_DATE + " text not null, ");
        sb.append(THUMB_IMAGE_URL + " text, ");
        sb.append(NORMAL_IMAGE_URL + " text, ");
        sb.append(CATEGORY + " text not null);");

        db.execSQL(sb.toString());

    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        StoriesTable.onCreate(db);

    }


}
