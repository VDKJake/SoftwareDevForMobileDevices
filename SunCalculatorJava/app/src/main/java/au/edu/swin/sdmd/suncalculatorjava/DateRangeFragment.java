package au.edu.swin.sdmd.suncalculatorjava;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class DateRangeFragment extends Fragment {

    private TextView sunSetView;
    private TextView sunRiseView;
    private DatePicker dp;
    private DatePicker dp2;
    private String location;
    private double lat;
    private double lon;
    private TimeZone tz;
    private TableLayout tLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_range, container, false);
        InitializeUI(view);
        return view;
    }

    public void InitializeUI(View view)
    {
        dp = view.findViewById(R.id.datePicker01);
        dp2 = view.findViewById(R.id.datePicker02);
        tLayout = view.findViewById(R.id.TableLayout01);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler);
        dp2.init(year,month,day,date2ChangeHandler);// setup initial values and reg. handler

        //sunSetView = view.findViewById(R.id.sunsetTimeTV);
        //sunRiseView = view.findViewById(R.id.sunriseTimeTV);
        location = "Melbourne";
        lat = -37.50;
        lon = 145.01;
        tz = TimeZone.getDefault();
        //dp.init(year,month,day,dateChangeHandler);
        //updateTime(year, month, day);
    }

    private void updateTime(int year, int monthOfYear, int dayOfMonth, int year2, int monthOfYear2, int dayOfMonth2) {
        GeoLocation geolocation = new GeoLocation(location, lat, lon, tz);
        Log.i("GEO_TZ", geolocation.getTimeZone().getDisplayName());
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(year2, monthOfYear2, dayOfMonth2);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyy");

        tLayout.removeAllViews();
        TableRow row = new TableRow(getContext());
        TextView empty = new TextView(getContext());
        TextView sunrise = new TextView(getContext());
        TextView sunset = new TextView(getContext());
        empty.setText("Date");
        sunrise.setText("Sunrise");
        sunset.setText("Sunset");
        row.addView(empty);
        row.addView(sunrise);
        row.addView(sunset);
        tLayout.addView(row);

        while(!ac.getCalendar().after(cal2))
        {
            row = new TableRow(getContext());
            TextView date = new TextView(getContext());
            TextView sunRise = new TextView(getContext());
            TextView sunSet = new TextView(getContext());

            Date currentDate = ac.getCalendar().getTime();
            Date srise = ac.getSunrise();
            Date sset = ac.getSunset();

            date.setText(sdf1.format(currentDate));
            sunRise.setText(sdf.format(srise));
            sunSet.setText(sdf.format(sset));

            row.addView(date);
            row.addView(sunRise);
            row.addView(sunSet);


            tLayout.addView(row);
            ac.getCalendar().add(Calendar.DATE, 1);
        }
    }

    public void UpdateLocation(String location, double lat, double lon, String tz)
    {
        this.location = location;
        this.lat = lat;
        this.lon = lon;
        this.tz = TimeZone.getTimeZone(tz);
        Log.i("TIME_ZONE", "Time Zone: " + this.tz.getDisplayName());
        //updateTime(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
        Log.i("DP_TIME", "Year: " + dp.getYear() + " Month: " + dp.getMonth() + " Day: " + dp.getDayOfMonth());
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
                updateTime(year, monthOfYear, dayOfMonth, dp2.getYear(), dp2.getMonth(), dp2.getDayOfMonth());
            }
            else
            {
                firstRun = true;
            }
        }
    };

    DatePicker.OnDateChangedListener date2ChangeHandler = new DatePicker.OnDateChangedListener()
    {
        boolean firstRun = true;
        public void onDateChanged(DatePicker dp2, int year, int monthOfYear, int dayOfMonth)
        {
            if(firstRun)
            {
                firstRun = false;
                updateTime(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), year, monthOfYear, dayOfMonth);
            }
            else
            {
                firstRun = true;
            }
        }
    };
}
