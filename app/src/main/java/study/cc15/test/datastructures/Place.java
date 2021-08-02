package study.cc15.test.datastructures;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private String name;
    private String address;
    private String type;
    private double latitude;
    private double longitude;
    private String atcocode;

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    private Place(Parcel in) {
        name = in.readString();
        address = in.readString();
        type = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        atcocode = in.readString();
    }

    public Place() {
    }

    public Place(String name, String address, String type, double latitude, double longitude, String atcocode) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.atcocode = atcocode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAtcocode() {
        return atcocode;
    }

    public void setAtcocode(String atcocode) {
        this.atcocode = atcocode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(type);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(atcocode);
    }

}
