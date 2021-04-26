package com.example.team3_1.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.team3_1.TruckItem;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TruckDao {

    //public ArrayList<TruckItem> mTruckList = null;

    @Query("SELECT * FROM truck_table")
    LiveData<ArrayList<Truck>> getAllTrucks();

    @Insert
    void insertTruck(Truck truck);

    @Delete
    void delete(Truck truck);
}
