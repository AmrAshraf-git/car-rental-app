package com.example.carrental.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.carrental.model.NewUser;
import com.example.carrental.model.Vehicle;
import com.example.carrental.repository.VehicleRepo;

import java.util.List;

import okhttp3.ResponseBody;

public class VehicleViewModel extends ViewModel {

    //Live data
    //private final MutableLiveData<VehicleResponse> mutableLiveData=new MutableLiveData<>();
    //private final LiveData<List<Vehicle>> mutableLiveData;;
    private final VehicleRepo vehicleRepo;

    public VehicleViewModel() {
        vehicleRepo = VehicleRepo.getInstance();
        //mutableLiveData=vehicleRepo.getVehicles();
    }



    /*
    public LiveData<VehicleResponse> getVehicles(View view){
        //======================================PARSE DATA======================================
        ApiClient.getInstance().getJsonModel().enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if (!response.isSuccessful() || response.body()==null) {
                    Toast.makeText(view.getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                    return;
                }
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        //======================================PARSE DATA======================================
    return mutableLiveData;
    }*/

    //Get The data from Repository
    public LiveData<List<Vehicle>> getVehicle() {
        //return mutableLiveData;
        return vehicleRepo.getVehicles();
    }

    public void signUp(NewUser newUser) {
        vehicleRepo.signUp(newUser);
    }

    public LiveData<ResponseBody> getNewUserResponse() {
        return vehicleRepo.getNewUserResponse();

    }
}
