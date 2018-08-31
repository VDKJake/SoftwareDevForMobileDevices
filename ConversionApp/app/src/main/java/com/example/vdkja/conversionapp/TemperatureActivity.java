package com.example.vdkja.conversionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity
{
    // The code for this orientation change was sourced and modified from user
    // hiBrianLee on stackoverflow - https://stackoverflow.com/questions/13022677/save-state-of-activity-when-orientation-changes-android

    // These variables are used for the orientation change, the FAHREN_VALUE is used to store
    // the value for the fahrenheit value when the orientation is changed. fahrenTextView is used
    // to access the TextView in the layout
    private static String FAHREN_VALUE = "0";
    private TextView fahrenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        initializeUI();

        // This code sets the fahrenTextView and if there is a saved instance to load, it loads
        // the FAHREN_VALUE and sets the fahrenTextView to that value, effectively saving the value
        // when the orientation changes
        fahrenTextView = findViewById(R.id.textFahren);
        if(savedInstanceState != null)
        {
            CharSequence savedFahrenText = savedInstanceState.getCharSequence(FAHREN_VALUE);
            fahrenTextView.setText(savedFahrenText);
        }
    }

    // onSaveInstanceState is called when the orientation changes, so it is used to save
    // the instance's state and store the current value of the fahrenheit to FAHREN_VALUE
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(FAHREN_VALUE, fahrenTextView.getText());
    }

    void initializeUI()
    {
        Button convert = findViewById(R.id.buttonTemp);
        convert.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            convertToFahren();
        }
    };

    void convertToFahren()
    {
        EditText celsius = findViewById(R.id.editCelsius);
        TextView fahrenheit = findViewById(R.id.textFahren);
        fahrenheit.setText(convertFahren(celsius.getText().toString()) + " F");
    }

    String convertFahren(String sCelsius)
    {
        double celsius = Double.parseDouble(sCelsius);
        double fahrenheit = celsius * (9.0 / 5.0) + 32;
        return String.format("%3.2f", fahrenheit);
    }
}
