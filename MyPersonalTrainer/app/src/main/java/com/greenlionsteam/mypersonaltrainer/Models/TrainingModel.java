package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Boday-Alfaro on 01.12.2015.
 */
public class TrainingModel  {
    private static TrainingModel trainingModel;
    private TrainingModel(){

    }
    public static TrainingModel Instance(){
        if (trainingModel == null)
            trainingModel = new TrainingModel();
        return trainingModel;
    }

    public static void CreateNewInstance()
    {
        trainingModel = new TrainingModel();
    }

    public List<DaysModel> getDaysModels() {
        return daysModels;
    }

    List<DaysModel> daysModels = new ArrayList<>();

    public static class DaysModel{
        public Date getDate() {
            return date;
        }

        private Date date;

        public String getName() {
            return name;
        }

        private String name;


        public DaysModel(){
            name = "Wed";
            date = new Date();
        }

        private List<ExerciseModel> exerciseModels = new ArrayList<>();
        public List<ExerciseModel> getExerciseModels() {
            return exerciseModels;
        }
    }



}
