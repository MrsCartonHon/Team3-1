package com.example.team3_1.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class TruckRepository {
    private TruckDao truckDao;
    private LiveData<ArrayList<Truck>> mAllTrucks;

    TruckRepository(Application application) {
        TruckDatabase db = TruckDatabase.getDbInstance(application);
        truckDao = db.truckDao();
        mAllTrucks = truckDao.getAllTrucks();
    }

    LiveData<ArrayList<Truck>> getAllTrucks() {
        return mAllTrucks;
    }

    public void insert (Truck truck) {
        new insertAsyncTask(truckDao).execute(truck);
    }

    private static class insertAsyncTask extends AsyncTask<Truck, Void, Void> {
        private TruckDao mAsyncTaskDao;

        insertAsyncTask(TruckDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Truck... params) {
            mAsyncTaskDao.insertTruck(params[0]);
            return null;
        }
    }
}
