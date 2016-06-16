package com.inclass.raja.homework5;

import android.app.ActionBar;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class CityData_Activity extends TabActivity {
    String cityName,stateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_data_);
        cityName = getIntent().getExtras().getString(MainActivity.CITY_KEY);
        stateName = getIntent().getExtras().getString(MainActivity.STATE_KEY);
        TabHost tabHost = getTabHost();
        TabHost.TabSpec hourlySpec = tabHost.newTabSpec("Hourly");
        TabHost.TabSpec forecastSpec = tabHost.newTabSpec("forecast");
        hourlySpec.setIndicator("Hourly Data");
        forecastSpec.setIndicator("Forecast Data");
        Intent hourIntent = new Intent(CityData_Activity.this,HourlyDataActivity.class);
        Intent forecastIntent = new Intent(CityData_Activity.this,ForecastActivity.class);
        hourIntent.putExtra("city",cityName);
        hourIntent.putExtra("state", stateName);
        forecastIntent.putExtra("city",cityName);
        forecastIntent.putExtra("state", stateName);
        hourlySpec.setContent(hourIntent);
        forecastSpec.setContent(forecastIntent);
        tabHost.addTab(hourlySpec);
        tabHost.addTab(forecastSpec);


    }

}
