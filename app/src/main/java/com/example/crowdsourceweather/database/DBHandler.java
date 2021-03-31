package com.example.crowdsourceweather.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "Weathers.db";
    public static final int DB_VER = 3;

    public static final String TABLE_NAME = "WeatherReport";
    public static final String COL_ID = "id";
    public static final String COL_LOC = "location";
    public static final String COL_WEATHER = "weatherCondition";
    public static final String COL_TIME = "timeDate";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String USER_TABLE = "CREATE TABLE " + TABLE_NAME +
//                " ( " + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                COL_LOC + " TEXT," +
//                COL_WEATHER + " TEXT," +
//                COL_TIME + " TEXT)";
        String USER_TABLE = "create table WeatherReport " +
                " (id integer primary key autoincrement, location text not null, weatherCondition text not null, timeDate text)";

        db.execSQL(USER_TABLE);
    }

    public boolean addUser(String location, String weather_condition, String time){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(COL_ID, id);
        values.put(COL_LOC,location);
        values.put(COL_WEATHER,weather_condition);
        values.put(COL_TIME,time);
        long sid = db.insert(TABLE_NAME, null, values);

        if(sid>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from WeatherReport", null);
        return cursor;
    }

//    public ArrayList<String> getUsers(){
//        ArrayList<String> list = new ArrayList<>();
//
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_LOC},
//                null, null, null, null, null);
//
//        while(cursor.moveToNext()){
//            list.add(cursor.getString(cursor.getColumnIndex(TABLE_NAME)));
//        }
//
//        return list;
//    }

    public Cursor getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from WeatherReport", null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
