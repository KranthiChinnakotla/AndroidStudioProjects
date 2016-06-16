package com.inclass.raja.homework5;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ForecastActivity extends AppCompatActivity implements GetForecast.DisplayForecast {

    ListView listView;
    static ProgressDialog progressDialog;
    static String cityName, stateName;
    String url;
    TextView textViewForecast;
    Forecast forecast;
    ForecastAdapter forecastAdapter;
    ArrayList<Forecast> listOfForecasts;
    int posit;
    DatabaseDataManager dmNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        progressDialog = new ProgressDialog(ForecastActivity.this);
        textViewForecast = (TextView) findViewById(R.id.textView_ForecastActivity);
        cityName = getIntent().getExtras().getString("city");
        stateName = getIntent().getExtras().getString("state");
        textViewForecast.setText("Current Location: " + cityName + "," + stateName);
        url = "http://api.wunderground.com/api/37af142f823f9ab7/forecast10day/q/" + stateName + "/" + cityName + ".xml";
        new GetForecast(this).execute(url);
        listView = (ListView) findViewById(R.id.listView_forecast);


    }


    @Override
    public void forecastWeather(final ArrayList<Forecast> forecastList) {

        listOfForecasts = forecastList;

        forecastAdapter = new ForecastAdapter(ForecastActivity.this, R.layout.forecast_adapter, listOfForecasts);
        listView.setAdapter(forecastAdapter);
        forecastAdapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posit = position;
                Intent intent = new Intent(ForecastActivity.this, AddNotesActivity.class);
                intent.putExtra("date", listOfForecasts.get(position));
                startActivityForResult(intent, 1);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dmNotes = new DatabaseDataManager(ForecastActivity.this,"citynotes");
                dmNotes.deleteNotes(new Notes(listOfForecasts.get(position).getDate(),listOfForecasts.get(position).getNotes()));
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView_saveNote);
                imageView.setVisibility(view.INVISIBLE);
                listOfForecasts.get(position).setNotes(null);
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == ForecastActivity.RESULT_OK) {

                forecast = (Forecast) data.getSerializableExtra("notes");
                Toast.makeText(ForecastActivity.this, "Notes Saved Succesfully", Toast.LENGTH_LONG).show();
                if (forecast != null) {
                    listOfForecasts.set(posit, forecast);
                }
            }
        }
        forecastAdapter.setNotifyOnChange(true);
        listView.setAdapter(forecastAdapter);
    }
}

