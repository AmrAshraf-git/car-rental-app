package com.example.carrental.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carrental.model.Booking;
import com.example.carrental.model.BookingResponse;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.VehicleResponse;
import com.example.carrental.repository.MainRepository;

public class VehicleViewModel extends ViewModel {

    //Live data
    //private final LiveData<VehicleResponse> mutableLiveData;
    //private final MutableLiveData<String> toastResponse;
    private final MutableLiveData<VehicleResponse> vehicleLiveDataResponse;
    private final MutableLiveData<VehicleResponse> vehicleSearchLiveDataResponse;
    private final MutableLiveData<SignInResponse> userLiveDataResponse;
    private final MutableLiveData<SignUpResponse> newUserLiveDataResponse;
    private final MutableLiveData<BookingResponse> bookingLiveDataResponse;
    private final MainRepository mainRepository;

    public VehicleViewModel() {
        mainRepository = MainRepository.getInstance();
        userLiveDataResponse = new MutableLiveData<>();
        newUserLiveDataResponse = new MutableLiveData<>();
        bookingLiveDataResponse = new MutableLiveData<>();
        vehicleLiveDataResponse = new MutableLiveData<>();
        vehicleSearchLiveDataResponse = new MutableLiveData<>();
        //toastResponse=new MutableLiveData<>("");
        //mutableLiveData=vehicleRepo.getVehiclesResponse();
        //newUserMutableLiveDataResponse=new MutableLiveData<>();
    }


    public void searchedVehicle(String search) {
        mainRepository.remoteVehicleSearchData(search, new MainRepository.OnVehiclesSearchResponseListener() {
                    @Override
                    public void onResponse(VehicleResponse vehicleResponse) {
                        vehicleResponse.setSearch(search);
                        vehicleSearchLiveDataResponse.postValue(vehicleResponse);
                    }
                    @Override
                    public void onFailed(Throwable throwable) {
                        VehicleResponse mVehicleResponse = new VehicleResponse();
                        mVehicleResponse.setMessage(throwable.getLocalizedMessage());
                        vehicleSearchLiveDataResponse.postValue(mVehicleResponse);
                    }
                }
        );
    }

    public MutableLiveData<VehicleResponse> getSearchedVehicle() {
        return vehicleSearchLiveDataResponse;
    }


    public LiveData<VehicleResponse> getVehicle() {
        //return mutableLiveData;
        //return mainRepository.getVehiclesResponse();
        mainRepository.remoteVehicleData(new MainRepository.OnVehiclesResponseListener() {
            @Override
            public void onResponse(VehicleResponse vehicleResponse) {
                vehicleLiveDataResponse.postValue(vehicleResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                VehicleResponse mVehicleResponse = new VehicleResponse();
                mVehicleResponse.setMessage(throwable.getLocalizedMessage());
                vehicleLiveDataResponse.postValue(mVehicleResponse);
            }
        });
        return vehicleLiveDataResponse;
    }

    public void booking(String token, Booking booking) {
        mainRepository.remoteBookingResponse(token, booking, new MainRepository.OnBookingResponseListener() {
            @Override
            public void onResponse(BookingResponse bookingResponse) {
                bookingLiveDataResponse.postValue(bookingResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                BookingResponse mBookingResponse = new BookingResponse();
                mBookingResponse.setMessage(throwable.getLocalizedMessage());
                bookingLiveDataResponse.postValue(mBookingResponse);
            }
        });
    }

    public MutableLiveData<BookingResponse> getBookingLiveDataResponse() {
        return bookingLiveDataResponse;
    }

    public void login(User user) {
        mainRepository.remoteSignIn(user, new MainRepository.OnSignInResponseListener() {
            @Override
            public void onResponse(SignInResponse signInResponse) {
                userLiveDataResponse.postValue(signInResponse);
                //toastResponse.postValue(signInResponse.getMessage());
            }

            @Override
            public void onFailed(Throwable throwable) {
                SignInResponse mSignInResponse = new SignInResponse();
                mSignInResponse.setMessage(throwable.getLocalizedMessage());
                userLiveDataResponse.postValue(mSignInResponse);
                //toastResponse.postValue(throwable.getLocalizedMessage());
            }
        });
    }

    public LiveData<SignInResponse> getUserResponse() {

        return userLiveDataResponse;
    }

    public void signUp(NewUser newUser) {
        mainRepository.remoteSignUp(newUser, new MainRepository.OnSignUpResponseListener() {
            @Override
            public void onResponse(SignUpResponse signUpResponse) {
                newUserLiveDataResponse.postValue(signUpResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                SignUpResponse mSignUpResponse = new SignUpResponse();
                mSignUpResponse.setMessage(throwable.getLocalizedMessage());
                newUserLiveDataResponse.postValue(mSignUpResponse);
            }
        });
    }

    public LiveData<SignUpResponse> getNewUserResponse() {

        return newUserLiveDataResponse;
    }


    /**
     * getVehicles LiveData (old method)
     public LiveData<VehicleResponse> getVehicles(View view){
     //======================================PARSE DATA======================================
     ApiClient.getInstance().getJsonModel().enqueue(new Callback<VehicleResponse>() {
    @Override public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
    if (!response.isSuccessful() || response.body()==null) {
    Toast.makeText(view.getContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
    return;
    }
    mutableLiveData.setValue(response.body());
    }

    @Override public void onFailure(Call<VehicleResponse> call, Throwable t) {
    t.printStackTrace();
    Toast.makeText(view.getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
    }
    });
     //======================================PARSE DATA======================================
     return mutableLiveData;
     }*/

    /** SignUp response return (old method)
     public LiveData<okhttp3.Response> getNewUserResponse(NewUser newUser) {
     return mainRepository.remoteSignUp(newUser);
     }*/

    /**
     * SignInResponse return (old method)
     public LiveData<SignInResponse> getUserResponse(User user) {
     return mainRepository.remoteSignIn(user);
     }*/
}
