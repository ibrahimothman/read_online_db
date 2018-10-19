package com.example.readonlinedb.retrofit;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClinet {


    private static Retrofit retrofit = null;

    public static Retrofit getApiClient(){
        if(retrofit == null){

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Log.d("fromApiClient","getApiClient");
            retrofit = new Retrofit.Builder().baseUrl(Constatns.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }

}
