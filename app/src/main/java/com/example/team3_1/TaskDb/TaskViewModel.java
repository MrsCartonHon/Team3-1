package com.example.team3_1.TaskDb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTask;

    public TaskViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTask = mRepository.getAllTask();
    }

    public LiveData<List<Task>> getAllTask() { return mAllTask; }

    public void insert(Task task) {
        mRepository.insert(task);
    }

    public void deleteTask(Task task){ mRepository.deleteTask(task); }
}
