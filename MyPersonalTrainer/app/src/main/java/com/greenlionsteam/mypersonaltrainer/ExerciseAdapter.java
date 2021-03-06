package com.greenlionsteam.mypersonaltrainer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenlionsteam.mypersonaltrainer.Models.Exercise;
import com.greenlionsteam.mypersonaltrainer.Models.TrainingModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends ArrayAdapter<TrainingModel.DaysModel> {
    Context context;
    List<TrainingModel.DaysModel> exerciseList;

    public ExerciseAdapter(Context context, List<TrainingModel.DaysModel> model) {
        super(context, R.layout.list_item);
        this.context = context;
        this.exerciseList = model;

    }


    public void itemClicked(int position) {
        Intent i = new Intent(context, ExerciseActivity.class);
        i.putExtra("pos", position);
        context.startActivity(i);
          //      Uri.parse(exerciseList.get(position).typeOfExercise.videoLink)));
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
        View row = convertView;
        MyViewHolder holder = null;
        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyViewHolder) row.getTag();
        }

        //get group_muscle string
        String muscleGroup = "";
        for(int i = 0; i < exerciseList.get(position).getExerciseModels().size(); ++i)
        {
            if(!muscleGroup.contains(exerciseList.get(position).getExerciseModels().get(i).getMuscle_group()))
            muscleGroup += exerciseList.get(position).getExerciseModels().get(i).getMuscle_group() + ", ";
        }
        muscleGroup = muscleGroup.substring(0, muscleGroup.length() - 2);

        holder.calenderImage.setImageResource(R.drawable.clock_icon);
        holder.clockImage.setImageResource(R.drawable.calendar_icon);
        //holder.fTime.setText(Integer.toString(exerciseList.get(position).typeOfExercise.duration));
       // holder.tTime.setText(exerciseList.get(position).getDate().toString());
        holder.eTitle.setText(muscleGroup);
        if(position == 0)
            holder.day.setText("Понеділок");
        else if (position == 1)
            holder.day.setText("Середа");
        else holder.day.setText("П'ятниця");
        return row;

    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }
}
