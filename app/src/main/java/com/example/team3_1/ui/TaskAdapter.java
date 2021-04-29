package com.example.team3_1.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.R;

import java.util.ArrayList;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ExampleViewHolder> {
    private ArrayList<TaskItem> mExampleList;
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
    public TaskAdapter(ArrayList<TaskItem> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_fragment, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        TaskItem currentItem = mExampleList.get(position);

     //   holder.mTextView1.setText(currentItem.getText1());
     //   holder.mTextView2.setText(currentItem.getText2());
    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
