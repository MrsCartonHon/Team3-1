package com.example.team3_1.TaskDb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTask;
    private int truckId;

    public TaskViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTask = mRepository.getAllTask();

    }

    public LiveData<List<Task>> getAllTask() { return mAllTask; }

    public int getTruckIdTask(String name) {
        return truckId;
    }

    public void insert(Task task) {
        mRepository.insert(task);
    }

    public void deleteTask(Task task){ mRepository.deleteTask(task); }

    public void updateTask(Task task) {
        mRepository.updateTask(task);
    }
}
