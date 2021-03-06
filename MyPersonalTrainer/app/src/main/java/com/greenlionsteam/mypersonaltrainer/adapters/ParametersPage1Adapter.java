package com.greenlionsteam.mypersonaltrainer.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.greenlionsteam.mypersonaltrainer.Models.UserParameters;
import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.fragments.ParameterInfoFragment;
import com.greenlionsteam.mypersonaltrainer.views.OnParameterViewListener;
import com.greenlionsteam.mypersonaltrainer.views.ParameterSpinnerView;
import com.greenlionsteam.mypersonaltrainer.views.ParameterEditView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ParametersPage1Adapter extends BaseAdapter {

    private final int ITEMS_COUNT = 5;

    private Context context;
    private FragmentManager fm;
    private List<ParameterEditView> viewList = new ArrayList<ParameterEditView>();
    /*private byte gender = 0;
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
    }*/

    UserParameters userParameters;

    public void saveChanges(){
        ParameterEditView v1 = weakReferenceSparseArray.get(1).get();
        ParameterEditView v2 = weakReferenceSparseArray.get(2).get();
        ParameterEditView v3 = weakReferenceSparseArray.get(3).get();

        userParameters.setAge(v1.getValue());
        userParameters.setWeight(v2.getValue());
        userParameters.setHeight(v3.getValue());
    }

    public ParametersPage1Adapter(Context context, FragmentManager fm, UserParameters userParameters) {
        this.context = context;
        this.fm = fm;
        this.userParameters = userParameters;
    }

    @Override
    public int getCount() {
        return ITEMS_COUNT;
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0: return userParameters.isGender();
            case 1: return userParameters.getAge();
            case 2: return userParameters.getWeight();
            case 3: return userParameters.getHeight();
            case 4: return userParameters.getActivity();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    SparseArray<WeakReference<ParameterEditView>> weakReferenceSparseArray = new SparseArray<>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        switch(position) {
            case 0: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.gender_param));
                view.setValues(new String[]{"Чоловік", "Жінка"});
                view.setSelectedValue(userParameters.isGender()? 1:0);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Стать", "Введіть, будь ласка, свою стать, для того, щоб ми могли правильно розрахувати навантаження на ваше тіло").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        userParameters.setGender(selectedPosition == 0? false:true );
                    }
                });
                return view;
            }
            case 1: {
                ParameterEditView view = (ParameterEditView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_edit, parent, false);
                view.setDescription(context.getString(R.string.age_param));
                view.setValues(userParameters.getAge());

                weakReferenceSparseArray.append(1, new WeakReference<ParameterEditView>(view));

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
                view.setValues(userParameters.getWeight());
                weakReferenceSparseArray.append(2, new WeakReference<ParameterEditView>(view));
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
                view.setValues(userParameters.getHeight());
                weakReferenceSparseArray.append(3, new WeakReference<ParameterEditView>(view));
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
                view.setSelectedValue(userParameters.getActivity());
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment("Фізична активність", "Вкажіть, будь ласка, Ваш рівень фізичної активності").show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        userParameters.setActivity(selectedPosition);
                    }
                });
                return view;
            }
        }
        return null;
    }
}
