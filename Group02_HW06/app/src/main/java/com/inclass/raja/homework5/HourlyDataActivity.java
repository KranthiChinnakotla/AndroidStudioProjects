package com.inclass.raja.homework5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class HourlyDataActivity extends AppCompatActivity implements HourlyDataAsync.DisplayWeather {

    static ProgressDialog progressDialog;
    String cityName,stateName,url;
    static ArrayList<Weather> listWeatherDetails = new ArrayList<Weather>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listview);

        progressDialog = new ProgressDialog(this);
        listView = (ListView) findViewById(R.id.listView2);
        cityName = getIntent().getExtras().getString("city");
        stateName = getIntent().getExtras().getString("state");
        url = "http://api.wunderground.com/api/37af142f823f9ab7/hourly/q/"+stateName+"/"+cityName+".xml";

        new HourlyDataAsync(this).execute(url);

    }

    @Override
    public void hourlyWeather(final ArrayList<Weather> weatherlist) {


        listWeatherDetails = weatherlist;
        WeatherAdapter adapter = new WeatherAdapter(HourlyDataActivity.this,R.layout.activity_hourly_data,listWeatherDetails);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HourlyDataActivity.this,DetailsActivity.class);
                intent.putExtra("details",listWeatherDetails.get(position));
                startActivity(intent);
            }
        });
    }
}
