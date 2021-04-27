package com.example.team3_1;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
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
        moreOptionsButton(v);
        contactButton(v, parent);
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

    public void contactButton(View view, ViewGroup parent) {
        view.findViewById(R.id.contact_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(contactActivity.class, view);
            }
        });
    }

    public void moreOptionsButton(View v) {
        v.findViewById(R.id.more_options).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(v.getContext(), v);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return moreOptionsTruck(item);
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.edit_truck, popup.getMenu());
                popup.show();
            }
        });
    }
  
    public boolean moreOptionsTruck(MenuItem item) {
        Log.d("Truck", "Truck" + mTruckList.size());
        switch (item.getItemId()) {
            case R.id.rename_truck:
                Log.d("Rename", "Rename");
                return true;
            case R.id.delete_truck:
                Log.d("DELETE", "DELETE");
                return true;
            default:
                return true;
        }
    }

    @Override
    public int getItemCount() {

        return mTruckList.size();
    }
    private void startNewActivity(Class activity, View v) {
        TextView nameView = (TextView)v.findViewById(R.id.name);
        Intent intent = new Intent(v.getContext(), activity);
        intent.putExtra("driver_name", nameView.getText().toString());
        v.getContext().startActivity(intent);
    }
}
