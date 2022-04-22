package com.example.carrental.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carrental.constant.Credentials;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.Vehicle;
import com.example.carrental.model.VehicleResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient instance;
    //private final ApiService apiService;
    private static Retrofit retrofit;
    //private final MutableLiveData<Vehicle> vehicleMutableLiveData;
    //private final   MutableLiveData<ResponseBody> newUserMutableLiveDataResponse;


    private ApiClient(){
        retrofit = new Retrofit.Builder().baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        //apiService = retrofit.create(ApiService.class);
        //vehicleMutableLiveData =new MutableLiveData<>();
        //newUserMutableLiveDataResponse=new MutableLiveData<>();

    }

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    public Retrofit retrofitRequest(){
        return retrofit;
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
        //return apiService;
    }

    /*
    public LiveData<Vehicle> getVehiclesResponse() {
        //======================================PARSE DATA======================================
        apiService.getJsonModel().enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("onResponseError", String.valueOf(response.code()));
                    vehicleMutableLiveData.setValue(null);
                    return;
                }
                List<Vehicle> list = new ArrayList<>(((VehicleResponse) response.body()).getData());
                vehicleMutableLiveData.setValue(list);
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                vehicleMutableLiveData.setValue(null);
                t.printStackTrace();
            }
        });
        //======================================PARSE DATA======================================
        return vehicleMutableLiveData;
    }

    public MutableLiveData<ResponseBody> getNewUserMutableLiveData() {
        return newUserMutableLiveDataResponse;
    }

    public void signUpResponseRequest(NewUser newUser)
    {
        Call<ResponseBody> call=apiService.signUp(newUser);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Log.e("SignUponResponseError", String.valueOf(response.raw()));
                    newUserMutableLiveDataResponse.setValue(null);
                    return;
                }
                newUserMutableLiveDataResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("SignUponFailure", String.valueOf(t.getMessage()));
                t.printStackTrace();
                newUserMutableLiveDataResponse.setValue(null);
            }
        });


    }*/


}
