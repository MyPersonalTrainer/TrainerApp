package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.Date;

/**
 * Created by oleg on 11/5/2015.
 */
public class Exercise {
    String name;
    ExerciseType typeOfExercise;
    Date dateTime;//date and time of exercise

    public Exercise(String name, ExerciseType typeOfExercise, Date dateTime)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.typeOfExercise = typeOfExercise;
//        this.videoLink = video;
//        this.duration = duration;
    }
}