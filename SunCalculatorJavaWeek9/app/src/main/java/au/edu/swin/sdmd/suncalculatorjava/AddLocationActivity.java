package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class AddLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
    }

    public void AddListener(View view) {
        String name = ((EditText) findViewById(R.id.etName)).getText().toString();
        String lat = ((EditText) findViewById(R.id.etLat)).getText().toString();
        String lon = ((EditText) findViewById(R.id.etLon)).getText().toString();
        Spinner gmtSpin = findViewById(R.id.gmtSpinner);
        String gmtString = gmtSpin.getSelectedItem().toString();
        TimeZone tz = TimeZone.getTimeZone(gmtString);

        String locationString = name + "," + lat + "," + lon + "," + gmtString;

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String prefsString = sharedPref.getString(getString(R.string.custom_location), "Whyalla,-33.03333,137.58333,Australia/Adelaide") + "_" + locationString;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.custom_location), prefsString);
        editor.commit();

        Intent addLocIntent = new Intent(this, MainActivity.class);

        startActivity(addLocIntent);
    }
}
