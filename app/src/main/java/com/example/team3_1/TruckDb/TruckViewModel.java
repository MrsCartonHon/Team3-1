package com.example.team3_1.TruckDb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TruckViewModel extends AndroidViewModel {

    private TruckRepository mRepository;
    private LiveData<List<Truck>> mAllTrucks;
    private List<Truck> mDropTrucks;

    public TruckViewModel (Application application) {
        super(application);
        mRepository = new TruckRepository(application);
        mAllTrucks = mRepository.getAllTrucks();

    }

    public LiveData<List<Truck>> getAllTrucks() { return mAllTrucks; }

    /*public List<Truck> getDropTrucks() {
        mDropTrucks = mRepository.getDropTrucks();
        return mDropTrucks;
    }*/

    public void insert(Truck truck) {
        mRepository.insert(truck);
    }

    public void deleteTruck(Truck truck){mRepository.deleteTruck(truck);}
}
