package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class MainActivity extends AppCompatActivity{

    private FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    private ViewPager vPager;
    private FragmentPagerAdapter pAdapter;
    private String[] locationArray;
    private Double[] latArray = {-37.50, -33.50, -35.00, -27.50, -42.50, -12.50, -34.50, -31.50};
    private Double[] lonArray = {144.50, 151.00, 149.00, 153.00, 147.00, 130.50, 138.50, 115.50};
    private String[] tzArray = {"Australia/Melbourne", "Australia/Sydney", "Australia/ACT", "Australia/Brisbane", "Australia/Hobart", "Australia/Darwin", "Australia/Adelaide", "Australia/Perth"};
    private android.support.v7.widget.ShareActionProvider shareActionProvider;

    public class FragmentPagerAdapter extends FragmentStatePagerAdapter{
        private final List<Fragment> mFragList = new ArrayList<>();
        private final List<Fragment> mFragTitleList = new ArrayList<>();

        public FragmentPagerAdapter(FragmentManager fM)
        {
            super(fM);
        }

        public void addFragment(Fragment frag)
        {
            mFragList.add(frag);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragList.get(position);
        }

        @Override
        public int getCount() {
            return mFragList.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu, menu);

        MenuItem item = menu.findItem(R.id.menuShare);
        shareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menuShare:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setShareIntent(Intent shareIntent)
    {
        if(shareActionProvider != null)
        {
            shareActionProvider.setShareIntent(shareIntent);
        }
    }

    private void initializeUI() {

        vPager = (ViewPager) findViewById(R.id.fragmentPager);
        pAdapter = new FragmentPagerAdapter(getSupportFragmentManager());
        pAdapter.addFragment(new DateLocationFragment());
        pAdapter.addFragment(new DateRangeFragment());
        vPager.setAdapter(pAdapter);

        locationArray = getResources().getStringArray(R.array.location_array);

        Spinner spinnerLocation = (Spinner)findViewById(R.id.locSpinner);

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                int index = adapterView.getSelectedItemPosition();
                Log.i("NEW_LOCATION", "new location, vPager current item: " + index);
                if(vPager.getCurrentItem() == 0)
                {
                    ((DateLocationFragment)pAdapter.getItem(vPager.getCurrentItem())).UpdateLocation(locationArray[index], latArray[index], lonArray[index], tzArray[index]);
                } else if(vPager.getCurrentItem() == 1)
                {
                    ((DateRangeFragment)pAdapter.getItem(vPager.getCurrentItem())).UpdateLocation(locationArray[index], latArray[index], lonArray[index], tzArray[index]);
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView)
            {
                return;
            }
        });

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener()
        {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
            {
                vPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft)
            {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft)
            {

            }
        };

        actionBar.addTab(actionBar.newTab().setText("Single").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Range").setTabListener(tabListener));

        vPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
        {
            public void onPageSelected(int position)
            {
                actionBar.setSelectedNavigationItem(position);
            }
        });
    }
}
