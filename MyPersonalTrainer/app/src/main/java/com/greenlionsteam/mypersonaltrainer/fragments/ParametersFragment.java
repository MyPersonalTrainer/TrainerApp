package com.greenlionsteam.mypersonaltrainer.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.greenlionsteam.mypersonaltrainer.ExerciseListFragment;
import com.greenlionsteam.mypersonaltrainer.Models.UserParameters;
import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage1Adapter;
import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage2Adapter;

import java.util.ArrayList;
import java.util.List;


public class ParametersFragment extends Fragment {

    private static final String TAG = "ParametersFragment";

    private View rootView;
    private Button backButton;
    private Button nextButton;
    private Button finishButton;
    private ListView paramsListView;

    private int currentAdapter;
    private List<BaseAdapter> adapters;

    private UserParameters userParameters;

    public static ParametersFragment newInstance() {
        ParametersFragment fragment = new ParametersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ParametersFragment() {
        userParameters = new UserParameters(false,18,80,185);
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
        setupFinishButton();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private void initViews() {
        backButton = (Button) rootView.findViewById(R.id.parameters_back_button);
        nextButton = (Button) rootView.findViewById(R.id.parameters_next_button);
        finishButton = (Button) rootView.findViewById(R.id.parameters_finish_button);
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

    private void setupFinishButton() {
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendParameters();
            }
        });
    }

    private void setupParamsListViewAdapters() {
        adapters = new ArrayList<BaseAdapter>();
        adapters.add(new ParametersPage1Adapter(getActivity(), getChildFragmentManager(), userParameters));
        adapters.add(new ParametersPage2Adapter(getActivity(), getChildFragmentManager(), userParameters));
    }

    private void setupParamsListView() {
        currentAdapter = 0;
        paramsListView.setAdapter(adapters.get(currentAdapter));
        setupControlButtonsVisibility();
    }

    private void setupControlButtonsVisibility() {
        finishButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.VISIBLE);
        if (currentAdapter > 0) {
            backButton.setVisibility(View.VISIBLE);
        } else {
            backButton.setVisibility(View.INVISIBLE);
        }
        if(currentAdapter == adapters.size()-1) {
            nextButton.setVisibility(View.GONE);
            finishButton.setVisibility(View.VISIBLE);
        }
    }

    private void nextPage() {
        ParametersPage1Adapter parametersPage1Adapter = (ParametersPage1Adapter) adapters.get(currentAdapter);
        parametersPage1Adapter.saveChanges();
        if (currentAdapter < adapters.size() - 1) {
            paramsListView.setAdapter(adapters.get(++currentAdapter));
        }
    }

    private void prevPage() {
        if (currentAdapter > 0) {
            paramsListView.setAdapter(adapters.get(--currentAdapter));
        }
    }

    private void sendParameters() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ExerciseListFragment()).commit();
        }
}
