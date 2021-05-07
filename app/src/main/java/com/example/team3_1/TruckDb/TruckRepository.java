package com.example.team3_1.TruckDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TruckRepository {
    private TruckDao truckDao;
    private LiveData<List<Truck>> mAllTrucks;
    private List<Truck> mDropTrucks;

    TruckRepository(Application application) {
        TruckDatabase db = TruckDatabase.getDbInstance(application);
        truckDao = db.truckDao();
        mAllTrucks = truckDao.getAllTrucks();
        //mDropTrucks = truckDao.getDropTrucks();
    }

    LiveData<List<Truck>> getAllTrucks() {
        return mAllTrucks;
    }

    /*List<Truck> getDropTrucks() {
        return getDropTrucks();
    }*/

    public void insert (Truck truck) {
        new insertAsyncTask(truckDao).execute(truck);
    }

    public void deleteTruck(Truck truck){
        new deleteAsyncTask(truckDao).execute(truck);
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

    private static class deleteAsyncTask extends AsyncTask<Truck, Void, Void> {
        private TruckDao mAsyncTaskDao;

        deleteAsyncTask(TruckDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Truck... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}