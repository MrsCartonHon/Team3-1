package com.example.team3_1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.R;
import com.example.team3_1.TaskDb.Task;
import com.example.team3_1.TruckDb.Truck;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ExampleViewHolder> {
    private List<Task> mTasks;
    private OnTaskDelete mCallback;

    public interface OnTaskDelete{
        void deleteTask(Task task);
    }



    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
        }
    }

    public TaskAdapter(List<Task> taskList, OnTaskDelete callback ) {
        mTasks = taskList;
        mCallback = callback;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        Task currentItem = mTasks.get(position);

          holder.mTextView1.setText(currentItem.getName());
          holder.mTextView2.setText(currentItem.getTruckNameTask());
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
