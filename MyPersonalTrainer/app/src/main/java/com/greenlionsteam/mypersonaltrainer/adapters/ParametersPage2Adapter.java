package com.greenlionsteam.mypersonaltrainer.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.fragments.ParameterInfoFragment;
import com.greenlionsteam.mypersonaltrainer.views.OnParameterViewListener;
import com.greenlionsteam.mypersonaltrainer.views.ParameterSpinnerView;
import com.greenlionsteam.mypersonaltrainer.views.ParameterEditView;

public class ParametersPage2Adapter extends BaseAdapter {

    private final int ITEMS_COUNT = 3;

    private Context context;
    private FragmentManager fm;

    private byte physical_level;
    private byte complexity;
    private byte training_type;

    public ParametersPage2Adapter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @Override
    public int getCount() {
        return ITEMS_COUNT;
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0: return physical_level;
            case 1: return complexity;
            case 2: return training_type;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch(position) {
            case 0: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.physical_level_param));
                view.setValues(new String[]{"Ніколи не займався спортом", "Займався(лася) раніше спортом", "Займаюся зараз спортом"});
                view.setSelectedValue(physical_level);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Загальна фізична підготовка", "Вкажіть, будь ласка, Вашу фізичну підготовку").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        physical_level  = (byte) selectedPosition;
                    }
                });
                return view;
            }
            case 1: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.complexity_param));
                view.setValues(new String[]{"Худа тілобудова", "Середня тілобудова", "Мускулиста тілобудова", "Повна тілобудова", "Дуже повна тілобудова"});
                view.setSelectedValue(complexity);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Тілобудова", "Вкажіть, будь ласка, Ваш тип тілобудови").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        complexity = (byte) selectedPosition;
                    }
                });
                return view;
            }
            case 2: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.training_type_param));
                view.setValues(new String[]{"Силові тренування", "Тренування на витривалість", "Кардіо", "Для набору маси", "Для похудання"});
                view.setSelectedValue(training_type);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Тип тренувань", "Виберіть, бадь ласка, тип тренувань").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        training_type = (byte) selectedPosition;
                    }
                });
                return view;
            }
        }
        return null;
    }
}
