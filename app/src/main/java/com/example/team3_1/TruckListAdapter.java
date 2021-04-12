package com.example.team3_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TruckListAdapter extends RecyclerView.Adapter<TruckListAdapter.TruckViewHolder> {
    private ArrayList<TruckItem> mTruckList;

    public static class TruckViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public ImageView mMoreOptions;
        public TextView mTask;
        public TextView mETA;
        public ImageView mCurrentTaskIcon;
        public ImageView mLocationIcon;
        public Button mMapButton;
        public Button mContactButton;
        public Button mNewTaskButton;

        public TruckViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name);
            mMoreOptions = itemView.findViewById(R.id.more_options);
            mTask = itemView.findViewById(R.id.task);
            mETA = itemView.findViewById(R.id.eta);
            mCurrentTaskIcon = itemView.findViewById(R.id.current_task_icon);
            mLocationIcon = itemView.findViewById(R.id.location_icon);
            mMapButton = itemView.findViewById(R.id.map_button);
            mContactButton = itemView.findViewById(R.id.contact_button);
            mNewTaskButton = itemView.findViewById(R.id.new_task_button);
        }
    }

    public TruckListAdapter(ArrayList<TruckItem> truckList) {
        mTruckList = truckList;
    }

    @NonNull
    @Override
    public TruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.truck_item, parent, false);
        TruckViewHolder evh = new TruckViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull TruckViewHolder holder, int position) {
        TruckItem currentItem = mTruckList.get(position);

        holder.mName.setText(currentItem.getName());
        holder.mMoreOptions.setImageResource(currentItem.getMoreOptions());
        holder.mTask.setText(currentItem.getTask());
        holder.mETA.setText(currentItem.getETA());
        holder.mCurrentTaskIcon.setImageResource(currentItem.getCurrentTaskIcon());
        holder.mLocationIcon.setImageResource(currentItem.getLocationIcon());
        holder.mMapButton.setText(currentItem.getMapButton());
        holder.mContactButton.setText(currentItem.getContactButton());
        holder.mNewTaskButton.setText(currentItem.getNewTaskButton());

    }

    @Override
    public int getItemCount() {

        return mTruckList.size();
    }
}
