package com.example.carrental.repository;

import android.util.Log;
import androidx.annotation.NonNull;

import com.example.carrental.data.ApiClient;
import com.example.carrental.data.ApiService;
import com.example.carrental.model.Booking;
import com.example.carrental.model.BookingHistoryResponse;
import com.example.carrental.model.BookingResponse;
import com.example.carrental.model.ForgetPasswordResponse;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.VehicleResponse;
import com.example.carrental.model.OnApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private static MainRepository instance;
    private final ApiService apiService;


    private MainRepository(){
        apiService=ApiClient.getInstance().getApiService();
    }
    public static synchronized MainRepository getInstance(){
        if(instance==null)
            instance=new MainRepository();
        return instance;
    }

    /*
    public void remoteVehicleSearchData(String query, OnVehiclesSearchResponseListener onVehiclesSearchResponseListener){
        Call<VehicleResponse> call=apiService.getSearchedItems(query);
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(@NonNull Call<VehicleResponse> call, @NonNull Response<VehicleResponse> response) {
                if(response.body()==null){
                    onVehiclesSearchResponseListener.onFailed(new Throwable("Message: Unreached response"+"Code: "+response.code()));
                    Log.e("VehicleSearchResponse", "null body");
                }
                else if(response.isSuccessful() && response.code()==200){
                    onVehiclesSearchResponseListener.onResponse(response.body());
                    //Log.e("VehicleSearchResponse", "Successful");
                }

                else {
                    onVehiclesSearchResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " + response.message() + " Code: " + response.code()));
                    Log.e("VehicleSearchResponse", "Unexpected error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<VehicleResponse> call, @NonNull Throwable t) {
                onVehiclesSearchResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteVehicleData(OnVehiclesResponseListener onVehiclesResponseListener){
        Call<VehicleResponse> call=apiService.getJsonModel();
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(@NonNull Call<VehicleResponse> call, @NonNull Response<VehicleResponse> response) {
                if (response.body() == null) {
                    Log.e("VehicleResponse", "null body");
                    onVehiclesResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " + response.code()));
                } else if (response.isSuccessful() && response.code() == 200) {
                    //Log.e("VehicleResponse","Successful");
                    onVehiclesResponseListener.onResponse(response.body());
                } else {
                    Log.e("VehicleResponse","Unexpected error");
                    onVehiclesResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " + response.message() + " Code: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<VehicleResponse> call, @NonNull Throwable t) {
                onVehiclesResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteSignUp(NewUser newUser, OnSignUpResponseListener onSignUpResponseListener) {
        Call<SignUpResponse> call=apiService.signUp(newUser);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                if(response.body()==null)
                {
                    Log.e("SignUpResponse","null body");
                    onSignUpResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    Log.e("SignUpResponse","Successful");
                    onSignUpResponseListener.onResponse(response.body());
                }
                else{
                    Log.e("SignUpResponse","Unexpected error");
                    onSignUpResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " + response.message() + (response.body().getValidateError()!=null? response.body().getValidateError()[0]: "  Code: "+response.code()  )));
                }
            }

            @Override
            public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                onSignUpResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteSignIn(User user, OnSignInResponseListener onSignInResponseListener) {
        Call<SignInResponse> call=apiService.signIn(user);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                if(response.body()==null)
                {
                    Log.e("SignInResponse", "null body");
                    if(response.code()==400)
                        onSignInResponseListener.onFailed(new Throwable("400"));
                    else
                        onSignInResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " + response.code()));

                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("SignInResponse","Successful Response");
                    onSignInResponseListener.onResponse(response.body());
                }
                else{
                    Log.e("SignInResponse","Unexpected error");
                    onSignInResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " + response.message() + (response.body().getMessage()!=null? response.body().getMessage(): "  Code: "+response.code())));
                }
            }
            @Override
            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                onSignInResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteBooking(String token, Booking booking, OnBookingResponseListener onBookingResponseListener){
        Call<com.example.carrental.model.BookingResponse> call = apiService.RentRequest(token, booking);
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingResponse> call, @NonNull Response<BookingResponse> response) {

                if(response.body()==null)
                {
                    Log.e("BookingResponse","null body");
                    onBookingResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("BookingResponse","Successful Response");
                    onBookingResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("BookingResponse","Unexpected error");
                    onBookingResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(@NonNull Call<BookingResponse> call, @NonNull Throwable t) {
                onBookingResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteBookingHistory(String userID, OnBookingHistoryResponseListener onBookingHistoryResponseListener){
        Call<BookingHistoryResponse> call=apiService.getHistory(userID);
        call.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingHistoryResponse> call, @NonNull Response<BookingHistoryResponse> response) {
                if(response.body()==null)
                {
                    Log.e("BookingHistoryResponse","null body");
                    onBookingHistoryResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("BookingResponse","Successful Response");
                    onBookingHistoryResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("BookingHistoryResponse","Unexpected error");
                    onBookingHistoryResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(Call<BookingHistoryResponse> call, Throwable t) {
                onBookingHistoryResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteForgetPassword(String email,OnForgetPasswordResponseListener onForgetPasswordResponseListener){
        Call<ForgetPasswordResponse> call= apiService.forgetPassword(email);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if(response.body()==null)
                {
                    Log.e("remoteForgetPassword","null body");
                    onForgetPasswordResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("BookingResponse","Successful Response");
                    onForgetPasswordResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("BookingHistoryResponse","Unexpected error");
                    onForgetPasswordResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                onForgetPasswordResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteAvailableRate(String userID, OnAvailableRateResponseListener onAvailableRateResponseListener){
        Call<BookingHistoryResponse> call=apiService.getAvailableRate(userID);
        call.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingHistoryResponse> call, @NonNull Response<BookingHistoryResponse> response) {
                if(response.body()==null)
                {
                    Log.e("onAvailableRateResponse","null body");
                    onAvailableRateResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("onAvailableRateResponse","Successful Response");
                    onAvailableRateResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("onAvailableRateResponse","Unexpected error");
                    onAvailableRateResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(Call<BookingHistoryResponse> call, Throwable t) {
                onAvailableRateResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteUpdateVehicleRate(String userID, int rate, String vehicleID, OnUpdateVehicleRateResponseListener onUpdateVehicleRateResponseListener){
        Call<ForgetPasswordResponse> call=apiService.updateVehicleRate(userID,rate,vehicleID);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgetPasswordResponse> call, Response<ForgetPasswordResponse> response) {
                if(response.body()==null)
                {
                    Log.e("onUpdateVehicleRateRes","null body");
                    onUpdateVehicleRateResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    Log.e("onUpdateVehicleRateRes","Successful Response");
                    onUpdateVehicleRateResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("onAvailableRateResponse","Unexpected error");
                    onUpdateVehicleRateResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(Call<ForgetPasswordResponse> call, Throwable t) {
                onUpdateVehicleRateResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteUpdateCompanyRate(String userID, int rate, String companyID, OnUpdateCompanyRateResponseListener onUpdateCompanyRateResponseListener){
        Call<ForgetPasswordResponse> call=apiService.updateCompanyRate(userID,rate,companyID);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if(response.body()==null)
                {
                    Log.e("onUpdateVehicleRateRes","null body");
                    onUpdateCompanyRateResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    Log.e("onUpdateVehicleRateRes","Successful Response");
                    onUpdateCompanyRateResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("onAvailableRateResponse","Unexpected error");
                    onUpdateCompanyRateResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                onUpdateCompanyRateResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }


    public void addToFavorite(String userID,boolean like, String vehicleID,OnForgetPasswordResponseListener onForgetPasswordResponseListener){
        Call<ForgetPasswordResponse> call=apiService.AddToFavorite(userID,like,vehicleID);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                if(response.body()==null)
                {
                    Log.e("remoteForgetPassword","null body");
                    onForgetPasswordResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("BookingResponse","Successful Response");
                    onForgetPasswordResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("BookingHistoryResponse","Unexpected error");
                    onForgetPasswordResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                onForgetPasswordResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteFavoriteList(String userID, OnVehiclesResponseListener onVehiclesResponseListener){
        Call<VehicleResponse> call=apiService.getFavorite(userID);
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(Call<VehicleResponse> call, Response<VehicleResponse> response) {
                if(response.body()==null)
                {
                    Log.e("BookingHistoryResponse","null body");
                    onVehiclesResponseListener.onFailed(new Throwable("Message: Unreached response" + "Code: " +response.code()));
                }
                else if (response.isSuccessful() && response.code()==200) {
                    //Log.e("BookingResponse","Successful Response");
                    onVehiclesResponseListener.onResponse(response.body());
                }

                else{
                    Log.e("BookingHistoryResponse","Unexpected error");
                    onVehiclesResponseListener.onFailed(new Throwable("Unexpected error occurred " + "Message: " +response.message()+ (response.body().getMessage()!=null? response.body().getMessage() :" Code: "+response.code())));
                }
            }

            @Override
            public void onFailure(Call<VehicleResponse> call, Throwable t) {
                onVehiclesResponseListener.onFailed(t);
                t.printStackTrace();
            }
        });
    }
    */

/*
    public interface OnForgetPasswordResponseListener {
        void onResponse(ForgetPasswordResponse forgetPasswordResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnSignInResponseListener {
        void onResponse(SignInResponse signInResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnSignUpResponseListener {
        void onResponse(SignUpResponse signUpResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnBookingResponseListener {
        void onResponse(com.example.carrental.model.BookingResponse bookingResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnVehiclesResponseListener {
        void onResponse(VehicleResponse vehicleResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnVehiclesSearchResponseListener{
        void onResponse(VehicleResponse vehicleResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnBookingHistoryResponseListener {
        void onResponse(BookingHistoryResponse bookingHistoryResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnAvailableRateResponseListener {
        void onResponse(BookingHistoryResponse bookingHistoryResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnUpdateVehicleRateResponseListener {
        void onResponse(ForgetPasswordResponse forgetPasswordResponse);
        void onFailed(Throwable throwable);
    }

    public interface OnUpdateCompanyRateResponseListener {
        void onResponse(ForgetPasswordResponse forgetPasswordResponse);
        void onFailed(Throwable throwable);
    }
*/


    public void remoteVehicleSearchData(String query, OnApiResponse onApiResponse){
        Call<VehicleResponse> call=apiService.getSearchedItems(query);
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(@NonNull Call<VehicleResponse> call, @NonNull Response<VehicleResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<VehicleResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteVehicleData(OnApiResponse onApiResponse){
        Call<VehicleResponse> call=apiService.getJsonModel();
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(@NonNull Call<VehicleResponse> call, @NonNull Response<VehicleResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<VehicleResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteSignUp(NewUser newUser, OnApiResponse onApiResponse) {
        Call<SignUpResponse> call=apiService.signUp(newUser);
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignUpResponse> call, @NonNull Response<SignUpResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<SignUpResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteSignIn(User user, OnApiResponse onApiResponse) {
        Call<SignInResponse> call=apiService.signIn(user);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }
            @Override
            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteBooking(String token, Booking booking, OnApiResponse onApiResponse){
        Call<com.example.carrental.model.BookingResponse> call = apiService.RentRequest(token, booking);
        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingResponse> call, @NonNull Response<BookingResponse> response) {

                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<BookingResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteBookingHistory(String userID, OnApiResponse onApiResponse){
        Call<BookingHistoryResponse> call=apiService.getHistory(userID);
        call.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingHistoryResponse> call, @NonNull Response<BookingHistoryResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<BookingHistoryResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteForgetPassword(String email,OnApiResponse onApiResponse){
        Call<ForgetPasswordResponse> call= apiService.forgetPassword(email);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }

    public void remoteAvailableRate(String userID, OnApiResponse onApiResponse){
        Call<BookingHistoryResponse> call=apiService.getAvailableRate(userID);
        call.enqueue(new Callback<BookingHistoryResponse>() {
            @Override
            public void onResponse(@NonNull Call<BookingHistoryResponse> call, @NonNull Response<BookingHistoryResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<BookingHistoryResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteUpdateVehicleRate(String userID, int rate, String vehicleID, OnApiResponse onApiResponse){
        Call<ForgetPasswordResponse> call=apiService.updateVehicleRate(userID,rate,vehicleID);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteUpdateCompanyRate(String userID, int rate, String companyID, OnApiResponse onApiResponse){
        Call<ForgetPasswordResponse> call=apiService.updateCompanyRate(userID,rate,companyID);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }


    public void addToFavorite(String userID,boolean like, String vehicleID,OnApiResponse onApiResponse){
        Call<ForgetPasswordResponse> call=apiService.AddToFavorite(userID,like,vehicleID);
        call.enqueue(new Callback<ForgetPasswordResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForgetPasswordResponse> call, @NonNull Response<ForgetPasswordResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<ForgetPasswordResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }


    public void remoteFavoriteList(String userID, OnApiResponse onApiResponse){
        Call<VehicleResponse> call=apiService.getFavorite(userID);
        call.enqueue(new Callback<VehicleResponse>() {
            @Override
            public void onResponse(@NonNull Call<VehicleResponse> call, @NonNull Response<VehicleResponse> response) {
                responseChecker(response,onApiResponse);
                //responseChecker(new Response<VehicleResponse>,onApiResponse);
            }

            @Override
            public void onFailure(@NonNull Call<VehicleResponse> call, @NonNull Throwable t) {
                onApiResponse.onFailureListener(t);
                t.printStackTrace();
            }
        });
    }



    private void responseChecker(@NonNull Response<?> response, OnApiResponse onApiResponse){
        if(response.body()==null){
            Log.e(response.getClass().getName(), "Response Failed, invalid response with a null body");
            if(response.code()==400)
                onApiResponse.onFailureListener(new Throwable("400"));
            else
                onApiResponse.onFailureListener(new Throwable("Message: Unreached response"+"Code: "+response.code()));
        }
        else if(response.isSuccessful() && response.code()==200){
            Log.e(response.body().getClass().getName(), "Response success, with a responseCode==200");
            onApiResponse.onResponseListener(response.body());
        }
        else if(response.isSuccessful()){
            Log.e(response.body().getClass().getName(), "Response success, with a responseCode>200");
            onApiResponse.onResponseListener(response.body());
        }
        else {
            Log.e(response.body().getClass().getName(), "An unexpected error occurred when getting the response");

            if(response.body().getClass()==SignUpResponse.class) {
                SignUpResponse mSignUpResponse = (SignUpResponse) response.body();
                onApiResponse.onFailureListener(new Throwable("An unexpected error occurred " + "Message: " + response.message() + (mSignUpResponse.getValidateError() != null ? mSignUpResponse.getValidateError()[0] : "  Code: " + response.code())));
            }
            else if(response.body().getClass()==SignInResponse.class){
                SignInResponse mSignInResponse = (SignInResponse) response.body();
                onApiResponse.onFailureListener(new Throwable("An unexpected error occurred " + "Message: " + response.message() + (mSignInResponse.getMessage()!=null? mSignInResponse.getMessage(): "  Code: "+response.code())));
            }
            else
                onApiResponse.onFailureListener(new Throwable("An unexpected error occurred " + "Message: " + response.message() + " Code: " + response.code()));
        }
        onApiResponse=null;
    }


    /**
     * SignInResponse LiveData with return (old method)
     public LiveData<SignInResponse> signInResponse(User user) {
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

    /**
     * SignUp (old method)
     public void signUp(NewUser newUser){
        apiClient.signUpResponseRequest(newUser);
    }
    */
    /**
     * SignUp return (old method)
     public MutableLiveData<ResponseBody> getNewUserMutableLiveData() {
     return newUserMutableLiveDataResponse;
     }*/

    /**
     * signUp UrlEncoded (old method)
    public void signUp(String fName, String lName, String email,
                       String pass, String cPass, int phone){
        apiClient.signUpResponseRequest(fName,lName,email,pass,cPass,phone);
    }*/

    /**
    * getVehiclesResponse LiveData with return (old method)
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
    }*/


}
