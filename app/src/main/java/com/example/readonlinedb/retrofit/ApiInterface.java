package com.example.readonlinedb.retrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {


    @FormUrlEncoded
    @POST(Constatns.LOGIN_URL)
    public Call<Error> login(@FieldMap HashMap<String,String> map);

    @FormUrlEncoded
    @POST(Constatns.SIGNUP_URL)
    public Call<Error> signup(@FieldMap HashMap<String,String> map);

}
