package com.example.carrental.model;

import java.util.ArrayList;
import java.util.List;

public class VehicleResponse {
    //private Vehicle[] data;
    private String message;
    private List<Vehicle> data;

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Vehicle> getData() {
        return data;
    }

    public void setData(List<Vehicle> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
