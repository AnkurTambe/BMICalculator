package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioButton otherButton;
    private EditText age;
    private EditText weight;
    private EditText feet;
    private EditText inches;
    private Button calculateButton;
    private TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
    }

    private void findViews() {
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        otherButton = findViewById(R.id.radio_button_other);
        weight = findViewById(R.id.edit_text_weight);
        age = findViewById(R.id.edit_text_age);
        feet = findViewById(R.id.edit_text_feet);
        inches = findViewById(R.id.edit_text_inches);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);
    }

    private void calculate() {
        String w = weight.getText().toString();
        String a = age.getText().toString();
        String f = feet.getText().toString();
        String i = inches.getText().toString();

        if (w.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please give proper information about your weight", Toast.LENGTH_SHORT).show();
            return;
        }
        if (a.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please give proper information about your age", Toast.LENGTH_SHORT).show();
            return;
        }
        if (i.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please give proper information about your height(inches)", Toast.LENGTH_SHORT).show();
            return;
        }
        if (f.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please give proper information about your height(feet)", Toast.LENGTH_SHORT).show();
            return;
        }

        double we = Double.parseDouble(w);
        double ag = Double.parseDouble(a);
        double fe = Double.parseDouble(f);
        double in = Double.parseDouble(i);

        double ins = (fe * 12) + in;
        double me = (ins * 0.0254);
        double bmi = we / (me * me);

        DecimalFormat fo = new DecimalFormat("0.00");
        String bmiResult = fo.format(bmi);

        String finalResult;

        if (ag >= 18) {
            if (maleButton.isChecked()) {
                if (bmi < 18) {
                    finalResult = bmiResult + " - You Are An Underweight Male";
                } else if (bmi > 25) {
                    finalResult = bmiResult + " - You Are An Overweight Male";
                } else {
                    finalResult = bmiResult + " - You Are A Healthy Male";
                }
            } else if (femaleButton.isChecked()) {
                if (bmi < 18) {
                    finalResult = bmiResult + " - You Are An Underweight Female";
                } else if (bmi > 25) {
                    finalResult = bmiResult + " - You Are An Overweight Female";
                } else {
                    finalResult = bmiResult + " - You Are A Healthy Female";
                }
            } else if (otherButton.isChecked()) {
                if (bmi < 18) {
                    finalResult = bmiResult + " - You Are Underweight";
                } else if (bmi > 25) {
                    finalResult = bmiResult + " - You Are Overweight";
                } else {
                    finalResult = bmiResult + " - You Are Healthy";
                }
            } else {
                Toast.makeText(MainActivity.this, "Please give proper information about your gender", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            if (!((femaleButton.isChecked()) || (maleButton.isChecked()) || (otherButton.isChecked()))) {
                Toast.makeText(MainActivity.this, "Please give proper information about your gender", Toast.LENGTH_SHORT).show();
                return;
            } else
                finalResult = bmiResult + " - as you are under 18, for more information related to your BMI contact a doctor";
        }
        resultText.setText(finalResult);
    }
}