package com.greenlionsteam.mypersonaltrainer.Models;

public class UserParameters {
    private boolean gender;
    private int age;
    private int weight;
    private int height;
    private int activity;
    private int physical_level;
    private int complexity;
    private int training_type;

    public UserParameters(boolean gender, int age, int weight, int height, int activity, int physical_level, int complexity, int training_type) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activity = activity;
        this.physical_level = physical_level;
        this.complexity = complexity;
        this.training_type = training_type;
    }

    public UserParameters(boolean gender, int age, int weight, int height) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getPhysical_level() {
        return physical_level;
    }

    public void setPhysical_level(int physical_level) {
        this.physical_level = physical_level;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getTraining_type() {
        return training_type;
    }

    public void setTraining_type(int training_type) {
        this.training_type = training_type;
    }
}
