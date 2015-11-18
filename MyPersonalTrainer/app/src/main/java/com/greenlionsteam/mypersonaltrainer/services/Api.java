package com.greenlionsteam.mypersonaltrainer.services;

import com.greenlionsteam.mypersonaltrainer.Models.ModelParser;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by andrew on 19.11.15.
 */

public interface Api {
    @GET("/")
    public void getEverything(Callback<ModelParser> parser);
}
