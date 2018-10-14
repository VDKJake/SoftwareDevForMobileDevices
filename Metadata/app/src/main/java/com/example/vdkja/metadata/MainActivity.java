package com.example.vdkja.metadata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageObject cat1;
    private ImageObject cat2;
    private ImageObject cat3;
    private ImageObject cat4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeUI();
    }

    private void initializeUI()
    {
        cat1 = new ImageObject("Banana Cat", "https://images.boredomfiles.com/wp-content/uploads/sites/7/2018/05/x720-Nx--768x432.jpg",
                "cat, banana", "28/09/2018", "banana@kitty.com", false, R.drawable.cat_01, 5.0f);
        cat2 = new ImageObject("Pondering Cat", "https://www.cats.org.uk/uploads/images/featurebox_sidebar_kids/grief-and-loss.jpg",
                "cat, pondering", "28/09/2018", "ponderer@kitty.com", true, R.drawable.cat_02, 4.0f);
        cat3 = new ImageObject("Curious Cat", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1024px-Cat03.jpg",
                "cat, curious", "28/09/2018", "curious@kitty.com", false, R.drawable.cat_03, 2.0f);
        cat4 = new ImageObject("Loving Cat", "https://www.thesprucepets.com/thmb/MaAxmJrRyLm5CdZ_qjqldDryBzA=/960x0/filters:no_upscale():max_bytes(150000):strip_icc()/Cats-cuddling-GettyImages-154502253-5887e0e43df78c2ccdb61dc0.jpg",
                "cat, loving, caring", "28/09/2018", "loving@kitty.com", false, R.drawable.cat_04, 5.0f);

        ImageButton button1 = (ImageButton)findViewById(R.id.imageButton1);
        button1.setImageResource(R.drawable.cat_01);
        ImageButton button2 = (ImageButton)findViewById(R.id.imageButton2);
        button2.setImageResource(R.drawable.cat_02);
        ImageButton button3 = (ImageButton)findViewById(R.id.imageButton3);
        button3.setImageResource(R.drawable.cat_03);
        ImageButton button4 = (ImageButton)findViewById(R.id.imageButton4);
        button4.setImageResource(R.drawable.cat_04);

        ((TextView)findViewById(R.id.cat1Text)).setText(cat1.toString());
        ((TextView)findViewById(R.id.cat2Text)).setText(cat2.toString());
        ((TextView)findViewById(R.id.cat3Text)).setText(cat3.toString());
        ((TextView)findViewById(R.id.cat4Text)).setText(cat4.toString());
    }

    public void buttonHandler(View v)
    {
        Intent i = new Intent(getApplicationContext(), FormActivity.class);
        switch(v.getId())
        {
            case R.id.imageButton1:
                i.putExtra("ImageObject", cat1);
                break;
            case R.id.imageButton2:
                i.putExtra("ImageObject", cat2);
                break;
            case R.id.imageButton3:
                i.putExtra("ImageObject", cat3);
                break;
            case R.id.imageButton4:
                i.putExtra("ImageObject", cat4);
                break;
        }
        startActivityForResult(i, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode == 0)
        {
            if (resultCode == RESULT_OK)
            {
                if(intent != null)
                {
                    ArrayList<ImageObject> imageObjects = intent.getParcelableArrayListExtra("ImageObject");
                    ImageObject iO = imageObjects.get(0);

                    switch(iO.getImageID())
                    {
                        case R.drawable.cat_01:
                            cat1 = iO;
                            ((TextView)findViewById(R.id.cat1Text)).setText(iO.toString());
                            break;
                        case R.drawable.cat_02:
                            cat2 = iO;
                            ((TextView)findViewById(R.id.cat2Text)).setText(iO.toString());
                            break;
                        case R.drawable.cat_03:
                            cat3 = iO;
                            ((TextView)findViewById(R.id.cat3Text)).setText(iO.toString());
                            break;
                        case R.drawable.cat_04:
                            cat4 = iO;
                            ((TextView)findViewById(R.id.cat4Text)).setText(iO.toString());
                            break;
                    }
                }
                else
                {
                    Log.i("INTENT", "Intent Empty");
                }
            }
            else
            {
                Log.i("INTENT", "Result not okay");
            }
        }
        else
        {
            Log.i("INTENT", "Code does not match");
        }
    }
}

