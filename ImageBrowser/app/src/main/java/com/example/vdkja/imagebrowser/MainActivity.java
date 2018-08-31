package com.example.vdkja.imagebrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // Const keys that are needed to pass the data to the image activity
    private static final String FOOD_DESC_KEY = "description";
    private static final String FOOD_IMAGE_KEY = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Click Handlers that are used by each of the images, runs showImage
    // and passes in their corresponding descriptions (from strings.xml)
    // and image
    public void mayo1ClickHandler(View v)
    {
        showImage(R.string.mayo1, R.drawable.mayo2);
    }

    public void mayo2ClickHandler(View v)
    {
        showImage(R.string.mayo2, R.drawable.mayo1);
    }

    public void mayo3ClickHandler(View v)
    {
        showImage(R.string.mayo3, R.drawable.mayo3);
    }

    public void mayo4ClickHandler(View v)
    {
        showImage(R.string.mayo4, R.drawable.mayo4);
    }

    // Puts the passed in description and image into a bundle and then an
    // intent. Then starts the other activity
    private void showImage(int desc, int id)
    {
        Bundle dataBundle = new Bundle();

        dataBundle.putInt(FOOD_DESC_KEY, desc);
        dataBundle.putInt(FOOD_IMAGE_KEY, id);

        Intent foodDetailIntent = new Intent(this, FoodActivity.class);
        foodDetailIntent.putExtras(dataBundle);

        startActivity(foodDetailIntent);
    }
}
