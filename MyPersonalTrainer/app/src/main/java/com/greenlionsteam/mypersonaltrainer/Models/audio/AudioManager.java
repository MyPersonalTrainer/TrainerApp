package com.greenlionsteam.mypersonaltrainer.Models.audio;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BohdanUhryn on 07.12.2015.
 */
public class AudioManager {

    private Context context;

    private List<Song> songsList;

    public AudioManager(Context context) {
        this.context = context;
    }

    public List<Song> getSongsList() {
        return songsList;
    }

    public void loadExternalCardSongs() {
        songsList = new ArrayList<Song>();
        ContentResolver musicResolver = context.getContentResolver();
        //Uri musicUri = Uri.parse("content://media/external");
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            int titleColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songsList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }

}
