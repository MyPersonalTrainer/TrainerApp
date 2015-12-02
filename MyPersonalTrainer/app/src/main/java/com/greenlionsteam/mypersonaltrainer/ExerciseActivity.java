package com.greenlionsteam.mypersonaltrainer;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.greenlionsteam.mypersonaltrainer.Models.ExerciseModel;
import com.greenlionsteam.mypersonaltrainer.Models.TrainingModel;

import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    ListView ExercisesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra("pos", -1);

        setContentView(R.layout.activity_exercise);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExercisesListView = (ListView)findViewById(R.id.ExercisesListView);


        TrainingModel trainingModel = TrainingModel.Instance();
        ExercisesListView.setAdapter(new ExercisesAdapter(this, 0,
                trainingModel.getDaysModels().get(position).getExerciseModels()));
    }

    private class ExercisesAdapter extends ArrayAdapter<ExerciseModel>
    {

        public ExercisesAdapter(Context context, int resource, List<ExerciseModel> models) {
            super(context, resource, models);
        }

        class  MyViewHolder {
            ImageView clockImage, calenderImage;
            TextView fTime, tTime, day,eTitle;

            MyViewHolder(View v) {
                calenderImage = (ImageView) v.findViewById(R.id.clock);
                clockImage = (ImageView)v.findViewById(R.id.calender_img);
                fTime = (TextView) v.findViewById(R.id.start_time);
                tTime = (TextView)v.findViewById(R.id.end_time);
                eTitle = (TextView)v.findViewById(R.id.title_training);
                day = (TextView)v.findViewById(R.id.day_of_week);
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                //convertView = Li
                LayoutInflater inflater = (LayoutInflater) ExerciseActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            MyViewHolder holder = new MyViewHolder(convertView);

            //holder.calenderImage.setImageResource(R.drawable.calendar);
            //holder.clockImage.setImageResource(R.drawable.clock);
            //holder.fTime.setText(Integer.toString(exerciseList.get(position).typeOfExercise.duration));

            ExerciseModel exerciseModel = getItem(position);

            holder.tTime.setText(exerciseModel.getName().toString());
            //holder.eTitle.setText(exerciseModel.getId());
            holder.day.setText("Monday");

            return convertView;
        }
    }

}
