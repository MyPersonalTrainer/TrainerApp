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

import java.util.ArrayList;
import java.util.List;

public class ParametersPage1Adapter extends BaseAdapter {

    private final int ITEMS_COUNT = 5;

    private Context context;
    private FragmentManager fm;
    private List<ParameterEditView> viewList = new ArrayList<ParameterEditView>();
    private byte gender = 0;
    private int age = 18;
    private int weight = 50;
    private int height = 150;
    private byte activity = 0;

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public byte getActivity() {
        return activity;
    }

    public void setActivity(byte activity) {
        this.activity = activity;
    }

    public ParametersPage1Adapter(Context context, FragmentManager fm) {
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
            case 0: return gender;
            case 1: return age;
            case 2: return weight;
            case 3: return height;
            case 4: return activity;
        }
        return null;
    }

    public void setItems()
    {
        age = viewList.get(0).getValue();
        weight = viewList.get(1).getValue();
        height = viewList.get(2).getValue();
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
                view.setDescription(context.getString(R.string.gender_param));
                view.setValues(new String[]{"Чоловік", "Жінка"});
                view.setSelectedValue(gender);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Стать", "Введіть, будь ласка, свою стать, для того, щоб ми могли правильно розрахувати навантаження на ваше тіло").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        gender = (byte) selectedPosition;
                    }
                });
                return view;
            }
            case 1: {
                ParameterEditView view = (ParameterEditView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_edit, parent, false);
                view.setDescription(context.getString(R.string.age_param));
                view.setValues(age);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Вік", "Вкажіть, будь ласка, Ваш вік").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                    }
                });
                viewList.add(view);
                return view;
            }
            case 2: {
                ParameterEditView view = (ParameterEditView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_edit, parent, false);
                view.setDescription(context.getString(R.string.weight_param));
                view.setValues(weight);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Вага", "Вкажіть, будь ласка, Вашу вагу").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                    }
                });
                viewList.add(view);
                return view;
            }
            case 3: {
                ParameterEditView view = (ParameterEditView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_edit, parent, false);
                view.setDescription(context.getString(R.string.height_param));
                view.setValues(height);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Ріст", "Вкажіть, будь ласка, Ваш зріст").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                    }
                });
                viewList.add(view);
                return view;
            }
            case 4: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.activity_param));
                view.setValues(new String[]{"Сидяча робота", "Праця не фізична, але багато ходжу ", "Праця з поміркованим фізичним навантаженням", "Важка фізична праця", "Гружу вагони кожного вечора"});
                view.setSelectedValue(activity);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Фізична активність", "Вкажіть, будь ласка, Ваш рівень фізичної активності").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        activity = (byte) selectedPosition;
                    }
                });
                return view;
            }
        }
        return null;
    }
}
