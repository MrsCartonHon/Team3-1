package com.example.team3_1.TaskDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.team3_1.TruckDb.Truck;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    @Insert
    void insertTask(Task task);

    @Delete
    void delete(Task task);

    /*@Query("SELECT * FROM truck_table INNER JOIN TASK_TABLE ON id = TruckID")
    LiveData<List<Truck>> getTruckTask();*/

}
