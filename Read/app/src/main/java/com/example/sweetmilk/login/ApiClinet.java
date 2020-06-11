package com.example.sweetmilk.login;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClinet {
    public  static  final  String BASE_URL ="http://192.168.43.43:8080/nicu/";
    public static Retrofit retrofit = null;
    public static Retrofit getApiClient(){
        if(retrofit==null)
        {
           retrofit= new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;

    }


}
