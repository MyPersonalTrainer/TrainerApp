package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boday-Alfaro on 01.12.2015.
 */
public class TrainingModel {

    public List<DaysModel> getDaysModels() {
        return daysModels;
    }

    List<DaysModel> daysModels = new ArrayList<>();

    public static class DaysModel{
        private List<ExerciseModel> exerciseModels = new ArrayList<>();
        public List<ExerciseModel> getExerciseModels() {
            return exerciseModels;
        }
    }



}
