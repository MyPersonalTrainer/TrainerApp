package com.greenlionsteam.mypersonaltrainer;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.greenlionsteam.mypersonaltrainer.Models.ExerciseModel;
        import com.greenlionsteam.mypersonaltrainer.Models.TrainingModel;

        import java.util.List;

public class ExerciseDescriptionActivity extends AppCompatActivity {

    ListView ExercisesListView;
    int dayNumber = 1;

    private ArrayAdapter<String> exerciseDescriptionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int positionOfDay =  getIntent().getIntExtra("PosOfDay", -1);
        int positionOfExercise =  getIntent().getIntExtra("PosOfExercise", -1);

        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExercisesListView = (ListView)findViewById(R.id.ExercisesListView);

        TrainingModel trainingModel = TrainingModel.Instance();
        toolbar.setTitle("Опис вправи" + trainingModel.getDaysModels().get(positionOfDay).getExerciseModels().get(positionOfExercise).getName());
        setupExerciseDescriptionAdapter(trainingModel.getDaysModels().get(positionOfDay).getExerciseModels().get(positionOfExercise).getDescription().getSteps());
        ExercisesListView.setAdapter(exerciseDescriptionAdapter);
    }

    private void setupExerciseDescriptionAdapter(List<String> data){
        exerciseDescriptionAdapter = new  ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, data);
    }
}

