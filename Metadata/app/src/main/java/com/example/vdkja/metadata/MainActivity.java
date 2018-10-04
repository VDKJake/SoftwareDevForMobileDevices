package com.example.vdkja.metadata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
    }

    private void initializeUI()
    {
        ImageObject cat1 = new ImageObject()
    }

    public void buttonHandler(View v)
    {
        Intent i = new Intent(getApplicationContext(), FormActivity.class);

        startActivityForResult(i, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(intent != null)
        {
            ArrayList<ImageObject> imageObjects = intent.getParcelableArrayListExtra("IMAGE_DATA");
            ImageObject iO = imageObjects.get(0);

        }

    }
}

Name
URL obtained
Keywords
Date was obtained
Share Image? Toggle
Who obtained
Rating

