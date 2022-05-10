package com.example.carrental.model;

import java.util.List;

public class BookingResponse {
    private String message;
    private List<Booking> Rend;

    public List<Booking> getRend() {
        return Rend;
    }

    public void setRend(List<Booking> rend) {
        Rend = rend;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
