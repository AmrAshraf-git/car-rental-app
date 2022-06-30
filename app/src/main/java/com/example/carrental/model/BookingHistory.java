package com.example.carrental.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingHistory implements Parcelable {
    private String _id;
    private String UserID;
    private String Pick_upLocation;
    private String return_Location;
    private String DateFrom;
    private String DateTo;
    private String status;
    private Vehicle VehicleID;
    private String companyID;


    protected BookingHistory(Parcel in) {
        _id = in.readString();
        UserID = in.readString();
        Pick_upLocation = in.readString();
        return_Location = in.readString();
        DateFrom = in.readString();
        DateTo = in.readString();
        status = in.readString();
        VehicleID = in.readParcelable(Vehicle.class.getClassLoader());
        companyID = in.readString();
    }

    public static final Creator<BookingHistory> CREATOR = new Creator<BookingHistory>() {
        @Override
        public BookingHistory createFromParcel(Parcel in) {
            return new BookingHistory(in);
        }

        @Override
        public BookingHistory[] newArray(int size) {
            return new BookingHistory[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPick_upLocation() {
        return Pick_upLocation;
    }

    public void setPick_upLocation(String pick_upLocation) {
        Pick_upLocation = pick_upLocation;
    }

    public String getReturn_Location() {
        return return_Location;
    }

    public void setReturn_Location(String return_Location) {
        this.return_Location = return_Location;
    }

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        DateTo = dateTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vehicle getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(Vehicle vehicleID) {
        VehicleID = vehicleID;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(UserID);
        dest.writeString(Pick_upLocation);
        dest.writeString(return_Location);
        dest.writeString(DateFrom);
        dest.writeString(DateTo);
        dest.writeString(status);
        dest.writeParcelable(VehicleID, flags);
        dest.writeString(companyID);
    }
}
