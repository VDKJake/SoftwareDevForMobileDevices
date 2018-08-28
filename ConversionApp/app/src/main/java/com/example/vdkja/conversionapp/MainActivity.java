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
        EditText inches = findViewById(R.id.editInches);
        EditText feet = findViewById(R.id.editFeet);
        EditText miles = findViewById(R.id.editMiles);
        /*inches.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    EditText feet = findViewById(R.id.editFeet);
                    feet.setText("");
                }
            }
        });*/
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
        String cMOutput = convertToCM(inches.getText().toString());
        TextView outputView = findViewById(R.id.textMetric);
        outputView.setText(cMOutput);
    }

    String convertToCM(String inchesValue)
    {
        double inches = Double.parseDouble(inchesValue);
        double centiM = inches * 2.54;
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
