package com.example.vdkja.conversionapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    void initializeUI()
    {
        Button convert = findViewById(R.id.buttonConvert);
        convert.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            convertToMetric();
        }
    };

    void convertToMetric()
    {
        EditText inches = findViewById(R.id.editInches);
        EditText feet = findViewById(R.id.editFeet);
        EditText miles = findViewById(R.id.editMiles);
        TextView outputView = findViewById(R.id.textMetric);
        outputView.setText(convertToCM(inches.getText().toString(), feet.getText().toString(), miles.getText().toString()));
    }

    String convertToCM(String inches, String feet, String miles)
    {
        double inchesVal = Double.parseDouble(inches) * 2.54;
        double feetVal = Double.parseDouble(feet) * 30.48;
        double  milesVal = Double.parseDouble(miles) * 160934.4;

        double centiM = inchesVal + feetVal + milesVal;

        CheckBox metresCheck = findViewById(R.id.checkBMetres);
        String conversionResult;
        if(metresCheck.isChecked())
        {
            centiM /= 100;
            conversionResult = "m";
        } else
        {
            conversionResult = "cm";
        }
        return String.format("%.2f", centiM) + conversionResult;
    }
}
