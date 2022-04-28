package com.example.carrental.model;


import java.util.Date;

public class Booking {

    private String vecihelID;
    private String token;
    private Date startDate;
    private Date endDate;
    private Coordinates pickupLocation;
    private Coordinates deliveryLocation;


    public String getVecihelID() {
        return vecihelID;
    }

    public void setVecihelID(String vecihelID) {
        this.vecihelID = vecihelID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Coordinates getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Coordinates pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Coordinates getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Coordinates deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

}
