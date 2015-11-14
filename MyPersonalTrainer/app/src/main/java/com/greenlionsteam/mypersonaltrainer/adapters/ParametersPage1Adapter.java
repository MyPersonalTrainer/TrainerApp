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

/**
 * Created by BohdanUhryn on 15.11.2015.
 */
public class ParametersPage1Adapter extends BaseAdapter {

    private final int ITEMS_COUNT = 2;

    private Context context;
    private FragmentManager fm;

    private boolean someBoolParam;
    private int someIntParam;
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
                view.setDescription(context.getString(R.string.some_bool_param));
                view.setValues(new String[]{"On", "Off"});
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        Toast.makeText(
                                context,
                                "Selected item is " + (String)selectedItem + " on position " + selectedPosition,
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
                return view;
            }
            case 1: {
                ParameterSpinnerView view = (ParameterSpinnerView) LayoutInflater
                        .from(context)
                        .inflate(R.layout.list_item_parameter_spinner, parent, false);
                view.setDescription(context.getString(R.string.some_int_param));
                view.setValues(new String[]{"1", "2", "3"});
                view.setOnParameterViewListener(new OnParameterViewListener() {
                    @Override
                    public void onShowInfoFragment(String title, String info) {
                        new ParameterInfoFragment(title, info).show(fm, ParameterInfoFragment.TAG);
                    }

                    @Override
                    public void onItemSelected(Object selectedItem, int selectedPosition) {
                        Toast.makeText(
                                context,
                                "Selected item is " + (String) selectedItem + " on position " + selectedPosition,
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
                return view;
            }
        }
        return null;
    }
}
