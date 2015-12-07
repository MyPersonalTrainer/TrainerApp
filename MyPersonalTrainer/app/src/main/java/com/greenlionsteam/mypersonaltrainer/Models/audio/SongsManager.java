package com.greenlionsteam.mypersonaltrainer.Models.audio;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BohdanUhryn on 07.12.2015.
 */
public class SongsManager {

    private final String TAG = "SongsManager";
    private final String MEDIA_PATH = new String("/sdcard/");

    private Context context;

    private List<Song> songsList;
    private long currentSongId;
    private int currentSongPosition;
    private MediaPlayer player;

    public SongsManager(Context context) {
        this.context = context;
    }

    public List<Song> getSongsList() {
        return songsList;
    }

    public int getCurrentSongPosition() {
        return currentSongPosition;
    }

    public void switchSongById(long id) {
        stopSong();
        try {
            Song song = null;
            int i = 0;
            for (; i < songsList.size(); ++i) {
                if (songsList.get(i).getId() == id) {
                    song = songsList.get(i);
                }
            }
            if (song != null) {
                Uri songUri = Uri.parse(song.getPath());
                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(context.getApplicationContext(), songUri);
                player.prepare();
                player.start();
                currentSongId = id;
                currentSongPosition = i;
            }
        } catch (Exception ex) {
            stopSong();
            Log.e(TAG, "Switch song error!");
        }
    }

    public void switchSongByPosition(int position) {
        stopSong();
        try {
            Song song = songsList.get(position);
            if (song != null) {
                Uri songUri = Uri.parse(song.getPath());
                player = new MediaPlayer();
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setDataSource(context.getApplicationContext(), songUri);
                player.prepare();
                player.start();
                currentSongId = song.getId();
                currentSongPosition = position;
            }
        } catch (Exception ex) {
            stopSong();
            Log.e(TAG, "Switch song error!");
        }
    }

    public void playSong() {
        if (player != null) {
            player.start();
        } else if ((songsList != null) && (songsList.size() > 0)) {
            switchSongById(songsList.get(0).getId());
        }
    }

    public void pauseSong() {
        if (player != null) {
            player.pause();
        }
    }

    public void stopSong() {
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    public void playNext() {
        if ((songsList != null) && (songsList.size() > 0)) {
            if (currentSongPosition < songsList.size() - 1) {
                ++currentSongPosition;
            } else {
                currentSongPosition = 0;
            }
            switchSongByPosition(currentSongPosition);
        }
    }

    public void playPrev() {
        if ((songsList != null) && (songsList.size() > 0)) {
            if (currentSongPosition > 0) {
                --currentSongPosition;
            } else {
                currentSongPosition = songsList.size() - 1;
            }
            switchSongByPosition(currentSongPosition);
        }
    }

    public boolean isSongSelected(long id) {
        return (player != null) && (currentSongId == id);
    }

    public boolean isSongSelected() {
        return player != null;
    }

    public boolean isPlaying() {
        return (player != null) && player.isPlaying();
    }

    public void loadExternalCardSongs() {
        songsList = new ArrayList<Song>();
        ContentResolver musicResolver = context.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            int titleColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int pathColumn = musicCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                long id = musicCursor.getLong(idColumn);
                String title = musicCursor.getString(titleColumn);
                String artist = musicCursor.getString(artistColumn);
                String path = musicCursor.getString(pathColumn);
                songsList.add(new Song(id, title, artist, path));
            }
            while (musicCursor.moveToNext());
        }
    }

    private Song getSongById(long id) {
        for (Song s : songsList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

}
