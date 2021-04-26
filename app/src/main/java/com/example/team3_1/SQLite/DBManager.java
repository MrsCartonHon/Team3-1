/*
package com.example.team3_1.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

import com.example.team3_1.R;
import com.example.team3_1.TruckItem;
import com.example.team3_1.TruckListAdapter;
import com.example.team3_1.ui.TruckFragment;

import java.util.ArrayList;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    TruckFragment tF = new TruckFragment();

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String task) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.TASK, task);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);

    }

    public int update(long _id, String name, String task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.TASK, task);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete (long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + " = " + _id, null);
    }
    public ArrayList<TruckItem> setUpTrucks() {
        ArrayList<TruckItem> truckList = new ArrayList<>();
        int i = 0;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String q = String.format("SELECT * FROM " + DatabaseHelper.TABLE_NAME);
        Cursor c = db.rawQuery(q, null);
        if(c.moveToFirst()) {
            do {
                Log.v("Working", "loop");
                int p = Integer.parseInt(c.getString(0));
                truckList.add(p-1, new TruckItem(c.getString(1), R.drawable.more_options_icon, c.getString(2), "N/A", R.drawable.current_task_icon, R.drawable.location_icon, "Map", "Contact", "New Task"));
            }
            while (c.moveToNext());
        }
        return truckList;
    }
    public long getRowCount() {
        long count = DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE_NAME);
        return count;
    }
}
*/
