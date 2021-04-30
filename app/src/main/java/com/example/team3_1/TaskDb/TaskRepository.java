package com.example.team3_1.TaskDb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> mAllTask;

    TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDbInstance(application);
        taskDao = (TaskDao) db.taskDao();
        mAllTask = taskDao.getAllTasks();
    }

    LiveData<List<Task>> getAllTask() {
        return mAllTask;
    }

    public void insert(Task task) {
        new TaskRepository.insertAsyncTask(taskDao).execute(task);
    }

    public void deleteTask(Task task){
        new TaskRepository.deleteAsyncTask(taskDao).execute(task);
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mAsyncTaskDao;

        insertAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.insertTask(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mAsyncTaskDao;

        deleteAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
