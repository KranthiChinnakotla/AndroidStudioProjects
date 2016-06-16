package com.medha.summermidterm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import it.sephiroth.android.library.picasso.Picasso;

public class WeatherSummary extends AppCompatActivity {

    ImageView image1,image2,image3;
    TextView temp,humidity,pressure,weath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_summary);
        getSupportActionBar().setTitle(MainActivity.cityState[0]+","+MainActivity.cityState[1]);

        image1= (ImageView) findViewById(R.id.imageView2);
        image2= (ImageView) findViewById(R.id.imageView3);
        image3= (ImageView) findViewById(R.id.imageView);
        temp= (TextView) findViewById(R.id.textView_temp);
        humidity= (TextView) findViewById(R.id.textView_humidity);
        pressure= (TextView) findViewById(R.id.textView_pressure);
        weath= (TextView) findViewById(R.id.textView_weather);

        Weather weather = (Weather) getIntent().getExtras().getSerializable("value");

        if(weather.weather.size() == 1){

            weath.setText(weather.weather.get(0));
            Picasso.with(this).load(weather.icon.get(0)).into(image2);
        }
        else if(weather.weather.size() == 2 ){

            weath.setText(weather.weather.get(0)+","+weather.weather.get(1));
            Picasso.with(this).load(weather.icon.get(0)).into(image2);
            Picasso.with(this).load(weather.icon.get(1)).into(image3);

        }
        else if(weather.weather.size() == 3){

            weath.setText(weather.weather.get(0)+","+weather.weather.get(1)+","+weather.weather.get(2));
            Picasso.with(this).load(weather.icon.get(0)).into(image2);
            Picasso.with(this).load(weather.icon.get(1)).into(image3);
            Picasso.with(this).load(weather.icon.get(2)).into(image1);
        }

        temp.setText("Temperature: "+weather.getTemp());
        humidity.setText("Humidity: "+weather.getHumidity());
        pressure.setText("Pressure: "+weather.getPressure());

    }
}
