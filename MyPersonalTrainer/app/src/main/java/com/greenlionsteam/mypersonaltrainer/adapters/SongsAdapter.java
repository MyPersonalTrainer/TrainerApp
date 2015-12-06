package com.greenlionsteam.mypersonaltrainer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.greenlionsteam.mypersonaltrainer.Models.audio.Song;
import com.greenlionsteam.mypersonaltrainer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BohdanUhryn on 07.12.2015.
 */
public class SongsAdapter extends BaseAdapter {

    private Context context;
    private List<Song> songsList;

    public SongsAdapter(Context context, List<Song> songsList) {
        this.context = context;
        this.songsList = songsList;
    }

    @Override
    public int getCount() {
        return songsList != null ? songsList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return songsList != null ? songsList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return songsList != null ? songsList.get(position).getId() : -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_song, parent, false);
        TextView titleView = (TextView)view.findViewById(R.id.song_title);
        TextView artistView = (TextView)view.findViewById(R.id.song_artist);
        Song item = songsList.get(position);
        titleView.setText(item.getTitle());
        artistView.setText(item.getArtist());
        view.setTag(position);
        return view;
    }
}
