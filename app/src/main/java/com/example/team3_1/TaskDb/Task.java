package com.example.team3_1.TaskDb;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.team3_1.TruckDb.Truck;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TaskId")
    private int id;

    @ColumnInfo(name = "TaskName")
    private String name;

    @ColumnInfo(name = "TruckNameTask")
    private String truckNameTask;


    @Embedded
    private Truck truck;


    public Task(String name, String truckNameTask ) {
        this.name = name;
        this.truckNameTask = truckNameTask;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public Truck getTruck(){return this.truck;}

    public void setTruck(Truck truck){
        this.truck = truck;
    }

    public void setTruckNameTask(String truckNameTask) {
        this.truckNameTask = truckNameTask;
    }

    public String getTruckNameTask() {
        return this.truckNameTask;
    }
}
