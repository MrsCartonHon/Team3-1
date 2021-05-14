package com.example.team3_1.TruckDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TruckRepository {
    private TruckDao truckDao;
    private LiveData<List<Truck>> mAllTrucks;

    TruckRepository(Application application) {
        TruckDatabase db = TruckDatabase.getDbInstance(application);
        truckDao = db.truckDao();
        mAllTrucks = truckDao.getAllTrucks();
    }

    LiveData<List<Truck>> getAllTrucks() {
        return mAllTrucks;
    }

    public void insert (Truck truck) {
        new insertAsyncTask(truckDao).execute(truck);
    }

    public void deleteTruck(Truck truck){
        new deleteAsyncTask(truckDao).execute(truck);
    }

    public void updateTruck(Truck truck){
        new updateAsyncTask(truckDao).execute(truck);
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

    private static class updateAsyncTask extends AsyncTask<Truck, Void, Void> {
        private TruckDao mAsyncTaskDao;

        updateAsyncTask(TruckDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Truck... params) {
            mAsyncTaskDao.updateTruck(params[0]);
            return null;
        }
    }

}