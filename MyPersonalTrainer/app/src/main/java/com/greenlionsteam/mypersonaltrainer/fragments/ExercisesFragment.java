package com.greenlionsteam.mypersonaltrainer.fragments;

        import android.app.Activity;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Adapter;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.ListView;

        import com.greenlionsteam.mypersonaltrainer.ExerciseListFragment;
        import com.greenlionsteam.mypersonaltrainer.Models.Exercise;
        import com.greenlionsteam.mypersonaltrainer.R;
        import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage1Adapter;
        import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage2Adapter;

        import java.util.ArrayList;
        import java.util.List;


public class ExercisesFragment extends Fragment {

    private static final String TAG = "ExercisesFragment";

    private View rootView;
    private ListView exercisesListView;

    private int currentAdapter;
    private ArrayAdapter<String> groupAdapter;
    private ArrayAdapter<String> exerciseAdapter;
    private ArrayAdapter<String> exerciseDescriptionAdapter;

    public static ExercisesFragment newInstance() {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ExercisesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_exercises, container, false);
        initViews();
        setupExerciseGroupAdapter(new String[]{"sasasa1","dasdasd1"});
        setupExercisesListView();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private void initViews() {
        exercisesListView = (ListView) rootView.findViewById(R.id.exercises_list_view);
    }

    private void setupExerciseGroupAdapter(String[] data){
        groupAdapter = new  ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, data);
    }

    private void setupExerciseAdapter(String[] data){
        exerciseAdapter = new  ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, data);
    }

    private void setupExerciseDescriptionAdapter(String[] data){
        exerciseDescriptionAdapter = new  ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, data);
    }

    private void setupExercisesListView() {
        currentAdapter = 0;
        exercisesListView.setAdapter(groupAdapter);
        exercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentAdapter == 0) {
                    setupExerciseAdapter(new String[]{"2", "2"});
                    exercisesListView.setAdapter(exerciseAdapter);
                    currentAdapter = 1;
                }
                else if(currentAdapter == 1) {
                    setupExerciseDescriptionAdapter(new String[]{"3", "3"});
                    exercisesListView.setAdapter(exerciseDescriptionAdapter);
                    currentAdapter = 2;
                }
            }
        });
    }

    public void GetBack(){
        if(currentAdapter == 1) {
            setupExerciseGroupAdapter(new String[]{"sasasa1", "dasdasd1"});
            exercisesListView.setAdapter(groupAdapter);
            currentAdapter = 0;
        }
        else if(currentAdapter == 2) {
            setupExerciseAdapter(new String[]{"2", "2"});
            exercisesListView.setAdapter(exerciseAdapter);
            currentAdapter = 1;
        }
    }

}
