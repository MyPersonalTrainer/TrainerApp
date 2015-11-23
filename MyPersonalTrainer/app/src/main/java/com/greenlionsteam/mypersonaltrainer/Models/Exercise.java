package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.Date;

public class Exercise {
    public String name;
    public ExerciseType typeOfExercise;
    public Date dateTime;//date and time of exercise

    public Exercise(String name, ExerciseType typeOfExercise, Date dateTime)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.typeOfExercise = typeOfExercise;
//        this.videoLink = video;
//        this.duration = duration;
    }
}