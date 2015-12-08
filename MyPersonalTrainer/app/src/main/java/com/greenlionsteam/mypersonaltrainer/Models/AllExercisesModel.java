package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bohdan on 08.12.2015.
 */

public class AllExercisesModel  {
    private static AllExercisesModel allExercisesModel;
    private AllExercisesModel(){

    }
    public static AllExercisesModel Instance(){
        if (allExercisesModel == null)
            allExercisesModel = new AllExercisesModel();
        return allExercisesModel;
    }

    public static void CreateNewInstance()
    {
        allExercisesModel = new AllExercisesModel();
    }

        public List<ExerciseModel> exerciseModels = new ArrayList<>();

        public List<ExerciseModel> getExerciseModels() {
            return exerciseModels;
        }
}