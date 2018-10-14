package com.example.vdkja.metadata;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private int imageID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        InitializeUI();
    }

    private void InitializeUI()
    {
        Intent imageIntent = getIntent();
        ImageObject iO = imageIntent.getParcelableExtra("ImageObject");
        if(iO != null)
        {
            ((EditText)findViewById(R.id.eName)).setText(iO.getName());
            ((EditText)findViewById(R.id.eURL)).setText(iO.getURL());
            ((EditText)findViewById(R.id.eKeywords)).setText(iO.getKeywords());
            ((EditText)findViewById(R.id.eDate)).setText(dateFormat.format(iO.getDate()));
            ((ToggleButton)findViewById(R.id.tShare)).setChecked(iO.isShared());
            ((EditText)findViewById(R.id.eEmail)).setText(iO.getEmail());
            ((RatingBar)findViewById(R.id.rRating)).setRating(iO.getRating());
            imageID = iO.getImageID();
        }
    }

    @Override
    public void onBackPressed()
    {
        if(!((EditText)findViewById(R.id.eName)).getText().toString().matches(""))
        {
            if(!((EditText)findViewById(R.id.eEmail)).getText().toString().matches("") && Patterns.EMAIL_ADDRESS.matcher(((EditText)findViewById(R.id.eEmail)).getText().toString()).matches()) {
                Intent i = new Intent();
                String name = ((EditText) findViewById(R.id.eName)).getText().toString();
                String URL = ((EditText) findViewById(R.id.eURL)).getText().toString();
                String keywords = ((EditText) findViewById(R.id.eKeywords)).getText().toString();
                String date = ((EditText) findViewById(R.id.eDate)).getText().toString();
                boolean share = ((ToggleButton) findViewById(R.id.tShare)).isChecked();
                String email = ((EditText) findViewById(R.id.eEmail)).getText().toString();
                float rating = ((RatingBar) findViewById(R.id.rRating)).getRating();
                int imID = imageID;
                ImageObject imageObject = new ImageObject(name, URL, keywords, date, email, share, imID, rating);

                ArrayList<ImageObject> imageObjects = new ArrayList<ImageObject>();
                imageObjects.add(imageObject);
                i.putParcelableArrayListExtra("ImageObject", imageObjects);
                setResult(RESULT_OK, i);
                super.onBackPressed();
            }
            else
            {
                ((EditText)findViewById(R.id.eEmail)).setError("Please Enter A Valid Email");
            }
        }
        else
        {
            Log.i("VALIDATION", "Name Empty");
            ((EditText)findViewById(R.id.eName)).setError("Please Enter Image Name");
        }
    }
}
