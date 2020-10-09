package com.openclassrooms.realestatemanager.features.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.openclassrooms.realestatemanager.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.calculator_seek_amount) SeekBar seekAmount;
    @BindView(R.id.calculator_seek_duration) SeekBar seekDuration;
    @BindView(R.id.calculator_editText_amount) EditText editAmount;
    @BindView(R.id.calculator_editText_duration) EditText editDuration;
    @BindView(R.id.calculator_editText_rate) EditText editRate;
    @BindView(R.id.calculator_editText_payment) TextView textPayment;
    @BindView(R.id.calculator_editText_credit) TextView textCredit;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);

        df = new DecimalFormat("#,###");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        configureViews();

        seekAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 10000;
                progress = progress * 10000;
                editAmount.setText(String.valueOf(progress));
                updatePayment();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editDuration.setText(String.valueOf(progress));
                updatePayment();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                updatePayment();
                if (!editAmount.getText().toString().equals("")) {
                    seekAmount.setProgress(Integer.parseInt(s.toString()));
                }
            }
        });

        editDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                updatePayment();
                if (!editDuration.getText().toString().equals("")) {
                    seekDuration.setProgress(Integer.parseInt(s.toString()));
                }
            }
        });

        editRate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                updatePayment();
            }
        });
    }

    private void configureViews() {

        seekAmount.setMax(600000);
        seekAmount.setProgress(10000);
        seekDuration.setMax(30);
        seekDuration.setProgress(1);
        editAmount.setText("10000");
        editDuration.setText("1");
        editRate.setText("0.00");
        updatePayment();
    }

    private void updatePayment() {

        int amount;
        int duration;
        float rate;

        if (!editAmount.getText().toString().equals("")) {
            String str = editAmount.getText().toString().replaceAll(",", "");
            amount = Integer.parseInt(str);
            editAmount.setText(String.valueOf(df.format(amount)));
        } else {
            amount = 0;
        }

        if (!editDuration.getText().toString().equals("")) {
            duration = Integer.parseInt(editDuration.getText().toString());
        } else {
            duration = 0;
        }

        if (!editRate.getText().toString().equals("")) {
            rate = Float.parseFloat(editRate.getText().toString()) / 100;
        } else {
            rate = 0;
        }

        double payment = 0;
        double credit = 0;

        if (amount > 0 && duration > 0 && rate > 0) {
            payment = (amount * (rate / 12)) /  (1 - Math.pow((1 + rate/12),(-12 * duration)));
            credit = 12 * duration * payment - amount;
        }
        else if (amount > 0 && duration > 0 && rate == 0) {
            payment = amount / (duration * 12);
            credit = 0;
        }
        else {
            payment = 0;
            credit = 0;
        }

        textPayment.setText(String.valueOf(df.format(payment)));
        textCredit.setText(String.valueOf(df.format(credit)));
    }

}