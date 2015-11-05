package com.greenlionsteam.mypersonaltrainer.Models;

import java.util.Date;

/**
 * Created by oleg on 11/5/2015.
 */
public class Exercise {
    String name;
    String videoLink;
    int duration;//in sec
    Date dateTime;//date and time of exercise

    public Exercise(String name, String video, int duration, Date dateTime)
    {
        this.dateTime = dateTime;
        this.name = name;
        this.videoLink = video;
        this.duration = duration;
    }
}