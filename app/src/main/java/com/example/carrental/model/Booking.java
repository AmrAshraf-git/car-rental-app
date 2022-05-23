package com.example.carrental.model;


public class Booking {

    //private String token;
    private String Pick_upLocation;
    private String return_Location;
    private String DateFrom;
    private String DateTo;
    private String VehicleID;


    public String getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.VehicleID = vehicleID;
    }

    //public String getToken() {
        //return token;
    //}

    //public void setToken(String token) {
        //this.token = token;
    //}

    public String getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.DateFrom = dateFrom;
    }

    public String getDateTo() {
        return DateTo;
    }

    public void setDateTo(String dateTo) {
        this.DateTo = dateTo;
    }

    public String getPick_upLocation() {
        return Pick_upLocation;
    }

    public void setPick_upLocation(String pick_upLocation) {
        this.Pick_upLocation = pick_upLocation;
    }

    public String getReturn_Location() {
        return return_Location;
    }

    public void setReturn_Location(String return_Location) {
        this.return_Location = return_Location;
    }

}
