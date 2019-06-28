package com.example.hacktrain;

public class NotificationClass {
    String mTitle;
    String mDescription;
    String mName_sender;

    public NotificationClass(){

    }
    public NotificationClass(String title,String description,String name_sender){
        this.mDescription=description;
        this.mName_sender=name_sender;
        this.mTitle=title;

    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmName_sender() {
        return mName_sender;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmName_sender(String mName_sender) {
        this.mName_sender = mName_sender;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
