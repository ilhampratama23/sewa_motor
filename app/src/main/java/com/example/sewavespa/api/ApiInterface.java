package com.example.sewavespa.api;

import com.example.sewavespa.model.bayar.Bayar;
import com.example.sewavespa.model.login.Login;
import com.example.sewavespa.model.motor.Motor;
import com.example.sewavespa.model.register.Register;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<Login> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("register")
    Call<Register> registerResponse(
            @Field("nama") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String confirmation
    );

    @FormUrlEncoded
    @POST("bayar")
    Call<Bayar> bayarResponse(
            @Field("pesanan") String name,
            @Field("biaya") String email
    );


//
//    @GET("logout")
//    Call<Logout> logoutResponse();

//    @Multipart
//    @POST("motor/input")
//    Call<Motor> motorResponse(
//            @Part MultipartBody.Part image,
//            @Part("nama") RequestBody nama,
//            @Part("tahun") RequestBody tahun,
//            @Part("harga") RequestBody harga
//    );

    @GET("motor/show")
    Call<Motor> motorShowResponse();
}
