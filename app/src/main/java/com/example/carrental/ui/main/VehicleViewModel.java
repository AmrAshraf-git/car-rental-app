package com.example.carrental.ui.main;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carrental.data.SingletonApiClient;
import com.example.carrental.model.VehicleBridge;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleViewModel extends ViewModel {
    public MutableLiveData<VehicleBridge> mutableLiveData=new MutableLiveData<>();

    public void getVehicles(View view){
        //======================================PARSE DATA======================================
        SingletonApiClient.getSingletonClient().getJsonModel().enqueue(new Callback<VehicleBridge>() {
            @Override
            public void onResponse(Call<VehicleBridge> call, Response<VehicleBridge> response) {
                if (!response.isSuccessful() || response.body()==null) {
                    Toast.makeText(view.getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VehicleBridge> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        //======================================PARSE DATA======================================
    }
}
