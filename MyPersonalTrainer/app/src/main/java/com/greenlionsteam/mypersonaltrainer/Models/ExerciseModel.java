package com.greenlionsteam.mypersonaltrainer.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExerciseModel {
    private int id;
    private String name;
    private DescriptionClass description;
    private String muscle_group;
    private String imageUrl;
    private String videoUrl;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImgageUrl() {
        return imageUrl;
    }

    public void setImgageUrl(String imgageUrl) {
        this.imageUrl = imgageUrl;
    }

    public static class DescriptionClass{
        public List<String> getSteps() {
            return steps;
        }

        public void setSteps(List<String> steps) {
            this.steps = steps;
        }

        List<String> steps = new ArrayList<>();

    }

    public ExerciseModel(JSONObject mainJsonObject)  {
        try {
            //mainJsonObject = mainJsonObject.getJSONObject("exercises");
            id = mainJsonObject.getInt("id");
            name = mainJsonObject.getString("name");
            muscle_group = mainJsonObject.getJSONObject("muscle_group").getString("name");
            imageUrl = "https://personal-trainer-app.herokuapp.com" + mainJsonObject.getString("ex_image_url");
            videoUrl = mainJsonObject.getString("video_url");
            JSONArray array = null;

            array = mainJsonObject.getJSONArray("exercise_descriptions");

            description = new DescriptionClass();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                String currStep = jsonObject.getString("step");
                description.getSteps().add(currStep);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DescriptionClass getDescription() {
        return description;
    }

    public void setDescription(DescriptionClass description) {
        this.description = description;
    }

    public String getMuscle_group() { return muscle_group; }

    public void setMuscle_group(String muscle_group) { this.muscle_group = muscle_group;   }
}
