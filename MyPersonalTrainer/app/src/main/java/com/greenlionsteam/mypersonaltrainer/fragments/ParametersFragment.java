package com.greenlionsteam.mypersonaltrainer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage1Adapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BohdanUhryn on 14.11.2015.
 */
public class ParametersFragment extends Fragment {

    private static final String TAG = "ParametersFragment";

    private View rootView;
    private Button backButton;
    private Button nextButton;
    private ListView paramsListView;

    private int currentAdapter;
    private List<BaseAdapter> adapters;

    public static ParametersFragment newInstance() {
        ParametersFragment fragment = new ParametersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ParametersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_parameters, container, false);
        initViews();
        setupBackButton();
        setupNextButton();
        setupParamsListViewAdapters();
        setupParamsListView();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private void initViews() {
        backButton = (Button) rootView.findViewById(R.id.parameters_back_button);
        nextButton = (Button) rootView.findViewById(R.id.parameters_next_button);
        paramsListView = (ListView) rootView.findViewById(R.id.parameters_list_view);
    }

    private void setupBackButton() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevPage();
                setupControlButtonsVisibility();
            }
        });
    }

    private void setupNextButton() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
                setupControlButtonsVisibility();
            }
        });
    }

    private void setupParamsListViewAdapters() {
        adapters = new ArrayList<BaseAdapter>();
        adapters.add(new ParametersPage1Adapter(getActivity(), getChildFragmentManager()));
        adapters.add(new ParametersPage1Adapter(getActivity(), getChildFragmentManager()));
    }

    private void setupParamsListView() {
        currentAdapter = 0;
        paramsListView.setAdapter(adapters.get(currentAdapter));
        setupControlButtonsVisibility();
    }

    private void setupControlButtonsVisibility() {
        if (currentAdapter >= adapters.size() - 1) {
            nextButton.setVisibility(View.INVISIBLE);
        } else {
            nextButton.setVisibility(View.VISIBLE);
        }
        if (currentAdapter > 0) {
            backButton.setVisibility(View.VISIBLE);
        } else {
            backButton.setVisibility(View.INVISIBLE);
        }
    }

    private void nextPage() {
        if (currentAdapter < adapters.size() - 1) {
            paramsListView.setAdapter(adapters.get(++currentAdapter));
        }
    }

    private void prevPage() {
        if (currentAdapter > 0) {
            paramsListView.setAdapter(adapters.get(--currentAdapter));
        }
    }
}
