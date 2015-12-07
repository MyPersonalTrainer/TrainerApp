package com.greenlionsteam.mypersonaltrainer.fragments.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.greenlionsteam.mypersonaltrainer.Models.audio.Song;
import com.greenlionsteam.mypersonaltrainer.Models.audio.SongsManager;

import java.util.List;

/**
 * Created by BohdanUhryn on 07.12.2015.
 */
public class ExternalCardSongsLoader extends AsyncTaskLoader<List<Song>> {

    private SongsManager songsManager;

    public ExternalCardSongsLoader(Context context, SongsManager songsManager) {
        super(context);
        this.songsManager = songsManager;
    }

    @Override
    public List<Song> loadInBackground() {
        if (songsManager != null) {
            songsManager.loadExternalCardSongs();
        }
        return null;
    }
}
