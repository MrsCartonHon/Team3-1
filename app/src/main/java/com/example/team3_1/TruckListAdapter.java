package com.example.team3_1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Icon;
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
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.team3_1.TruckDb.Truck;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class TruckListAdapter extends RecyclerView.Adapter<TruckListAdapter.TruckViewHolder> {
    private List<Truck> mTrucks;

    public interface OnTruckDelete{
        void deleteTruck(Truck truck);
    }

    private OnTruckDelete mCallback;

    public static class TruckViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public ImageView mMoreOptions;
        public TextView mTask;
        public TextView mETA;
        public ImageView mCurrentTaskIcon;
        public ImageView mLocationIcon;
        public MaterialButton mMapButton;
        public MaterialButton mContactButton;
        public MaterialButton mNewTaskButton;

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

    public TruckListAdapter(List<Truck> truckList, OnTruckDelete callback) {
        mTrucks = truckList;
        mCallback = callback;
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
        Truck currentItem = mTrucks.get(position);
        String nameText = currentItem.getName();
        String taskText = currentItem.getTask();
        String phoneNumber = currentItem.getPhoneNumber();
        int color = currentItem.getColor();

        holder.mName.setText(nameText);
        holder.mName.setTextColor(color);
        holder.mTask.setText(taskText);
        holder.mMoreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return moreOptionsTruck(item, currentItem);
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.edit_truck, popup.getMenu());
                popup.show();
            }
        });
        holder.mContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameView = (TextView)view.findViewById(R.id.name);
                Intent intent = new Intent(view.getContext(), contactActivity.class);
                intent.putExtra("driver_name", nameText);
                intent.putExtra("phone_number", phoneNumber);
                view.getContext().startActivity(intent);
            }
        });
        //holder.mMoreOptions.setImageResource(currentItem.getMoreOptions());
        //holder.mETA.setText(currentItem.getETA());
        holder.mCurrentTaskIcon.setImageTintList(ColorStateList.valueOf(color));
        holder.mLocationIcon.setImageTintList(ColorStateList.valueOf(color));
        holder.mMapButton.setTextColor(color);
        holder.mMapButton.setIconTint(ColorStateList.valueOf(color));
        holder.mContactButton.setTextColor(color);
        holder.mContactButton.setIconTint(ColorStateList.valueOf(color));
        holder.mNewTaskButton.setTextColor(color);
        holder.mNewTaskButton.setIconTint(ColorStateList.valueOf(color));


    }
  
    public boolean moreOptionsTruck(MenuItem item, Truck truck) {
        switch (item.getItemId()) {
            case R.id.delete_truck:
                mCallback.deleteTruck(truck);
                return true;
            default:
                return true;
        }
    }

    @Override
    public int getItemCount() {
        return mTrucks.size();
    }

    public void setTrucks(List<Truck> trucks){
        mTrucks = trucks;
        notifyDataSetChanged();
    }
}
