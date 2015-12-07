package com.greenlionsteam.mypersonaltrainer.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by BohdanUhryn on 07.12.2015.
 */
public class PlaylistsFragment extends DialogFragment {

    private HashMap<Integer, String> playlistsMap;

    public PlaylistsFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        readAguments();
        return createDialog();
    }

    private void readAguments() {
        playlistsMap = new HashMap<Integer, String>();
        playlistsMap.put(0, "Phone");
    }

    private AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CharSequence[] titles = new CharSequence[playlistsMap.size()];
        int i = 0;
        for (Map.Entry<Integer, String> e : playlistsMap.entrySet()) {
            titles[i++] = e.getValue();
        }
        builder.setTitle("Playlists")
                .setSingleChoiceItems(titles, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}
