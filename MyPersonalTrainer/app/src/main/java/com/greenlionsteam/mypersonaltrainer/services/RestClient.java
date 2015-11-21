package com.greenlionsteam.mypersonaltrainer.services;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class RestClient {

    public static final String BASE_URL =
            "https://personal-trainer-app.herokuapp.com";


    private static Api api_ = null;

    public static Api getApi() {

        if (api_ == null) {
            new RestClient();
            return api_;
        } else {

            return api_;
        }
    }


    private RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        api_ = restAdapter.create(Api.class);
    }

}