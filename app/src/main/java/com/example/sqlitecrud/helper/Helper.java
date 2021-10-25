package com.example.sqlitecrud.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.core.view.accessibility.AccessibilityViewCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PrimitiveIterator;
import java.util.SplittableRandom;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "crud";

    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE film (id INTEGER PRIMARY KEY autoincrement, judul TEXT NOT NULL, tahun TEXT NOT NULL, durasi TEXT NOT NULL, pemeran TEXT NOT NULL, sinopsis TEXT NOT NULL)";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS film");
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getAll(){
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM film";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("judul", cursor.getString(1));
                map.put("tahun", cursor.getString(2));
                map.put("durasi", cursor.getString(3));
                map.put("pemeran", cursor.getString(4));
                map.put("sinopsis", cursor.getString(5));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public void insert(String judul, String tahun, String durasi, String pemeran, String sinopsis){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO film (judul, tahun, durasi, pemeran, sinopsis) VALUES('"+judul+"', '"+tahun+"', '"+durasi+"', '"+pemeran+"', '"+sinopsis+"')";
        database.execSQL(QUERY);
    }
    public void update(int id, String judul, String tahun, String durasi, String pemeran, String sinopsis){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE film SET judul = '"+judul+"', tahun = '"+tahun+"', durasi = '"+durasi+"', pemeran = '"+pemeran+"', sinopsis = '"+sinopsis+"' WHERE id = "+id;
        database.execSQL(QUERY);
    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM film WHERE id = "+id;
        database.execSQL(QUERY);
    }
}
