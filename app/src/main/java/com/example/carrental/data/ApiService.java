package com.example.carrental.data;

import com.example.carrental.model.VehicleBridge;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("getAllVehicle")
    Call<VehicleBridge> getJsonModel();
}
