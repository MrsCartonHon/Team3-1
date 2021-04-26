package com.example.team3_1.data;

import android.app.Application;
import android.widget.ArrayAdapter;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class TruckViewModel extends AndroidViewModel {

    private TruckRepository mRepository;
    private LiveData<ArrayList<Truck>> mAllTrucks;

    public TruckViewModel (Application application) {
        super(application);
        mRepository = new TruckRepository(application);
        mAllTrucks = mRepository.getAllTrucks();
    }

    public LiveData<ArrayList<Truck>> getAllTrucks() { return mAllTrucks; }

    public void insert(Truck truck) {
        mRepository.insert(truck);
    }
}
