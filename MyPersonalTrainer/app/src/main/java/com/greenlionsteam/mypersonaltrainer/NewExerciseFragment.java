package com.greenlionsteam.mypersonaltrainer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.greenlionsteam.mypersonaltrainer.Models.Exercise;
import com.greenlionsteam.mypersonaltrainer.Models.ExerciseType;

public class NewExerciseFragment extends Fragment{

    AddNewExerciseCallback mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AddNewExerciseCallback)
            mainActivity = (AddNewExerciseCallback) context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof AddNewExerciseCallback)
            mainActivity = (AddNewExerciseCallback) activity;
    }

    EditText name;
    Spinner type;
    DatePicker date;
    TimePicker time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_exersise, container, false);

        view.findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eName = name.getText().toString();
                ExerciseType eType = ExerciseType.getType(type.getSelectedItemPosition());
//                date.ge
//                Exercise exercise = new Exercise();
            }
        });

        return view;
    }
}
