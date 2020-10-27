package com.openclassrooms.realestatemanager.features.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.openclassrooms.realestatemanager.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.calculator_linear) LinearLayout linearLayout;
    @BindView(R.id.calculator_seek_amount) Slider sliderAmount;
    @BindView(R.id.calculator_seek_duration) Slider sliderDuration;
    @BindView(R.id.calculator_seek_rate) Slider sliderRate;
    @BindView(R.id.calculator_editText_amount) EditText editAmount;
    @BindView(R.id.calculator_editText_duration) EditText editDuration;
    @BindView(R.id.calculator_editText_rate) EditText editRate;
    @BindView(R.id.calculator_editText_payment) TextView textPayment;
    @BindView(R.id.calculator_editText_credit) TextView textCredit;

    private DecimalFormat df;

    private final int maxAmount = 600000;
    private final int maxDuration = 30;
    private final int maxRate = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);

        df = new DecimalFormat("#,###");
        df.setRoundingMode(RoundingMode.HALF_EVEN);

        configureViews();
        enableSliderListener();

        linearLayout.setOnClickListener(v -> {
            editAmount.clearFocus();
            editDuration.clearFocus();
            editRate.clearFocus();
        });

        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                updatePayment();
                disableSliderListener();
                if (!editAmount.getText().toString().equals("")) {
                    float f = Float.parseFloat(s.toString());
                    if (f <= maxAmount) {
                        float modulo = f % 10000;
                        if (modulo < 5000) {
                            f = (f - modulo) / 10000 * 10000;
                            Log.i("test 1", "" + f);
                        } else {
                            f = (f - modulo) / 10000 * 10000 + 10000;
                            Log.i("test 2", "" + f);
                        }
                        Log.i("test 3", "" + f);
                        sliderAmount.setValue(Math.round(f));
                    } else {
                        sliderAmount.setValue(maxAmount);
                    }
                }
                enableSliderListener();
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
                disableSliderListener();
                if (!editDuration.getText().toString().equals("")) {
                    float f = Float.parseFloat(s.toString());
                    if (f <= maxDuration) {
                        sliderDuration.setValue(Math.round(f));
                    } else {
                        sliderDuration.setValue(maxDuration);
                    }
                }
                enableSliderListener();
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
                disableSliderListener();
                if (!editRate.getText().toString().equals("")) {
                    float f = Float.parseFloat(s.toString());
                    if (f <= maxRate) {
                        sliderRate.setValue(f);
                    } else {
                        sliderRate.setValue(maxRate);
                    }
                }
                enableSliderListener();
            }
        });
    }

    private void configureViews() {

        sliderAmount.setValueFrom(0);
        sliderAmount.setValueTo(maxAmount);
        sliderAmount.setStepSize(10000);
        sliderAmount.setLabelFormatter(value -> {
            NumberFormat format = NumberFormat.getCurrencyInstance();
            format.setMaximumFractionDigits(0);
            format.setCurrency(Currency.getInstance("USD"));
            return format.format(value);
        });

        sliderDuration.setValueFrom(0);
        sliderDuration.setValueTo(maxDuration);
        sliderDuration.setStepSize(1);

        sliderRate.setValueFrom(0);
        sliderRate.setValueTo(maxRate);
        sliderRate.setStepSize(0.01f);

        editAmount.setText("10000");
        editDuration.setText("1");
        editRate.setText("0.00");

        updatePayment();
    }

    private void enableSliderListener() {
        sliderAmount.addOnChangeListener((slider, value, fromUser) -> editAmount.setText(String.valueOf(Math.round(value))));
        sliderDuration.addOnChangeListener((slider, value, fromUser) -> editDuration.setText(String.valueOf(Math.round(value))));
        sliderRate.addOnChangeListener((slider, value, fromUser) -> editRate.setText(String.valueOf(value)));
    }

    private void disableSliderListener() {
        sliderAmount.clearOnChangeListeners();
        sliderDuration.clearOnChangeListeners();
        sliderRate.clearOnChangeListeners();
    }

    private void updatePayment() {

        float amount;
        float duration;
        float rate;

        if (!editAmount.getText().toString().equals("")) {
            String str = editAmount.getText().toString();
            amount = Float.parseFloat(str);
            //editAmount.setText(df.format(amount));
        } else {
            amount = 0;
        }

        if (!editDuration.getText().toString().equals("")) {
            duration = Float.parseFloat(editDuration.getText().toString());
        } else {
            duration = 0;
        }

        if (!editRate.getText().toString().equals("")) {
            rate = Float.parseFloat(editRate.getText().toString()) / 100;
        } else {
            rate = 0;
        }

        double payment;
        double credit;

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

        textPayment.setText(df.format(payment));
        textCredit.setText(df.format(credit));
    }

}