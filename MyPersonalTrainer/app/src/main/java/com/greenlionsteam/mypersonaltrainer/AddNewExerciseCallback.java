package com.greenlionsteam.mypersonaltrainer;

import com.greenlionsteam.mypersonaltrainer.Models.Exercise;

public interface AddNewExerciseCallback {
    void addNewExercise(Exercise e);

    void showExercises();
}
