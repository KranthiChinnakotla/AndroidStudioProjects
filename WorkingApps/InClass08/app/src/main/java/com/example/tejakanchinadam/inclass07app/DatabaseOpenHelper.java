package com.example.tejakanchinadam.inclass07app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tejakanchinadam on 3/14/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper{


    static final String DB_NAME = "myStories.db";
    static final int DB_VERSION = 8;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StoriesTable.onCreate(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StoriesTable.onUpgrade(db,oldVersion,newVersion);

    }

}
