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
        import com.greenlionsteam.mypersonaltrainer.Models.ExerciseModel;
        import com.greenlionsteam.mypersonaltrainer.Models.ModelParser;
        import com.greenlionsteam.mypersonaltrainer.R;
        import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage1Adapter;
        import com.greenlionsteam.mypersonaltrainer.adapters.ParametersPage2Adapter;
        import com.greenlionsteam.mypersonaltrainer.services.RestClient;

        import java.util.ArrayList;
        import java.util.List;

        import retrofit.Callback;
        import retrofit.RetrofitError;
        import retrofit.client.Response;


public class ExercisesFragment extends Fragment {

    private static final String TAG = "ExercisesFragment";


    private enum AdapterState{
        Groups,
        Exercises,
        Description // API PLZ MAKE IT.
    }
    private int selectedGroup;
    private int selectedExercise;


    private View rootView;
    private ListView exercisesListView;

    private AdapterState state;

    ModelParser dataFromJson;

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

        RestClient.getApi().getEverything(new Callback<ModelParser>() {
            @Override
            public void success(ModelParser modelParser, Response response) {
                dataFromJson = modelParser;
                setupExerciseGroupAdapter(new String[]{"Спина", "Груди", "Біцепс", "Тріцепс", "Плечі", "Ноги", "Прес"});
                setupExercisesListView();

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
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
        state = AdapterState.Groups;
        exercisesListView.setAdapter(groupAdapter);
        exercisesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(state == AdapterState.Groups) {
                    setupExerciseAdapter(getInfo(position));
                    exercisesListView.setAdapter(exerciseAdapter);
                    state = AdapterState.Exercises;
                    selectedGroup = position;
                }
                else if(state == AdapterState.Exercises) {
                    setupExerciseDescriptionAdapter(new String[]{getExerciseGroup(selectedGroup)[position].getDescription().getSteps().get(0)});
                    exercisesListView.setAdapter(exerciseDescriptionAdapter);
                    state = AdapterState.Description;
                    selectedExercise = position;
                }
            }
        });
    }

    public ExerciseModel[] getExerciseGroup(int group){
        switch (group){
            case 0:
             return dataFromJson.Back;
            case 1:
               return dataFromJson.Chest;
            case 2:
                return dataFromJson.Biceps;
            case 3:
                return dataFromJson.Triceps;
            case 4:
                return dataFromJson.Shoulders;
            case 5:
                return dataFromJson.Legs;
            case 6:
                return dataFromJson.Press;

        }
        return dataFromJson.Back;
    }

    public String [] getInfo(int group){
        String [] result ;
        switch (group){
            case 0:
                result = new String[dataFromJson.Back.length];
                for(int i =0; i< dataFromJson.Back.length;++i){
                    result[i]=dataFromJson.Back[i].getName();
                }
                break;
            case 1:
                result = new String[dataFromJson.Chest.length];
                for(int i =0; i< dataFromJson.Chest.length;++i){
                    result[i]=dataFromJson.Chest[i].getName();
                }
                break;
            case 2:
                result = new String[dataFromJson.Biceps.length];
                for(int i =0; i< dataFromJson.Biceps.length;++i){
                    result[i]=dataFromJson.Biceps[i].getName();
                }
                break;
            case 3:
                result = new String[dataFromJson.Triceps.length];
                for(int i =0; i< dataFromJson.Triceps.length;++i){
                    result[i]=dataFromJson.Triceps[i].getName();
                }
                break;
            case 4:
                result = new String[dataFromJson.Shoulders.length];
                for(int i =0; i< dataFromJson.Shoulders.length;++i){
                    result[i]=dataFromJson.Shoulders[i].getName();
                }
                break;
            case 5:
                result = new String[dataFromJson.Legs.length];
                for(int i =0; i< dataFromJson.Legs.length;++i){
                    result[i]=dataFromJson.Legs[i].getName();
                }
                break;
            case 6:
                result = new String[dataFromJson.Press.length];
                for(int i =0; i< dataFromJson.Press.length;++i){
                    result[i]=dataFromJson.Press[i].getName();
                }
                break;
            default:
                result = new String[]{"XZ"};
                break;
        }
        return result;
    }




    public void GetBack(){
        if(state == AdapterState.Exercises) {
            setupExerciseGroupAdapter(new String[]{"Спина", "Груди", "Біцепс", "Тріцепс", "Плечі", "Ноги", "Прес"});
            exercisesListView.setAdapter(groupAdapter);
            state = AdapterState.Groups;
        }
        else if(state == AdapterState.Description) {
            setupExerciseAdapter(getInfo(selectedGroup));
            exercisesListView.setAdapter(exerciseAdapter);
            state = AdapterState.Exercises;
        }
    }

}
