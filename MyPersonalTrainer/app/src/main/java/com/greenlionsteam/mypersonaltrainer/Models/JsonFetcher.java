package com.greenlionsteam.mypersonaltrainer.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Boday-Alfaro on 01.12.2015.
 */
public class JsonFetcher {
    private static final String TAG = "TvEventsFetchr";


    byte[] getUrlBytesGet(String urlSpec) throws Exception {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception();
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            connection.disconnect();
        }
    }

    byte[] getUrlBytes(String urlSpec, String params, String method) throws Exception {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestMethod(method);

        //send parameters
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception();
            }
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();

            return out.toByteArray();
        }
        catch (Exception e){
            throw e;
        }
        finally {
            connection.disconnect();
        }
    }

    String getUrl(String urlSpec, String jsonParams, String method) throws IOException, Exception {
        return new String(getUrlBytes(urlSpec, jsonParams, method));
    }

    //input json, sport name; output array if matches
    void parseExercises(TrainingModel trainingModel, String jsonString)  {
        try {
            JSONObject mainJsonObject = (JSONObject) new JSONTokener(jsonString.toString())
                    .nextValue();
            JSONArray array = mainJsonObject.getJSONArray("training_days");//sport type array
            List<TrainingModel.DaysModel> exerciseModelList = trainingModel.getDaysModels();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                TrainingModel.DaysModel currDay = new TrainingModel.DaysModel();
                JSONArray exercises = jsonObject.getJSONArray("exercises");
                List<ExerciseModel> exerciseModels = currDay.getExerciseModels();
                for (int j = 0; j < exercises.length(); j++) {
                    exerciseModels.add(new ExerciseModel(exercises.getJSONObject(j)));
                }

                exerciseModelList.add(currDay);
                //Log.i(TAG, "Json object : " + jsonObject.toString());

            }

        }catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public TrainingModel fetchExercises() throws Exception {//todo constants do
        TrainingModel.CreateNewInstance();
        TrainingModel trainingModel = TrainingModel.Instance();
        try {
            //String date1 = "2015-08-13";
            String url = /*context.getResources().getString(R.string, date);*/
                    "https://personal-trainer-app.herokuapp.com/programs";
            String jsonParams = "{\n" +
                    "\"program\" : \n" +
                    " {\n" +
                    " \"training_place\" : \"1\",\n" +
                    " \"training_type\" : \"2\"\n" +
                    " }\n" +
                    "}";
            String jsonString = getUrl(url, jsonParams, "POST");
            //Log.i(TAG, "Fetched contents of URL: " + jsonString);
            parseExercises(trainingModel, jsonString);
        } catch (Exception ex){
            ex.printStackTrace();
            Log.e(TAG, "Failed to fetch items", ex);
            throw new Exception("Error");
        }
        return trainingModel;
    }


    void parseAllExercises(AllExercisesModel allExercisesModel,String jsonString)  {
      try {
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString())
                    .nextValue();

            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                allExercisesModel.exerciseModels.add(new ExerciseModel(jsonObject));

            }
        }catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    public void fetchAllExcercises() {
        String url = /*context.getResources().getString(R.string, date);*/
                "https://personal-trainer-app.herokuapp.com/exercises.json";
        AllExercisesModel.CreateNewInstance();
        AllExercisesModel allExercisesModel = AllExercisesModel.Instance();
        try {
            String jsonString = new String(getUrlBytesGet(url));
            parseAllExercises(allExercisesModel,jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
