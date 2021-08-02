package study.cc15.test.datastructures;

import android.os.Parcel;
import android.os.Parcelable;

// TODO: Make it parcelable for "SAVED" tab
public class Route implements Parcelable {
    private String duration;
    // TODO: For now, only one route part will be retrieved
    // NOTE: as routepart only has one information [which is useless], I just removed it
//    private RoutePart routePart;
    private String departureTime; // departure_datetime
    private String arrivalTime; // arrival_datetime

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    private Route(Parcel in) {
        duration = in.readString();
        departureTime = in.readString();
        arrivalTime = in.readString();
    }

    public Route() {
    }

    public Route(String duration, String departureTime, String arrivalTime) {
        this.duration = duration;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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
        parcel.writeString(duration);
        parcel.writeString(departureTime);
        parcel.writeString(arrivalTime);
    }
}
