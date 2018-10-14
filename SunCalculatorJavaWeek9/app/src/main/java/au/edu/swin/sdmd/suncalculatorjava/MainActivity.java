package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class MainActivity extends AppCompatActivity {

    ArrayList<Location> locations = new ArrayList<>();
    DatePicker dp;
    Spinner locSpin;

    public class Location
    {
        private String lName;
        private double lLat;
        private double lLon;
        private TimeZone lTZ;

        public Location(String name, double lat, double lon, TimeZone tz)
        {
            lName = name;
            lLat = lat;
            lLon = lon;
            lTZ = tz;
        }

        public String getlName() {
            return lName;
        }

        public double getlLat() {
            return lLat;
        }

        public double getlLon() {
            return lLon;
        }

        public TimeZone getlTZ() {
            return lTZ;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    private void initializeUI() {
        dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        readValues();
        locSpin = findViewById(R.id.locSpinner);

        ArrayList<String> spinArray = new ArrayList<>();
        for(Location l : locations)
        {
            spinArray.add(l.getlName());
        }

        ArrayAdapter<String> spinArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinArray);
        locSpin.setAdapter(spinArrayAdapter);

        locSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                updateTime(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), locations.get(adapterView.getSelectedItemPosition()));
            }
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                return;
            }
        });

        updateTime(year, month, day, locations.get(locSpin.getSelectedItemPosition()));
    }

    private void readValues()
    {
        InputStream inputStream = getResources().openRawResource(R.raw.au_locations);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String inputLine;

        try{
            while((inputLine = br.readLine()) != null)
            {
                String[] inputSeparated = inputLine.split(",");
                locations.add(new Location(inputSeparated[0], Double.parseDouble(inputSeparated[1]), Double.parseDouble(inputSeparated[2]), TimeZone.getTimeZone(inputSeparated[3])));
            }
        } catch(IOException e)
        {

        }
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String[] prefsSplit = sharedPref.getString(getString(R.string.custom_location), "Whyalla,-33.03333,137.58333,Australia/Adelaide").split("_");
        for(String s : prefsSplit)
        {
            String[] inputSeparated = s.split(",");
            locations.add(new Location(inputSeparated[0], Double.parseDouble(inputSeparated[1]), Double.parseDouble(inputSeparated[2]), TimeZone.getTimeZone(inputSeparated[3])));
        }
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth, Location loc) {
        GeoLocation geolocation = new GeoLocation(loc.getlName(), loc.getlLat(), loc.getlLon(), loc.getlTZ());
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        TextView sunriseTV = findViewById(R.id.sunriseTimeTV);
        TextView sunsetTV = findViewById(R.id.sunsetTimeTV);
        Log.d("SUNRISE Unformatted", srise+"");

        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            updateTime(year, monthOfYear, dayOfMonth, locations.get(locSpin.getSelectedItemPosition()));
        }
    };

    public void AddLocationHandler(View view)
    {
        Intent addLocIntent = new Intent(this, AddLocationActivity.class);

        startActivity(addLocIntent);
    }
}
