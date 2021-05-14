package com.example.team3_1.TaskDb;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.team3_1.TruckDb.Truck;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Taskid")
    private int id;

    @ColumnInfo(name = "Taskname")
    private String name;

    @ColumnInfo(name = "TruckTaskId")
    private long truckId;


    @Embedded
    private Truck truck;


    public Task(String name, long truckId ) {
        this.name = name;
        this.truckId = truckId;
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

    public void setTruckId(long truckId) {
        this.truckId = truckId;
    }

    public long getTruckId() {
        return this.truckId;
    }
}
