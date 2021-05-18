package com.example.team3_1.ui;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.R;
import com.example.team3_1.TaskDb.Task;
import com.example.team3_1.TaskDb.TaskViewModel;
import com.example.team3_1.TruckDb.Truck;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ExampleViewHolder> {
    private List<Task> mTasks;
    private OnTaskDelete mCallback;
    private TasksFragment tF;

    public interface OnTaskDelete{
        void deleteTask(Task task);
    }



    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView mComplete;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            mComplete = itemView.findViewById(R.id.complete);
        }
    }

    public TaskAdapter(List<Task> taskList, OnTaskDelete callback ) {
        mTasks = taskList;
        mCallback = callback;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        tF = new TasksFragment();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Task currentItem = mTasks.get(position);

          holder.mTextView1.setText(currentItem.getName());
          holder.mTextView2.setText(currentItem.getTruckNameTask());
        holder.mComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return completeTask(item, currentItem);
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.complete_task, popup.getMenu());
                popup.show();
            }
        });
    }

    public boolean completeTask(MenuItem item, Task task) {
        switch (item.getItemId()) {
            case R.id.complete_task:
                mCallback.deleteTask(task);
                return true;
            default:
                return true;
        }
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void setTasks(List<Task> tasks){
        mTasks = tasks;
        notifyDataSetChanged();
    }
}
