package study.cc15.test.datastructures;

import android.os.Parcel;
import android.os.Parcelable;

public class Saved implements Parcelable {

    private int index;
    private String name;
    private String duration;
    private String departureTime; // departure_datetime
    private String arrivalTime; // arrival_datetime

    public static final Parcelable.Creator<Saved> CREATOR = new Parcelable.Creator<Saved>() {
        public Saved createFromParcel(Parcel in) {
            return new Saved(in);
        }

        public Saved[] newArray(int size) {
            return new Saved[size];
        }
    };

    private Saved(Parcel in) {
        name = in.readString();
        duration = in.readString();
        departureTime = in.readString();
        arrivalTime = in.readString();
    }

    public Saved() {
    }

    public Saved(String name, String duration, String departureTime, String arrivalTime) {
        this.name = name;
        this.duration = duration;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(duration);
        parcel.writeString(departureTime);
        parcel.writeString(arrivalTime);
    }
}
