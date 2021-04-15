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

    public TruckItem(int moreOptions, String task, String eta, int currentTaskIcon, int locationIcon, String mapButton, String contactButton, String newTaskButton)
    {
        mName = formActivity.getName(n);
        mMoreOptions = moreOptions;
        mTask = formActivity.getTask(cl);
        mETA = eta;
        mCurrentTaskIcon = currentTaskIcon;
        mLocationIcon = locationIcon;
        mMapButton = mapButton;
        mContactButton = contactButton;
        mNewTaskButton = newTaskButton;

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
