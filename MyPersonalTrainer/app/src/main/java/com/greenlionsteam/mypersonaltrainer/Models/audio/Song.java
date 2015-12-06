package com.greenlionsteam.mypersonaltrainer.Models.audio;

import java.io.Serializable;

/**
 * Created by BohdanUhryn on 07.12.2015.
 */
public class Song implements Serializable {

    private long id;
    private String title;
    private String artist;

    public Song(long id, String title, String artist) {
        this.id = id;
        this.title=title;
        this.artist=artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}
