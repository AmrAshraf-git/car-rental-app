package com.example.carrental.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carrental.model.Booking;
import com.example.carrental.model.BookingHistoryResponse;
import com.example.carrental.model.BookingResponse;
import com.example.carrental.model.ForgetPasswordResponse;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.OnApiResponse;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.VehicleResponse;
import com.example.carrental.repository.MainRepository;

public class VehicleViewModel extends ViewModel {

    private final MainRepository mainRepository;
    //Live data
    /*
    //private final LiveData<VehicleResponse> mutableLiveData;
    //private final MutableLiveData<String> toastResponse;
    private final MutableLiveData<VehicleResponse> vehicleLiveDataResponse;
    private final MutableLiveData<VehicleResponse> vehicleSearchLiveDataResponse;
    private final MutableLiveData<SignInResponse> userLiveDataResponse;
    private final MutableLiveData<SignUpResponse> newUserLiveDataResponse;
    private final MutableLiveData<BookingResponse> bookingLiveDataResponse;
    private final MutableLiveData<ForgetPasswordResponse> forgetPasswordLiveDataResponse;
    private final MutableLiveData<ForgetPasswordResponse> updateVehicleRateResponse;
    private final MutableLiveData<ForgetPasswordResponse> updateCompanyRateResponse;
    private final MutableLiveData<ForgetPasswordResponse> addToFavoriteResponse;
    private final MutableLiveData<BookingHistoryResponse> bookingHistoryLiveDataResponse;
    private final MutableLiveData<BookingHistoryResponse> availableRateLiveDataResponse;
    private final MutableLiveData<VehicleResponse> favoriteListLiveDataResponse;
    */

    private MutableLiveData<VehicleResponse> vehicleLiveDataResponse;
    private MutableLiveData<VehicleResponse> vehicleSearchLiveDataResponse;
    private MutableLiveData<SignInResponse> userLiveDataResponse;
    private MutableLiveData<SignUpResponse> newUserLiveDataResponse;
    private MutableLiveData<BookingResponse> bookingLiveDataResponse;
    private MutableLiveData<ForgetPasswordResponse> forgetPasswordLiveDataResponse;
    private MutableLiveData<ForgetPasswordResponse> updateVehicleRateResponse;
    private MutableLiveData<ForgetPasswordResponse> updateCompanyRateResponse;
    private MutableLiveData<ForgetPasswordResponse> addToFavoriteResponse;
    private MutableLiveData<BookingHistoryResponse> bookingHistoryLiveDataResponse;
    private MutableLiveData<BookingHistoryResponse> availableRateLiveDataResponse;
    private MutableLiveData<VehicleResponse> favoriteListLiveDataResponse;


    public VehicleViewModel() {
        mainRepository = MainRepository.getInstance();
        //toastResponse=new MutableLiveData<>("");
        //mutableLiveData=vehicleRepo.getVehiclesResponse();
        //newUserMutableLiveDataResponse=new MutableLiveData<>();
    }

/*
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
        mainRepository.remoteBooking(token, booking, new MainRepository.OnBookingResponseListener() {
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

    public void history(String id) {
        mainRepository.remoteBookingHistory(id, new MainRepository.OnBookingHistoryResponseListener() {
            @Override
            public void onResponse(BookingHistoryResponse bookingHistoryResponse) {
                bookingHistoryLiveDataResponse.postValue(bookingHistoryResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                BookingHistoryResponse mBookingHistoryResponse = new BookingHistoryResponse();
                mBookingHistoryResponse.setMessage(throwable.getLocalizedMessage());
                bookingHistoryLiveDataResponse.postValue(mBookingHistoryResponse);
            }
        });
    }

    public MutableLiveData<BookingHistoryResponse> getBookingHistory() {
        return bookingHistoryLiveDataResponse;
    }

    public void forgetPassword(String email) {
        mainRepository.remoteForgetPassword(email, new MainRepository.OnForgetPasswordResponseListener() {
            @Override
            public void onResponse(ForgetPasswordResponse forgetPasswordResponse) {
                forgetPasswordLiveDataResponse.postValue(forgetPasswordResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                forgetPasswordLiveDataResponse.postValue(mForgetPasswordResponse);
            }
        });
    }

    public MutableLiveData<ForgetPasswordResponse> getForgetPassword() {
        return forgetPasswordLiveDataResponse;
    }


    public void availableRate(String id) {
        mainRepository.remoteAvailableRate(id, new MainRepository.OnAvailableRateResponseListener() {
            @Override
            public void onResponse(BookingHistoryResponse bookingHistoryResponse) {
                availableRateLiveDataResponse.postValue(bookingHistoryResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                BookingHistoryResponse mAvailableRateResponse = new BookingHistoryResponse();
                mAvailableRateResponse.setMessage(throwable.getLocalizedMessage());
                availableRateLiveDataResponse.postValue(mAvailableRateResponse);
            }
        });
    }

    public MutableLiveData<BookingHistoryResponse> getAvailableRate() {
        return availableRateLiveDataResponse;
    }


    public void updateVehicleRate(String userId, int rate, String vehicleID) {
        mainRepository.remoteUpdateVehicleRate(userId, rate, vehicleID, new MainRepository.OnUpdateVehicleRateResponseListener() {
            @Override
            public void onResponse(ForgetPasswordResponse forgetPasswordResponse) {
                updateVehicleRateResponse.postValue(forgetPasswordResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                updateVehicleRateResponse.postValue(mForgetPasswordResponse);
            }
        });
    }


    public MutableLiveData<ForgetPasswordResponse> getUpdateVehicleRate() {
        return updateVehicleRateResponse;
    }


    public void updateCompanyRate(String userId, int rate, String companyID) {
        mainRepository.remoteUpdateCompanyRate(userId, rate, companyID, new MainRepository.OnUpdateCompanyRateResponseListener() {
            @Override
            public void onResponse(ForgetPasswordResponse forgetPasswordResponse) {
                updateCompanyRateResponse.postValue(forgetPasswordResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                updateCompanyRateResponse.postValue(mForgetPasswordResponse);
            }
        });
    }

    public MutableLiveData<ForgetPasswordResponse> getUpdateCompanyRate() {
        return updateCompanyRateResponse;
    }


    public void favoriteList(String id) {
        mainRepository.remoteFavoriteList(id, new MainRepository.OnVehiclesResponseListener() {
                    @Override
                    public void onResponse(VehicleResponse vehicleResponse) {
                        favoriteListLiveDataResponse.postValue(vehicleResponse);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        VehicleResponse mVehicleResponse = new VehicleResponse();
                        mVehicleResponse.setMessage(throwable.getLocalizedMessage());
                        favoriteListLiveDataResponse.postValue(mVehicleResponse);
                    }
        });
    }

    public MutableLiveData<VehicleResponse> getFavoriteList() {
        return favoriteListLiveDataResponse;
    }

    public void addToFavorite(String userID,boolean like, String vehicleID) {
        mainRepository.addToFavorite(userID, like, vehicleID, new MainRepository.OnForgetPasswordResponseListener() {
            @Override
            public void onResponse(ForgetPasswordResponse forgetPasswordResponse) {
                addToFavoriteResponse.postValue(forgetPasswordResponse);
            }

            @Override
            public void onFailed(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                addToFavoriteResponse.postValue(mForgetPasswordResponse);
            }
        });
    }

    public MutableLiveData<ForgetPasswordResponse> getAddToFavorite() {
        return addToFavoriteResponse;
    }*/

    public void allVehicleRequest(){
        vehicleLiveDataResponse = new MutableLiveData<>();
        //return mutableLiveData;
        //return mainRepository.getVehiclesResponse();
        mainRepository.remoteVehicleData(new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                vehicleLiveDataResponse.postValue((VehicleResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                VehicleResponse mVehicleResponse = new VehicleResponse();
                mVehicleResponse.setMessage(throwable.getLocalizedMessage());
                vehicleLiveDataResponse.postValue(mVehicleResponse);
            }
        });
    }


    public void searchedVehicleRequest(String search) {
        vehicleSearchLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteVehicleSearchData(search, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                VehicleResponse mVehicleResponse = (VehicleResponse) object;
                mVehicleResponse.setSearch(search);
                vehicleSearchLiveDataResponse.postValue(mVehicleResponse);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                VehicleResponse mVehicleResponse = new VehicleResponse();
                mVehicleResponse.setMessage(throwable.getLocalizedMessage());
                vehicleSearchLiveDataResponse.postValue(mVehicleResponse);
            }
        });
    }


    public void bookingRequest(String token, Booking booking) {
        bookingLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteBooking(token, booking, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                bookingLiveDataResponse.postValue((BookingResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                BookingResponse mBookingResponse = new BookingResponse();
                mBookingResponse.setMessage(throwable.getLocalizedMessage());
                bookingLiveDataResponse.postValue(mBookingResponse);
            }
        });
    }


    public void signInRequest(User user) {
        userLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteSignIn(user, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                userLiveDataResponse.postValue((SignInResponse) object);
                //toastResponse.postValue(signInResponse.getMessage());
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                SignInResponse mSignInResponse = new SignInResponse();
                mSignInResponse.setMessage(throwable.getLocalizedMessage());
                userLiveDataResponse.postValue(mSignInResponse);
                //toastResponse.postValue(throwable.getLocalizedMessage());
            }
        });
    }


    public void signUpRequest(NewUser newUser) {
        newUserLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteSignUp(newUser, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                newUserLiveDataResponse.postValue((SignUpResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                SignUpResponse mSignUpResponse = new SignUpResponse();
                mSignUpResponse.setMessage(throwable.getLocalizedMessage());
                newUserLiveDataResponse.postValue(mSignUpResponse);
            }
        });
    }


    public void historyRequest(String id) {
        bookingHistoryLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteBookingHistory(id, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                bookingHistoryLiveDataResponse.postValue((BookingHistoryResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                BookingHistoryResponse mBookingHistoryResponse = new BookingHistoryResponse();
                mBookingHistoryResponse.setMessage(throwable.getLocalizedMessage());
                bookingHistoryLiveDataResponse.postValue(mBookingHistoryResponse);
            }
        });
    }


    public void forgetPasswordRequest(String email) {
        forgetPasswordLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteForgetPassword(email, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                forgetPasswordLiveDataResponse.postValue((ForgetPasswordResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                forgetPasswordLiveDataResponse.postValue(mForgetPasswordResponse);
            }
        });
    }


    public void availableRateRequest(String id) {
        availableRateLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteAvailableRate(id, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                availableRateLiveDataResponse.postValue((BookingHistoryResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                BookingHistoryResponse mAvailableRateResponse = new BookingHistoryResponse();
                mAvailableRateResponse.setMessage(throwable.getLocalizedMessage());
                availableRateLiveDataResponse.postValue(mAvailableRateResponse);
            }
        });
    }


    public void updateVehicleRateRequest(String userId, int rate, String vehicleID) {
        updateVehicleRateResponse = new MutableLiveData<>();
        mainRepository.remoteUpdateVehicleRate(userId, rate, vehicleID, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                updateVehicleRateResponse.postValue((ForgetPasswordResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                updateVehicleRateResponse.postValue(mForgetPasswordResponse);
            }
        });
    }


    public void updateCompanyRateRequest(String userId, int rate, String companyID) {
        updateCompanyRateResponse = new MutableLiveData<>();
        mainRepository.remoteUpdateCompanyRate(userId, rate, companyID, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                updateCompanyRateResponse.postValue((ForgetPasswordResponse)object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                updateCompanyRateResponse.postValue(mForgetPasswordResponse);
            }
        });
    }


    public void favoriteListRequest(String id) {
        favoriteListLiveDataResponse = new MutableLiveData<>();
        mainRepository.remoteFavoriteList(id, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                favoriteListLiveDataResponse.postValue((VehicleResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                VehicleResponse mVehicleResponse = new VehicleResponse();
                mVehicleResponse.setMessage(throwable.getLocalizedMessage());
                favoriteListLiveDataResponse.postValue(mVehicleResponse);
            }
        });
    }


    public void addToFavoriteRequest(String userID, boolean like, String vehicleID) {
        addToFavoriteResponse = new MutableLiveData<>();
        mainRepository.addToFavorite(userID, like, vehicleID, new OnApiResponse() {
            @Override
            public void onResponseListener(Object object) {
                addToFavoriteResponse.postValue((ForgetPasswordResponse) object);
            }

            @Override
            public void onFailureListener(Throwable throwable) {
                ForgetPasswordResponse mForgetPasswordResponse = new ForgetPasswordResponse();
                mForgetPasswordResponse.setMessage(throwable.getLocalizedMessage());
                addToFavoriteResponse.postValue(mForgetPasswordResponse);
            }
        });
    }




    public LiveData<VehicleResponse> getAllVehicleResponse() {
        return vehicleLiveDataResponse;
    }
    public MutableLiveData<BookingResponse> getBookingLiveDataResponse() {
        return bookingLiveDataResponse;
    }
    public LiveData<SignInResponse> getSignInResponse() {
        return userLiveDataResponse;
    }
    public LiveData<SignUpResponse> getSignUpResponse() {
        return newUserLiveDataResponse;
    }
    public MutableLiveData<BookingHistoryResponse> getBookingHistoryResponse() {
        return bookingHistoryLiveDataResponse;}
    public MutableLiveData<ForgetPasswordResponse> getForgetPasswordResponse() {
        return forgetPasswordLiveDataResponse;
    }
    public MutableLiveData<BookingHistoryResponse> getAvailableRateResponse() {
        return availableRateLiveDataResponse;
    }
    public MutableLiveData<ForgetPasswordResponse> getUpdateVehicleRateResponse() {
        return updateVehicleRateResponse;
    }
    public MutableLiveData<ForgetPasswordResponse> getUpdateCompanyRateResponse() {
        return updateCompanyRateResponse;
    }
    public MutableLiveData<VehicleResponse> getFavoriteListResponse() {
        return favoriteListLiveDataResponse;
    }
    public MutableLiveData<ForgetPasswordResponse> getAddToFavoriteResponse() {
        return addToFavoriteResponse;
    }
    public MutableLiveData<VehicleResponse> getSearchedVehicleResponse() {
        return vehicleSearchLiveDataResponse;
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
