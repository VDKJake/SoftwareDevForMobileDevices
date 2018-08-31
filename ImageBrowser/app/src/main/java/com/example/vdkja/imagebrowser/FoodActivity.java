package com.example.vdkja.imagebrowser;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        initializeUI();
    }

    // Takes the values out of the intent from the other activity
    // and grabs them from the resources. Then finds the elements from the
    // layout and sets their corresponding features to the passed in
    // resources
   private void initializeUI()
   {
       Bundle extras = getIntent().getExtras();

       String desc = getResources().getString(extras.getInt("description"));
       Drawable image = getResources().getDrawable(extras.getInt("image"));

       TextView textViewFoodDesc = findViewById(R.id.foodDesc);
       ImageView imageViewFood = findViewById(R.id.foodImage);

       textViewFoodDesc.setText(desc);
       imageViewFood.setImageDrawable(image);
   }
}
