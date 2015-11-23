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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    Context context;
    ArrayList<Exercise> exerciseList;

    public ExerciseAdapter(Context context, ArrayList<Exercise> model) {
        super(context, R.layout.list_item);
        this.context = context;
        this.exerciseList = model;

    }

    public void addItem(Exercise e) {
        exerciseList.add(e);
        notifyDataSetChanged();
    }

    public void itemClicked(int position) {
        context.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(exerciseList.get(position).typeOfExercise.videoLink)));
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

        holder.calenderImage.setImageResource(R.drawable.calendar);
        holder.clockImage.setImageResource(R.drawable.clock);
        holder.fTime.setText(Integer.toString(exerciseList.get(position).typeOfExercise.duration));
        holder.tTime.setText(exerciseList.get(position).dateTime.toString());
        holder.eTitle.setText(exerciseList.get(position).name);
        holder.day.setText("Monday");
        return row;

    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }
}
