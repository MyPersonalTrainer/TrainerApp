package com.greenlionsteam.mypersonaltrainer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.greenlionsteam.mypersonaltrainer.Models.audio.AudioManager;
import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.adapters.SongsAdapter;

/**
 * Created by BohdanUhryn on 06.12.2015.
 */
public class AudioFragment extends Fragment {

    private static final String TAG = "AudioFragment";

    private View rootView;
    private ListView audioList;
    private ImageButton playButton;

    private static AudioManager audioManager;

    public static AudioFragment newInstance() {
        AudioFragment fragment = new AudioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AudioFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_audio_player, container, false);
        initViews();
        setupAudioManager();
        loadSongs();
        setupAudioList();
        setupPlayButton();
        return rootView;
    }

    private void initViews() {
        audioList = (ListView) rootView.findViewById(R.id.audio_list);
        playButton = (ImageButton) rootView.findViewById(R.id.audio_play_button);
    }

    private void setupAudioManager() {
        if (audioManager == null) {
            audioManager = new AudioManager(getActivity());
        }
    }

    private void setupAudioList() {
        if (audioManager != null) {
            SongsAdapter adapter = new SongsAdapter(getActivity(), audioManager.getSongsList());
            audioList.setAdapter(adapter);
        }
    }

    private void setupPlayButton() {

    }

    private void loadSongs() {
        audioManager.loadExternalCardSongs();
    }
}
