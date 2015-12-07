package com.greenlionsteam.mypersonaltrainer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.greenlionsteam.mypersonaltrainer.Models.audio.Song;
import com.greenlionsteam.mypersonaltrainer.Models.audio.SongsManager;
import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.adapters.SongsAdapter;
import com.greenlionsteam.mypersonaltrainer.fragments.loaders.ExternalCardSongsLoader;

import java.util.List;

/**
 * Created by BohdanUhryn on 06.12.2015.
 */
public class AudioFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Song>> {

    private static final String TAG = "AudioFragment";

    private final int EXTERNAL_CARD_SONGS_LOADER_ID = hashCode();

    private View rootView;
    private ListView audioList;
    private ImageButton playButton;
    private ImageButton pauseButton;
    private ImageButton prevButton;
    private ImageButton nextButton;
    private ImageButton playlistsButton;

    private SongsAdapter adapter;

    private static SongsManager songsManager;

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
        setupSongsManager();
        loadSongs();
        setupPlayButton();
        setupPauseButton();
        setupPlayNextButton();
        setupPlayPrevButton();
        setupPlaylistsButton();
        return rootView;
    }

    private void initViews() {
        audioList = (ListView) rootView.findViewById(R.id.audio_list);
        playButton = (ImageButton) rootView.findViewById(R.id.audio_play_button);
        pauseButton = (ImageButton) rootView.findViewById(R.id.audio_pause_button);
        prevButton = (ImageButton) rootView.findViewById(R.id.audio_play_prev_button);
        nextButton = (ImageButton) rootView.findViewById(R.id.audio_play_next_button);
        playlistsButton = (ImageButton) rootView.findViewById(R.id.audio_playlists_button);
    }

    private void setupSongsManager() {
        if (songsManager == null) {
            songsManager = new SongsManager(getActivity());
        }
    }

    private void setupSongsList() {
        if (songsManager != null) {
            adapter = new SongsAdapter(getActivity(), songsManager.getSongsList());
            audioList.setAdapter(adapter);
            audioList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (songsManager != null) {
                        if (songsManager.isSongSelected(id)) {
                            if (songsManager.isPlaying()) {
                                songsManager.pauseSong();
                                playButton.setVisibility(View.VISIBLE);
                                pauseButton.setVisibility(View.GONE);
                            } else {
                                songsManager.playSong();
                                playButton.setVisibility(View.GONE);
                                pauseButton.setVisibility(View.VISIBLE);
                            }
                        } else {
                            songsManager.switchSongByPosition(position);
                            playButton.setVisibility(View.GONE);
                            pauseButton.setVisibility(View.VISIBLE);
                        }
                        adapter.setSelectedSong(songsManager.getCurrentSongPosition());
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            audioList.setSelector(android.R.color.holo_green_dark);
        }
    }

    private void setupPlayButton() {
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songsManager != null) {
                    songsManager.playSong();
                    playButton.setVisibility(View.GONE);
                    pauseButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setupPauseButton() {
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songsManager != null && songsManager.isSongSelected()) {
                    songsManager.pauseSong();
                    playButton.setVisibility(View.VISIBLE);
                    pauseButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupPlayNextButton() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songsManager != null) {
                    songsManager.playNext();
                    adapter.setSelectedSong(songsManager.getCurrentSongPosition());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void setupPlayPrevButton() {
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songsManager != null) {
                    songsManager.playPrev();
                    adapter.setSelectedSong(songsManager.getCurrentSongPosition());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void setupPlaylistsButton() {
        playlistsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlaylistsFragment playlistsFragment = new PlaylistsFragment();
                playlistsFragment.show(getChildFragmentManager(), "PlaylistsFragment");
            }
        });
    }

    private void loadSongs() {
        getLoaderManager().restartLoader(EXTERNAL_CARD_SONGS_LOADER_ID, null, this).forceLoad();
    }

    @Override
    public Loader<List<Song>> onCreateLoader(int id, Bundle args) {
        if (id == EXTERNAL_CARD_SONGS_LOADER_ID) {
            return new ExternalCardSongsLoader(getActivity(), songsManager);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Song>> loader, List<Song> data) {
        if (loader.getId() == EXTERNAL_CARD_SONGS_LOADER_ID) {
            setupSongsList();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Song>> loader) {}
}
