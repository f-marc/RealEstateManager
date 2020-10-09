package com.openclassrooms.realestatemanager.features.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.util.StringUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.utils.Utils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConverterActivity extends AppCompatActivity {

    @BindView(R.id.activity_converter_first_edittext) TextView editTextFirst;
    @BindView(R.id.activity_converter_second_edittext) TextView editTextSecond;
    @BindView(R.id.activity_converter_first_textview) TextView textViewFirst;
    @BindView(R.id.activity_converter_second_textview) TextView textViewSecond;
    @BindView(R.id.activity_converter_first_image) ImageView imageViewFirst;
    @BindView(R.id.activity_converter_second_image) ImageView imageViewSecond;
    @BindView(R.id.activity_converter_invert_image) CardView cardViewInvert;
    @BindView(R.id.activity_converter_back_btn) ImageButton buttonBack;
    @BindView(R.id.activity_converter_clean_btn) Button buttonClean;
    @BindView(R.id.activity_converter_0_btn) Button button0;
    @BindView(R.id.activity_converter_1_btn) Button button1;
    @BindView(R.id.activity_converter_2_btn) Button button2;
    @BindView(R.id.activity_converter_3_btn) Button button3;
    @BindView(R.id.activity_converter_4_btn) Button button4;
    @BindView(R.id.activity_converter_5_btn) Button button5;
    @BindView(R.id.activity_converter_6_btn) Button button6;
    @BindView(R.id.activity_converter_7_btn) Button button7;
    @BindView(R.id.activity_converter_8_btn) Button button8;
    @BindView(R.id.activity_converter_9_btn) Button button9;
    private SharedPreferences sharedPreferences;
    private Boolean euroToDollar;
    private DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        ButterKnife.bind(this);

        sharedPreferences = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        euroToDollar = sharedPreferences.getBoolean("euroToDollar", true);

        df = new DecimalFormat("#,###");

        if (euroToDollar) {
            setEuroToDollar();
        } else {
            setDollarToEuro();
        }

        cardViewInvert.setOnClickListener(this::onClick);
        buttonBack.setOnClickListener(this::onClick);
        buttonClean.setOnClickListener(this::onClick);
        button0.setOnClickListener(this::onClick);
        button1.setOnClickListener(this::onClick);
        button2.setOnClickListener(this::onClick);
        button3.setOnClickListener(this::onClick);
        button4.setOnClickListener(this::onClick);
        button5.setOnClickListener(this::onClick);
        button6.setOnClickListener(this::onClick);
        button7.setOnClickListener(this::onClick);
        button8.setOnClickListener(this::onClick);
        button9.setOnClickListener(this::onClick);

        editTextFirst.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if ((s != null) && (s.length() > 0)) {
                    updateSecond(s.toString());
                } else {
                    resetSecond();
                }
            }
        });

    }

    private String addText(String s) {
        return editTextFirst.getText() + s;
    }

    private void updateSecond(String s) {
        int firstText = Integer.parseInt(s);
        if (euroToDollar) {
            editTextSecond.setText(String.valueOf(df.format(Utils.convertEuroToDollar(firstText))));
        } else {
            editTextSecond.setText(String.valueOf(df.format(Utils.convertDollarToEuro(firstText))));
        }
    }

    private void resetSecond() {
        editTextSecond.setText("");
    }

    private void setEuroToDollar() {
        textViewFirst.setText(R.string.eur);
        textViewSecond.setText(R.string.usd);
        imageViewFirst.setImageResource(R.drawable.currency_eur);
        imageViewSecond.setImageResource(R.drawable.currency_usd);
    }

    private void setDollarToEuro() {
        textViewFirst.setText(R.string.usd);
        textViewSecond.setText(R.string.eur);
        imageViewFirst.setImageResource(R.drawable.currency_usd);
        imageViewSecond.setImageResource(R.drawable.currency_eur);
    }

    private void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_converter_invert_image:
                if (euroToDollar) {
                    sharedPreferences.edit().putBoolean("euroToDollar", false).apply();
                    euroToDollar = false;
                    editTextFirst.setText("");
                    setDollarToEuro();
                } else {
                    sharedPreferences.edit().putBoolean("euroToDollar", true).apply();
                    euroToDollar = true;
                    editTextFirst.setText("");
                    setEuroToDollar();
                }
                break;
            case R.id.activity_converter_back_btn:
                if ((editTextFirst.getText() != null) && (editTextFirst.getText().length() > 0)) {
                    editTextFirst.setText(Utils.removeLastCharacter(editTextFirst.getText().toString()));
                }
                break;
            case R.id.activity_converter_clean_btn:
                editTextFirst.setText("");
                break;
            case R.id.activity_converter_0_btn:
                editTextFirst.setText(addText("0"));
                break;
            case R.id.activity_converter_1_btn:
                editTextFirst.setText(addText("1"));
                break;
            case R.id.activity_converter_2_btn:
                editTextFirst.setText(addText("2"));
                break;
            case R.id.activity_converter_3_btn:
                editTextFirst.setText(addText("3"));
                break;
            case R.id.activity_converter_4_btn:
                editTextFirst.setText(addText("4"));
                break;
            case R.id.activity_converter_5_btn:
                editTextFirst.setText(addText("5"));
                break;
            case R.id.activity_converter_6_btn:
                editTextFirst.setText(addText("6"));
                break;
            case R.id.activity_converter_7_btn:
                editTextFirst.setText(addText("7"));
                break;
            case R.id.activity_converter_8_btn:
                editTextFirst.setText(addText("8"));
                break;
            case R.id.activity_converter_9_btn:
                editTextFirst.setText(addText("9"));
                break;
        }
    }
}