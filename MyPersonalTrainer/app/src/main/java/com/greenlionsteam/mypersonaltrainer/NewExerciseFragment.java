package com.greenlionsteam.mypersonaltrainer;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.greenlionsteam.mypersonaltrainer.Models.Exercise;
import com.greenlionsteam.mypersonaltrainer.Models.ExerciseType;

import java.util.Date;

public class NewExerciseFragment extends Fragment
        implements TimePickerDialog.OnTimeSetListener,
                DatePickerDialog.OnDateSetListener{

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
    TextView date;
    TextView time;

    int h, m;
    int day;
    int month;
    int year;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_exersise, container, false);

        name = (EditText) view.findViewById(R.id.nameOfExercise);
        type = (Spinner) view.findViewById(R.id.exerciseType);
        date = (TextView) view.findViewById(R.id.datePicker);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), NewExerciseFragment.this, 2015, 05, 11).show();
            }
        });

        time = (TextView) view.findViewById(R.id.timePicker);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getActivity(), NewExerciseFragment.this, 0, 0,
                        DateFormat.is24HourFormat(getActivity())).show();
            }
        });


        type.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, ExerciseType.getAllTypes()));

        view.findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eName = name.getText().toString();
                ExerciseType eType = ExerciseType.getType(type.getSelectedItemPosition());

                Date d = new Date(year, month, day, h, m);
                Exercise exercise = new Exercise(eName, eType, d);
                if(mainActivity!=null)
                    mainActivity.addNewExercise(exercise);
            }
        });

        view.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity != null)
                    mainActivity.showExercises();
            }
        });


        return view;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        h = hourOfDay;
        m = minute;
    }

    @Override
    public void onDateSet(DatePicker view, int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
