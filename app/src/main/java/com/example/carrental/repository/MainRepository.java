package com.example.carrental.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.carrental.data.ApiClient;
import com.example.carrental.data.ApiService;
import com.example.carrental.model.Booking;
import com.example.carrental.model.BookingResponse;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.VehicleResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {


    private static MainRepository instance;
    private final ApiService apiService;
    private final MutableLiveData<VehicleResponse> vehicleMutableLiveData;
    private final MutableLiveData<okhttp3.Response> newUserMutableLiveDataResponse;
    //private final MutableLiveData<SignInResponse> userMutableLiveDataResponse;


    private MainRepository(){
        vehicleMutableLiveData =new MutableLiveData<>();
        newUserMutableLiveDataResponse=new MutableLiveData<>();
        //userMutableLiveDataResponse=new MutableLiveData<>();
        apiService=ApiClient.getInstance().getApiService();
    }

    public static synchronized MainRepository getInstance(){
        if(instance==null)
            instance=new MainRepository();
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
    //public LiveData<List<Vehicle>> getVehicles() {
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
    //return apiClient.getVehiclesResponse();
    //}

    /*
    public void signUp(String fName, String lName, String email,
                       String pass, String cPass, int phone){
        apiClient.signUpResponseRequest(fName,lName,email,pass,cPass,phone);
    }*/

    //public void signUp(NewUser newUser){
        //apiClient.signUpResponseRequest(newUser);
    //}

    //public LiveData<ResponseBody> getNewUserResponse(){
        //return apiClient.getNewUserMutableLiveData();
    //}


    public LiveData<VehicleResponse> getVehiclesResponse() {
        //======================================PARSE DATA======================================
        Call<VehicleResponse> call=apiService.getJsonModel();
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(@NonNull Call<VehicleResponse> call, @NonNull Response<VehicleResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("onResponseError", String.valueOf(response.code()));
                    vehicleMutableLiveData.postValue(null);
                    //Toast.makeText(mApplication.getApplicationContext(), "Can't reach to the server, please try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                //List<Vehicle> list = new ArrayList<>(((VehicleResponse) response.body()).getData());
                vehicleMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<VehicleResponse> call, @NonNull Throwable t) {
                vehicleMutableLiveData.setValue(null);
                t.printStackTrace();
                //Toast.makeText(mApplication.getApplicationContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
            }
        });
        //======================================PARSE DATA======================================
        return vehicleMutableLiveData;
    }

    //public MutableLiveData<ResponseBody> getNewUserMutableLiveData() {
        //return newUserMutableLiveDataResponse;
    //}

    public LiveData<okhttp3.Response> signUpResponse(NewUser newUser) {
        Call<ResponseBody> call=apiService.signUp(newUser);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                //if (!response.isSuccessful()) {
                    //Log.e("SignUponResponseError", String.valueOf(response.raw()));
                    //newUserMutableLiveDataResponse.setValue(null);
                    //Toast.makeText(mApplication.getApplicationContext(), "Can't reach to the server, please try again " + "Message: "+response.message()+"  "+"Code: "+response.code(), Toast.LENGTH_LONG).show();
                    //return;
                //}
                newUserMutableLiveDataResponse.postValue(response.raw());
                Log.e("SignUponResponseError", response.errorBody() != null ? response.errorBody().toString():"null");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e("SignUponFailure", String.valueOf(t.getMessage()));
                t.printStackTrace();
                newUserMutableLiveDataResponse.postValue(null);
                //Toast.makeText(mApplication.getApplicationContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
            }
        });
        return newUserMutableLiveDataResponse;
    }


    /*public LiveData<SignInResponse> signInResponse(User user) {
        Call<SignInResponse> call=apiService.signIn(user);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("onResponseError", String.valueOf(response.code()));
                    userMutableLiveDataResponse.postValue(null);
                    //Toast.makeText(mApplication.getApplicationContext(), "Can't reach to the server, please try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                userMutableLiveDataResponse.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                userMutableLiveDataResponse.postValue(null);
                t.printStackTrace();
            }
        });
        return userMutableLiveDataResponse;
    }*/

    public void remoteSignIn(User user,LoginResponse loginResponse) {
        Call<SignInResponse> call=apiService.signIn(user);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                if(response.body()==null)
                {
                    loginResponse.onFailed(new Throwable(String.valueOf(response.code())));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("resTest",String.valueOf(response.body()));
                    loginResponse.onResponse(response.body());
                }
                else{
                    loginResponse.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+" Code: "+response.code()));
                }
            }
            @Override
            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                loginResponse.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteBookingResponse(Booking booking, BookingResponse bookingResponse){
        Call<com.example.carrental.model.BookingResponse> call = apiService.RentRequest(booking);
        call.enqueue(new Callback<com.example.carrental.model.BookingResponse>() {
            @Override
            public void onResponse(Call<com.example.carrental.model.BookingResponse> call, Response<com.example.carrental.model.BookingResponse> response) {

                if(response.body()==null)
                {
                    bookingResponse.onFailed(new Throwable(String.valueOf(response.code())));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    bookingResponse.onResponse(response.body());
                }

                else{
                    bookingResponse.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+" Code: "+response.code()));
                }
            }

            @Override
            public void onFailure(Call<com.example.carrental.model.BookingResponse> call, Throwable t) {
                bookingResponse.onFailed(t);
                t.printStackTrace();
            }
        });


    }




    public interface LoginResponse{
        void onResponse(SignInResponse signInResponse);
        void onFailed(Throwable throwable);
    }

    public interface BookingResponse{
        void onResponse(com.example.carrental.model.BookingResponse bookingResponse);
        void onFailed(Throwable throwable);
    }




}
