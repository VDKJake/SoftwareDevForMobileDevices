package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class DateLocationFragment extends Fragment {

    private TextView sunSetView;
    private TextView sunRiseView;
    private DatePicker dp;
    private String location;
    private double lat;
    private double lon;
    private TimeZone tz;
    private OnFragmentInteractionListener mListener;
    private boolean ready = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_loc, container, false);
        InitializeUI(view);
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener)context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(String s);
    }

    public void InitializeUI(View view)
    {
        dp = view.findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler

        sunSetView = view.findViewById(R.id.sunsetTimeTV);
        sunRiseView = view.findViewById(R.id.sunriseTimeTV);
        location = "Melbourne";
        lat = -37.50;
        lon = 145.01;
        tz = TimeZone.getDefault();
        dp.init(year,month,day,dateChangeHandler);
        updateTime(year, month, day);
        ready = true;
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        GeoLocation geolocation = new GeoLocation(location, lat, lon, tz);
        Log.i("GEO_TZ", geolocation.getTimeZone().getDisplayName());
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        sunRiseView.setText(sdf.format(srise));
        sunSetView.setText(sdf.format(sset));
        if(ready == true)
        {
            mListener.onFragmentInteraction("uhh");
        }
    }

    public void UpdateLocation(String location, double lat, double lon, String tz)
    {
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.tz = TimeZone.getTimeZone(tz);
        Log.i("TIME_ZONE", "Time Zone: " + this.tz.getDisplayName());
        updateTime(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
        Log.i("DP_TIME", "Year: " + dp.getYear() + " Month: " + dp.getMonth() + " Day: " + dp.getDayOfMonth());
    }

    public String ShareString()
    {
        Log.i("SUNRISE/SETVIEW" , sunRiseView.getText() + " " + sunSetView.getText());
        return "Sunrise: " + sunRiseView.getText() + " Sunset: " + sunSetView.getText() + ", " + dp.getDayOfMonth() + "/" + dp.getMonth() + 1 + "/" + dp.getYear();
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        boolean firstRun = true;
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            if(firstRun)
            {
                firstRun = false;
                Log.i("DATE_CHANGED", "memes");
                updateTime(year, monthOfYear, dayOfMonth);
            }
            else
            {
                firstRun = true;
            }
        }
    };
}
