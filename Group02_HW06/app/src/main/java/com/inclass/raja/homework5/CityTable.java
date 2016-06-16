package com.inclass.raja.homework5;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Prathyusha on 3/17/16.
 */
public class CityTable {

    static final String TABLE_NAME = "city";
    static final String COLUMN_CITYID = "city_id";
    static final String COLUMN_CITYNAME = "cityname";
    static final String COLUMN_CITYSTATE = "citystate";

    static public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+ TABLE_NAME+"(");
        sb.append(COLUMN_CITYID+" integer primary key autoincrement, ");
        sb.append(COLUMN_CITYNAME+" text, ");
        sb.append(COLUMN_CITYSTATE+" text);");
        db.execSQL(sb.toString());

    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        CityTable.onCreate(db);

    }
}
