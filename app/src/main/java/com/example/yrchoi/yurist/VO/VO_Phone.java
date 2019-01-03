package com.example.yrchoi.yurist.VO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gbkim on 2018-01-11.
 */

public class VO_Phone implements Parcelable {

    private String name;

    protected VO_Phone(Parcel in) {
        name = in.readString();
    }

    public VO_Phone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VO_Phone> CREATOR = new Creator<VO_Phone>() {
        @Override
        public VO_Phone createFromParcel(Parcel in) {
            return new VO_Phone(in);
        }

        @Override
        public VO_Phone[] newArray(int size) {
            return new VO_Phone[size];
        }
    };



}
