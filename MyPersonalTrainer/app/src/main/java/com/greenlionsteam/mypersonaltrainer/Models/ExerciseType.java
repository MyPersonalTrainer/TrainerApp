package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.ArrayList;

public class ExerciseType {

    public String typeName;
    public String videoLink;
    public int duration;

    public ExerciseType(String typeName, String videoLink, int duration)
    {
        this.duration = duration;
        this.typeName = typeName;
        this.videoLink = videoLink;
    }


    public static ArrayList<ExerciseType> exerciseTypes;
    public static void createAllTypes()
    {
        exerciseTypes = new ArrayList<>();

        ExerciseType type = new ExerciseType("abs training", "https://www.youtube.com/watch?v=vkKCVCZe474", 8*60);
        exerciseTypes.add(type);

        type = new ExerciseType("chest training", "https://www.youtube.com/watch?v=jWc8gHlAkoM", 8*60);
        exerciseTypes.add(type);

        type = new ExerciseType("absLev2 training", "https://www.youtube.com/watch?v=44mgUselcDU", 10*60);
        exerciseTypes.add(type);
    }

    public static String[] getAllTypes()
    {
        createAllTypes();
        String[] strings = new String[exerciseTypes.size()];
        int i=0;
        for(ExerciseType type : exerciseTypes)
        {
            strings[i++] = type.typeName;
        }

        return strings;
    }

    public static ExerciseType getType(int selectedItemPosition) {
        return exerciseTypes.get(selectedItemPosition);
    }
}
