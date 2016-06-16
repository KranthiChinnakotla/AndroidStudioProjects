package com.inclass.raja.homework5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NavUtils;

/**
 * Created by Prathyusha on 3/17/16.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    static final int DB_VERSION = 1;
    String db_Name;

    public DatabaseOpenHelper(Context context, String name) {
        super(context, name, null, DB_VERSION);
        this.db_Name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        if (db_Name.equals("citynotes")) {
            NotesTable.onCreate(db);
        } else if (db_Name.equals("cities")) {
            CityTable.onCreate(db);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (db_Name.contains("citynotes")) {
            NotesTable.onUpgrade(db, oldVersion, newVersion);
        } else if (db_Name.contains("cities")) {
            CityTable.onUpgrade(db, oldVersion, newVersion);
        }

    }
}
