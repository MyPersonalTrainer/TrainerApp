package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.List;

public class TrainingDay {
    private List<ExerciseModel> exercises;
    private int duration;
    private int day;

    public List<ExerciseModel> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseModel> exercises) {
        this.exercises = exercises;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
