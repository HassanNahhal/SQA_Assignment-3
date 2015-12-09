package com.example.hassannahhal.starwars;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String TABLE_TROOPER = "TROOPER_TABLE";
    public static final String TROOPER_ID = "_id";
    public static final String TROOPER_STAR = "TROOPER_STAR";


    static final String DB_NAME = "USER.DB";
    static final int DB_VERSION = 1;

    public static final String SCRIPT = " CREATE TABLE " + TABLE_TROOPER + "( "
            + TROOPER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + TROOPER_STAR
            + " TEXT )";

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL(" DROP TABLE IF EXESTS" + TABLE_TROOPER);
        onCreate(db);

    }

}
