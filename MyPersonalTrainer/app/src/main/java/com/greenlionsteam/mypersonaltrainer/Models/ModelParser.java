package com.greenlionsteam.mypersonaltrainer.Models;

import com.google.gson.annotations.SerializedName;

public class ModelParser {
    @SerializedName("Спина")
    public ExerciseModel[] Back;

    @SerializedName("Груди")
    public ExerciseModel[] Chest;

    @SerializedName("Біцепс")
    public ExerciseModel[] Biceps;

    @SerializedName("Тріцепс")
    public ExerciseModel[] Triceps;

    @SerializedName("Плечі")
    public ExerciseModel[] Shoulders;

    @SerializedName("Ноги")
    public ExerciseModel[] Legs;

    @SerializedName("Прес")
    public ExerciseModel[] Press;

}
