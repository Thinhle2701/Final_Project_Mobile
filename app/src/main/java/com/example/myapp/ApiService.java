package com.example.myapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Field;
import retrofit2.http.Headers;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.Query;


public interface ApiService {




    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")

            .create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.130:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);




    @GET("api/question/{id}")
    Call<question> get_question(@Path("id") int groupid);



    @POST("api/user/login")
    Call<user> loginUser(@Body Body_User body);

    @POST("api/user/add_user_player")
    Call<user> RegisterUser(@Body Body_User body);

    @GET("api/user/{id}")
    Call<user> get_user(@Path("id") String user_id);

    @PUT("api/user/update_point/{id}")
    Call<user> updatePoint(@Path("id") String user_id,@Body user x);

    @GET("api/image/{id}")
    Call<IMG> lay_url(@Path("id") int id);



}


