package com.example.carrental.data;

import com.example.carrental.constant.Credentials;
import com.example.carrental.model.Booking;
import com.example.carrental.model.BookingResponse;
import com.example.carrental.model.NewUser;
import com.example.carrental.model.SignInResponse;
import com.example.carrental.model.SignUpResponse;
import com.example.carrental.model.User;
import com.example.carrental.model.VehicleResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {


    @GET(Credentials.ALL_VEHICLES_URL)
    Call<VehicleResponse> getJsonModel();

    @GET(Credentials.SEARCHED_VEHICLES_URL)
    Call<VehicleResponse> getSearchedItems(@Query("serch") String model);


    /**
     * UrlEncoded
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
    Call<SignUpResponse> signUp(@Body NewUser newUser);

    @POST(Credentials.SIGN_IN)
    Call<SignInResponse> signIn(@Body User user);

    @POST(Credentials.Booking)
    Call<BookingResponse> RentRequest(@Header("authorization") String token, @Body Booking booking);



}
