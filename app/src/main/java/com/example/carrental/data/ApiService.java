package com.example.carrental.data;

import com.example.carrental.constant.Credentials;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.Vehicle;
import com.example.carrental.model.VehicleResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    @GET(Credentials.ALL_VEHICLES_URL)
    Call<VehicleResponse> getJsonModel();


    /*
    @FormUrlEncoded
    @POST(Credentials.REGISTER)
    Call<ResponseBody> signUp(@Field("firstName") String firstName,
                              @Field("lastName") String lastName,
                              @Field("email") String email,
                              @Field("password") String password,
                              @Field("cpassword") String cpassword,
                              @Field("phone") int phone
                                );
    */

    @POST(Credentials.REGISTER)
    Call<ResponseBody> signUp(@Body NewUser newUser);

    @POST(Credentials.SIGN_IN)
    Call<SignInResponse> signIn(@Body User user);






}
