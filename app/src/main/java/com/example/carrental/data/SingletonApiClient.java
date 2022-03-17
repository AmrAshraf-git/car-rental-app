package com.example.carrental.data;

import com.example.carrental.model.VehicleBridge;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonApiClient {
    private static SingletonApiClient singletonApiClient;
    private final String BASE_URL="https://car-rental-eg.herokuapp.com/";
    private final ApiService apiService;


    public SingletonApiClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(ApiService.class);

    }

    public static synchronized SingletonApiClient getSingletonClient() {
        if (singletonApiClient == null) {
            singletonApiClient = new SingletonApiClient();
        }
        return singletonApiClient;
    }


    public Call<VehicleBridge> getJsonModel(){
        return apiService.getJsonModel();
    }


}
