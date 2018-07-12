package com.cog.api;

import com.cog.retrofit.ApiInterface;

import retrofit.RestAdapter;

public class Api {

    public static ApiInterface getClient()
    {
        RestAdapter restAdapter =new RestAdapter.Builder()
                .setEndpoint("http://mobileappdatabase.in/")
                .build();
        ApiInterface apiInterface = restAdapter.create(ApiInterface.class);
        return  apiInterface;
    }
}
