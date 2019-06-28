package com.example.hacktrain;

import android.graphics.Bitmap;

public class PostClass {
    private String mPost_name;
    private String mPost_description;
    private Bitmap mBitmap;
    private String mDate;

    public PostClass(){

    }
    public PostClass(String mPost_description,String mPost_name,Bitmap mBitmap,String date){
        this.mPost_description=mPost_description;
        this.mPost_name=mPost_name;
        this.mBitmap=mBitmap;
        this.mDate=date;
    }

    public String getmDate() {
        return mDate;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public String getmPost_description() {
        return mPost_description;
    }

    public String getmPost_name() {
        return mPost_name;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public void setmPost_description(String mPost_description) {
        this.mPost_description = mPost_description;
    }

    public void setmPost_name(String mPost_name) {
        this.mPost_name = mPost_name;
    }

    public void setmDate(String date) {
        this.mDate = date;
    }
}
