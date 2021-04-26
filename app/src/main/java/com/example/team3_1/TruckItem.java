package com.example.team3_1;

import android.widget.ImageView;

public class TruckItem extends formActivity {
    private String mName;
    private int mMoreOptions;
    private String mTask;
    private String mETA;
    private int mCurrentTaskIcon;
    private int mLocationIcon;
    private String mMapButton;
    private String mContactButton;
    private String mNewTaskButton;

    public TruckItem(String name, String task)
    {
        mName = name;
        mTask = task;

    }

    public String getName() {return mName;}
    public int getMoreOptions() {return mMoreOptions;}
    public String getTask() {return mTask;}
    public String getETA() {return mETA;}
    public int getCurrentTaskIcon() {return mCurrentTaskIcon;}
    public int getLocationIcon() {return mLocationIcon;}
    public String getMapButton() {return mMapButton;}
    public String getContactButton() {return mContactButton;}
    public String getNewTaskButton() {return mNewTaskButton;}
}
