package com.example.hassannahhal.starwars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {

    private DBHelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;
    public static final String TABLE_TROOPER = "TROOPER_TABLE";


    public SQLController(Context C) {
        ourcontext = C;
    }

    public SQLController open() throws SQLException {
        dbhelper = new DBHelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbhelper.close();
    }

    public void insertData(String star) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.TROOPER_STAR, star);
        database.insert(DBHelper.TABLE_TROOPER, null, cv);

    }

    public Cursor readData() {
        String[] allColumns = new String[]{DBHelper.TROOPER_ID,
                DBHelper.TROOPER_STAR};
        Cursor c = database.query(DBHelper.TABLE_TROOPER, allColumns, null, null,
                null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor readall() {

        Cursor localCursor = this.database.query(DBHelper.TABLE_TROOPER,
                new String[]{
                        DBHelper.TROOPER_ID,
                        DBHelper.TROOPER_STAR}, null,
                null, null, null, null);
        if (localCursor != null)
            localCursor.moveToFirst();
        return localCursor;

    }

    public int getLastId() {
        int lastId = 0;
        Cursor cursor = this.database.rawQuery("SELECT MAX(_ID) from "+TABLE_TROOPER, null);
        if (cursor.moveToLast()) {
            lastId = cursor.getInt(0);
        }

        return lastId;
    }

}
