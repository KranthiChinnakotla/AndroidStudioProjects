package com.inclass.raja.homework5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathyusha on 3/17/16.
 */
public class CityDao {

    private SQLiteDatabase db;

    public CityDao(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(CityDetails city) {
        ContentValues values = new ContentValues();
        values.put(CityTable.COLUMN_CITYNAME, city.getCityName());
        values.put(CityTable.COLUMN_CITYSTATE, city.getStateName());
        return db.insert(CityTable.TABLE_NAME, null, values);
    }

    public boolean update(CityDetails city) {

        ContentValues values = new ContentValues();
        values.put(CityTable.COLUMN_CITYNAME, city.getCityName());
        values.put(CityTable.COLUMN_CITYSTATE, city.getStateName());
        return db.update(CityTable.TABLE_NAME, values, CityTable.COLUMN_CITYNAME + "=?", new String[]{city.getCityName() + ""}) > 0;

    }

    public boolean delete(CityDetails city) {
        return db.delete(CityTable.TABLE_NAME, CityTable.COLUMN_CITYNAME + "=?", new String[]{city.getCityName() + ""}) > 0;
    }

    public CityDetails get(long cityID) {
        CityDetails city = null;
        Cursor c = db.query(true, CityTable.TABLE_NAME, new String[]{CityTable.COLUMN_CITYID, CityTable.COLUMN_CITYNAME, CityTable.COLUMN_CITYSTATE},
                CityTable.COLUMN_CITYID + "=?", new String[]{cityID + ""}, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            city = buildCityfromCursor(c);
            if (!c.isClosed())
                c.close();
        }
        return city;

    }

    public List<CityDetails> getAll() {
        List<CityDetails> cities = new ArrayList<CityDetails>();
        Cursor c = db.query(true, CityTable.TABLE_NAME, new String[]{CityTable.COLUMN_CITYID, CityTable.COLUMN_CITYNAME, CityTable.COLUMN_CITYSTATE},
                null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {
                CityDetails city = buildCityfromCursor(c);
                if (city != null)
                    cities.add(city);
            } while (c.moveToNext());
        }
        return cities;
    }

    public boolean deleteAll() {
        return db.delete(CityTable.TABLE_NAME, null, null) > 0;
    }

    public void settingID(CityDetails city) {
        Cursor c = db.query(true, CityTable.TABLE_NAME, new String[]{CityTable.COLUMN_CITYID, CityTable.COLUMN_CITYNAME, CityTable.COLUMN_CITYSTATE},
                CityTable.COLUMN_CITYNAME + "=?", new String[]{city.getCityName()}, null, null, null, null);

        if (c != null && c.moveToFirst()) {

            city.setCityID(c.getLong(0));
        }
        if (!c.isClosed())
            c.close();
    }

    private CityDetails buildCityfromCursor(Cursor c) {
        CityDetails city = null;
        if (c != null) {
            city = new CityDetails();
            city.setCityID(c.getLong(0));
            city.setCityName(c.getString(1));
            city.setStateName(c.getString(2));
        }
        return city;
    }


}
