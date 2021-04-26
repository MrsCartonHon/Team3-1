/*
package com.example.team3_1.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.team3_1.TruckItem;
import com.google.android.material.tabs.TabLayout;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "TRUCKS";

    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String TASK = "task";

    static final String DB_NAME = "MRGLOG_TRUCKS.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = String.format("create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + TASK + " TEXT);");

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(String.format("DROP TABLE IF EXISTS " + TABLE_NAME));
        onCreate(db);
    }



}
*/
