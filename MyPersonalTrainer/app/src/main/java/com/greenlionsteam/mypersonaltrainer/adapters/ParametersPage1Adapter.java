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

public class ParametersPage1Adapter extends BaseAdapter {

    private final int ITEMS_COUNT = 5;

    private Context context;
    private FragmentManager fm;

    private boolean someBoolParam;
    private int someIntParam = 0;
    private int[] someIntParamVariants;

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
            case 0: return someBoolParam;
            case 1: return someIntParam;
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
                view.setDescription(context.getString(R.string.gender_param));
                view.setValues(new String[]{"Чоловік", "Жінка"});
                view.setSelectedValue(someIntParam);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {}
                });
                return view;
            }
            case 1: {
                ParameterEditView view = (ParameterEditView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_edit, parent, false);
                view.setDescription(context.getString(R.string.age_param));
                view.setValues(18);
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {}
                });
                return view;
            }
            case 2: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.weight_param));
                view.setValues(new String[]{"1", "2", "3"});
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {}
                });
                return view;
            }
            case 3: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.height_param));
                view.setValues(new String[]{"1", "2", "3"});
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {}
                });
                return view;
            }
            case 4: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.activity_param));
                view.setValues(new String[]{"Сидяча робота", "Праця не фізична, але багато ходжу ", "Праця з поміркованим фізичним навантаженням", "Важка фізична праця", "Гружу вагони кожного вечора"});
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {}
                });
                return view;
            }
        }
        return null;
    }
}
