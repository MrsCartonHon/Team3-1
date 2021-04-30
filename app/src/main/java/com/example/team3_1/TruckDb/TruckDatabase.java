package com.example.team3_1.TruckDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Truck.class}, version = 3,  exportSchema = false)
public abstract class TruckDatabase extends RoomDatabase {

    public abstract TruckDao truckDao();

    private static TruckDatabase INSTANCE;

    public static TruckDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TruckDatabase.class, "Truck_Database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback
            = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final TruckDao mDao;

        PopulateDbAsync(TruckDatabase db) {
            mDao = db.truckDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}