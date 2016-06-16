package com.inclass.raja.homework5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Prathyusha on 3/17/16.
 */
public class DatabaseDataManager {

    private Context mContext;
    private SQLiteDatabase db;
    private DatabaseOpenHelper dbOpenHelper;
    private CityDao cityDao;
    private NotesDao notesDao;
    private String db_Name;

    public DatabaseDataManager(Context mContext, String db_Name) {
        this.mContext = mContext;
        this.db_Name = db_Name;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext, this.db_Name);
        db = dbOpenHelper.getWritableDatabase();
        cityDao = new CityDao(db);
        notesDao = new NotesDao(db);
    }

    public CityDao getCityDao() {
        return this.cityDao;
    }

    public NotesDao getNotesDao() {
        return this.notesDao;
    }

    public long saveNotes(Notes notes) {
        return notesDao.save(notes);
    }

    public long saveCity(CityDetails city) {
        return cityDao.save(city);
    }

    public boolean updateNotes(Notes notes) {
        return notesDao.update(notes);
    }

    public boolean updateCity(CityDetails city) {
        return cityDao.update(city);
    }

    public boolean deleteNotes(Notes notes) {
        return notesDao.delete(notes);
    }

    public boolean deleteCity(CityDetails city) {
        return cityDao.delete(city);
    }

    public boolean deleteAllNotes() {
        return notesDao.deleteAll();
    }

    public boolean deleteAllCity() {
        return cityDao.deleteAll();
    }

    public Notes getNotes(long city_id) {
        return notesDao.get(city_id);
    }

    public CityDetails getCity(long city_id) {
        return cityDao.get(city_id);
    }

    public List<Notes> getAllNotes() {
        return notesDao.getAll();
    }

    public List<CityDetails> getAllCity() {
        return cityDao.getAll();
    }

    public void settingCityId(CityDetails city){
        cityDao.settingID(city);
    }
}
