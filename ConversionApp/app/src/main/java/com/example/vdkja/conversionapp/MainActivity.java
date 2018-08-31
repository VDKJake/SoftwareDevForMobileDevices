package com.example.vdkja.conversionapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    }

    // These click handlers are run when either the temperature or distance button
    // is clicked, and they are linked in the activity's layout file. Each creates
    // a new intent with their respective Activity.java files, and starts the
    // activity.
    public void tempClickHandler(View v)
    {
        Intent temperature = new Intent(this, TemperatureActivity.class);
        startActivity(temperature);
    }

    public void distClickHandler(View v)
    {
        Intent distance = new Intent(this, DistanceActivity.class);
        startActivity(distance);
    }

}
