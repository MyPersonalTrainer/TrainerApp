package com.greenlionsteam.mypersonaltrainer.services;

import com.greenlionsteam.mypersonaltrainer.Models.ModelParser;

import retrofit.Callback;
import retrofit.http.GET;

public interface Api {
    @GET("/")
    public void getEverything(Callback<ModelParser> parser);
}
