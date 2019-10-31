package com.example.demosqlite.model;

public class Student {
    private int mID;
    private String mName;
    private String mAddress;
    private String mNumber;
    private String mEmail;

    public Student() {
    }

    public Student(int mID, String mName, String mAddress, String mNumber, String mEmail) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mNumber = mNumber;
        this.mEmail = mEmail;
    }

    public Student(String mName, String mAddress, String mNumber, String mEmail) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mNumber = mNumber;
        this.mEmail = mEmail;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;

    }
    @Override
    public String toString() {
        return "Name:" +getmName() + " \n" +"Number: "+getmNumber() +"\n" + "Email: "+getmEmail() +"\n" + "Address:" +getmAddress();
    }
}


