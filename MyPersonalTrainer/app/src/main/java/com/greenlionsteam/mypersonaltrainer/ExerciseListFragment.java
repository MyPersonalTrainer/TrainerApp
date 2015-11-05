package com.greenlionsteam.mypersonaltrainer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.greenlionsteam.mypersonaltrainer.Models.Exercise;
import com.greenlionsteam.mypersonaltrainer.Models.ExerciseType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by oleg on 11/5/2015.
 */
public class ExerciseListFragment extends Fragment {

    ExerciseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_feed, container, false);
        adapter = new ExerciseAdapter(getActivity(), MainFeedActivity.exercises);
        ((ListView) v.findViewById(R.id.listView)).setAdapter(adapter);
        ((ListView) v.findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.itemClicked(position);
            }
        });
        v.findViewById(R.id.addNewExercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainFeedActivity)getActivity()).showAddingScreen();
            }
        });
        return v;
    }
}
