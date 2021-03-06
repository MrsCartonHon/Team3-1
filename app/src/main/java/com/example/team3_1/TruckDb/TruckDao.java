package com.example.team3_1.TruckDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TruckDao {

    @Query("SELECT * FROM truck_table")
    LiveData<List<Truck>> getAllTrucks();

    @Insert
    void insertTruck(Truck truck);

    @Delete
    void delete(Truck truck);

    @Update
    void updateTruck(Truck truck);


}
