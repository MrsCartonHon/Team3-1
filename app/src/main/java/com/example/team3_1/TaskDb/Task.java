package com.example.team3_1.TaskDb;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @Nullable
    @ColumnInfo(name = "TruckID")
    private int truckId;


    public Task(String name, int truckId) {
        this.name = name;
        this.truckId = truckId;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getTruckId() {return truckId;}

    public void setName(String name) {
        this.name = name;
    }

    public void setTruckId(int truckId) { this.truckId = truckId; }

    public void setId(int id){
        this.id = id;
    }

}
