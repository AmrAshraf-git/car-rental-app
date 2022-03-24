package com.example.carrental.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carrental.constant.Credentials;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.model.Vehicle;
import com.example.carrental.model.VehicleResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ApiClient instance;
    private final ApiService apiService;
    private static Retrofit retrofit;
    private final MutableLiveData<List<Vehicle>> mutableLiveData;


    private ApiClient(){


        retrofit = new Retrofit.Builder().baseUrl(Credentials.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        apiService = retrofit.create(ApiService.class);
        mutableLiveData=new MutableLiveData<>();

    }

    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }


    /*
    public Call<VehicleResponse> getJsonModel(){
        return apiService.getJsonModel();
    }*/

    public Retrofit retrofitRequest(){
        return retrofit;
    }


    public LiveData<List<Vehicle>> getVehiclesResponse() {
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
        return mutableLiveData;
    }

    public void signUpResponseRequest(String fName,String lName, String email,
                                                      String pass,String cPass, int phone)
    {
        apiService.signUp(fName,lName,email,pass,cPass,phone).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    //Log.e("onResponseError", String.valueOf(response.body().getErr()));
                    //Log.e("onResponseError", String.valueOf(response.body().getValidateError()));
                    Log.e("onResponseError", String.valueOf(response.code()));
                    Log.e("onResponseError", String.valueOf(response.body()));
                    //return;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


}
