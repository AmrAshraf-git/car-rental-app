package com.example.carrental.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.VehicleResponse;
import com.example.carrental.repository.MainRepository;

public class VehicleViewModel extends ViewModel {

    //Live data
    //private final MutableLiveData<VehicleResponse> mutableLiveData=new MutableLiveData<>();
    //private final LiveData<VehicleResponse> mutableLiveData;
    //private final MutableLiveData<ResponseBody> newUserMutableLiveDataResponse;
    private final MutableLiveData<SignInResponse> userMutableLiveDataResponse;
    //private final MutableLiveData<String> toastResponse;
    private final MainRepository mainRepository;

    public VehicleViewModel() {
        mainRepository = MainRepository.getInstance();
        userMutableLiveDataResponse=new MutableLiveData<>();
        //toastResponse=new MutableLiveData<>("");
        //mutableLiveData=vehicleRepo.getVehiclesResponse();
        //newUserMutableLiveDataResponse=new MutableLiveData<>();
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
    public LiveData<VehicleResponse> getVehicle() {
        //return mutableLiveData;
        return mainRepository.getVehiclesResponse();
    }



    //public void signUp(NewUser newUser) {
        //vehicleRepo.signUp(newUser);
    //}

    public LiveData<okhttp3.Response> getNewUserResponse(NewUser newUser) {
        return mainRepository.signUpResponse(newUser);
    }

    /*public LiveData<SignInResponse> getUserResponse(User user) {
        return mainRepository.remoteSignIn(user);
    }*/
    public LiveData<SignInResponse> getUserResponse() {
        return userMutableLiveDataResponse;
    }

    public void login(User user){
        mainRepository.remoteSignIn(user, new MainRepository.LoginResponse() {
            @Override
            public void onResponse(SignInResponse signInResponse) {
                userMutableLiveDataResponse.postValue(signInResponse);
                //toastResponse.postValue(signInResponse.getMessage());
            }

            @Override
            public void onFailed(Throwable throwable) {
                SignInResponse mSignInResponse=new SignInResponse();
                mSignInResponse.setMessage(throwable.getLocalizedMessage());
                userMutableLiveDataResponse.postValue(mSignInResponse);
                //toastResponse.postValue(throwable.getLocalizedMessage());
            }
        });
    }
}
