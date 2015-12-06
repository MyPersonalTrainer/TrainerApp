package com.greenlionsteam.mypersonaltrainer.views;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.greenlionsteam.mypersonaltrainer.R;
import com.greenlionsteam.mypersonaltrainer.fragments.ParameterInfoFragment;


public class ParameterEditView extends RelativeLayout {

    private OnParameterViewListener parameterViewListener;

    private TextView titleView;
    private EditText paramEdit;
    private ImageButton infoButton;

    private String description;
    private int value;

    public ParameterEditView(Context context) {

        this(context, null);

        paramEdit.setRawInputType(Configuration.KEYBOARD_12KEY);
        paramEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public ParameterEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parameterViewListener = null;
        inflateView();
        initViews();
        setupButtons();
        paramEdit.setRawInputType(Configuration.KEYBOARD_12KEY);
        paramEdit.setInputType(InputType.TYPE_CLASS_NUMBER );
    }

    public void setDescription(String description) {
        this.description = description;
        titleView.setText(description);
    }

    public void setValues(int value) {
        this.value = value;
        paramEdit.setText(Integer.toString(value));
    }

    public int getValue() {
        return Integer.parseInt(paramEdit.getText().toString());
    }

    public void setOnParameterViewListener(OnParameterViewListener parameterViewListener) {
        this.parameterViewListener = parameterViewListener;
    }

    private void inflateView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_parameter_edit, this, true);
    }

    private void initViews() {
        titleView = (TextView) findViewById(R.id.parameter_title);
        paramEdit = (EditText) findViewById(R.id.parameter_edit);
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
        paramEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                value = Integer.parseInt(v.getText().toString());
                return true;
            }
        });
    }

}
