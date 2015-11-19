package com.greenlionsteam.mypersonaltrainer.views;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.fragments.ParameterInfoFragment;


public class ParameterSpinnerView extends RelativeLayout {

    private OnParameterViewListener parameterViewListener;

    private TextView titleView;
    private Spinner paramSpinner;
    private ImageButton infoButton;

    private String description;
    private String[] values;

    public ParameterSpinnerView(Context context) {
        this(context, null);
    }

    public ParameterSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parameterViewListener = null;
        inflateView();
        initViews();
        setupButtons();
    }

    public void setDescription(String description) {
        this.description = description;
        titleView.setText(description);
    }
    public void setSelectedValue(int index)
    {
        paramSpinner.setSelection(index);
    }

    public void setValues(String[] values) {
        this.values = values;
        paramSpinner.setAdapter(
                new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, values));
    }

    public void setOnParameterViewListener(OnParameterViewListener parameterViewListener) {
        this.parameterViewListener = parameterViewListener;
    }

    private void inflateView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_parameter_spinner, this, true);
    }

    private void initViews() {
        titleView = (TextView) findViewById(R.id.parameter_title);
        paramSpinner = (Spinner) findViewById(R.id.parameter_spinner);
        infoButton = (ImageButton) findViewById(R.id.parameter_info_button);
    }

    private void setupButtons() {
        infoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parameterViewListener != null) {
                    parameterViewListener.onShowInfoFragment(titleView.getText().toString(), description);
                }
            }
        });
        paramSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parameterViewListener != null) {
                    parameterViewListener.onItemSelected(paramSpinner.getSelectedItem(), position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
