package com.example.carrental.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carrental.data.ApiClient;
import com.example.carrental.data.ApiService;
import com.example.carrental.model.Vehicle;
import com.example.carrental.model.VehicleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleRepo {

    private static VehicleRepo instance;
    //private final MutableLiveData<List<Vehicle>> mutableLiveData;
    //private final ApiService apiService;
    private ApiClient apiClient;

    private VehicleRepo(){
        //mutableLiveData=new MutableLiveData<>();
        //apiService= ApiClient.getInstance().retrofitRequest().create(ApiService.class);
        apiClient=ApiClient.getInstance();
    }

    public static VehicleRepo getInstance(){
        if(instance==null)
            instance=new VehicleRepo();
        return instance;
    }


    //Receive data from the remote data source
    /*public LiveData<List<Vehicle>> getVehicles(){
        //======================================PARSE DATA======================================
        apiService.getJsonModel().enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if (!response.isSuccessful() || response.body()==null) {
                    Log.e("onResponseError", String.valueOf(response.code()));
                    return;
                }
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
                t.printStackTrace();
            }
        });
        //======================================PARSE DATA======================================
        return mutableLiveData;
    }*/




    //Get The data from remote DB
    public LiveData<List<Vehicle>> getVehicles() {
        /*
        //======================================PARSE DATA======================================
        apiService.getJsonModel().enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("onResponseError", String.valueOf(response.code()));
                    return;
                }
                List<Vehicle> list = new ArrayList<>(((VehicleResponse) response.body()).getData());
                mutableLiveData.setValue(list);
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
                t.printStackTrace();
            }
        });
        //======================================PARSE DATA======================================
        return mutableLiveData;*/
        return apiClient.getVehiclesResponse();
    }

    public void signUp(String fName, String lName, String email,
                       String pass, String cPass, int phone){
        apiClient.signUpResponseRequest(fName,lName,email,pass,cPass,phone);
    }





}
