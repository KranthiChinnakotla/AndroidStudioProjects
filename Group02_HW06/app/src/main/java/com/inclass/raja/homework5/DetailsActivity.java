package com.inclass.raja.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import it.sephiroth.android.library.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    Weather weather;
    ArrayList<Integer> temperature;
    TextView textViewMax,textViewMin,textViewTemp,textViewLocation,textViewClimatetype,tvFeelsLike,tvHumidity,tvDewpoint,tvPressure,tvClouds,tvWinds;
    ImageView imageView;
    int hour,start = 0,index = 0;
    String timeFormat;
    ImageButton btnNext, btnPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        temperature = new ArrayList<Integer>();
        weather = (Weather) getIntent().getExtras().getSerializable("details");

        if(weather != null){
            start = 1;
        }

       for(Weather wt: HourlyDataActivity.listWeatherDetails){
           temperature.add(Integer.parseInt(wt.getTemperature()));
           if(weather.getTime().equals(wt.getTime()))
               index = HourlyDataActivity.listWeatherDetails.indexOf(wt);
       }
        Collections.sort(temperature);

        textViewMax = (TextView) findViewById(R.id.textView_maxtemp);
        textViewMin = (TextView) findViewById(R.id.textView_mintemp);
        textViewTemp = (TextView) findViewById(R.id.textView_temp);
        textViewLocation = (TextView) findViewById(R.id.textView_citytime);
        textViewClimatetype = (TextView) findViewById(R.id.textView_Climate);
        tvFeelsLike = (TextView) findViewById(R.id.textView_feelslike);
        tvHumidity = (TextView) findViewById(R.id.textView_humidity);
        tvClouds = (TextView) findViewById(R.id.textView_clouds);
        tvDewpoint = (TextView) findViewById(R.id.textView_dewpoint);
        tvPressure = (TextView) findViewById(R.id.textView_pressure);
        tvWinds = (TextView) findViewById(R.id.textView_winds);
        imageView = (ImageView) findViewById(R.id.imageView2);
        btnNext = (ImageButton) findViewById(R.id.imageButton_right);
        btnPrev = (ImageButton) findViewById(R.id.imageButton_left);
        textViewMax.setText(String.valueOf(temperature.get(temperature.size() - 1))+" F");
        textViewMin.setText(String.valueOf(temperature.get(1))+" F");
        textViewTemp.setText(weather.getTemperature() + " F");
        textViewClimatetype.setText(weather.getClimateType());
        Picasso.with(DetailsActivity.this).load(weather.getIconUrl()).into(imageView);
        tvFeelsLike.setText(weather.getFeelsLike() + " Fahrenheit");
        tvWinds.setText(weather.getWindSpeed() + "mPh" + " ," + weather.getWindDirection());
        tvHumidity.setText(weather.getHumidity() + "%");
        tvClouds.setText(weather.getClouds());
        tvDewpoint.setText(weather.getDewpoint()+" Fahrenheit");
        tvPressure.setText(weather.getPressure()+"hPa");


        hour = Integer.parseInt(weather.getTime());
        switch (hour){
            case 0:
                timeFormat = "12 AM";
                break;
            case 1:
                timeFormat = "01 AM";
                break;
            case 2:
                timeFormat = "02 AM";
                break;
            case 3:
                timeFormat = "03 AM";
                break;
            case 4:
                timeFormat = "04 AM";
                break;
            case 5:
                timeFormat = "05 AM";
                break;
            case 6:
                timeFormat = "06 AM";
                break;
            case 7:
                timeFormat = "07 AM";
                break;
            case 8:
                timeFormat = "08 AM";
                break;
            case 9:
                timeFormat="09 AM";
                break;
            case 10:
                timeFormat = "10 AM";
                break;
            case 11:
                timeFormat = "11 AM";
                break;
            case 12:
                timeFormat = "12 PM";
                break;
            case 13:
                timeFormat = "01 PM";
                break;
            case 14:
                timeFormat = "02 PM";
                break;
            case 15:
                timeFormat = "03 PM";
                break;
            case 16:
                timeFormat = "04 PM";
                break;
            case 17:
                timeFormat = "05 PM";
                break;
            case 18:
                timeFormat = "06 PM";
                break;
            case 19:
                timeFormat="07 PM";
                break;
            case 20:
                timeFormat = "08 PM";
                break;
            case 21:
                timeFormat = "09 PM";
                break;
            case 22:
                timeFormat = "10 PM";
                break;
            case 23:
                timeFormat = "11 PM";
                break;
            default:
                break;
        }
        textViewLocation.setText(AddCityActivity.cityName+", "+AddCityActivity.stateName + "("+ timeFormat +")");



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start == 1 && index < HourlyDataActivity.listWeatherDetails.size()){

                    hour = Integer.parseInt(HourlyDataActivity.listWeatherDetails.get(index).getTime());

                    switch (hour){
                        case 0:
                            timeFormat = "12 AM";
                            break;
                        case 1:
                            timeFormat = "01 AM";
                            break;
                        case 2:
                            timeFormat = "02 AM";
                            break;
                        case 3:
                            timeFormat = "03 AM";
                            break;
                        case 4:
                            timeFormat = "04 AM";
                            break;
                        case 5:
                            timeFormat = "05 AM";
                            break;
                        case 6:
                            timeFormat = "06 AM";
                            break;
                        case 7:
                            timeFormat = "07 AM";
                            break;
                        case 8:
                            timeFormat = "08 AM";
                            break;
                        case 9:
                            timeFormat="09 AM";
                            break;
                        case 10:
                            timeFormat = "10 AM";
                            break;
                        case 11:
                            timeFormat = "11 AM";
                            break;
                        case 12:
                            timeFormat = "12 PM";
                            break;
                        case 13:
                            timeFormat = "01 PM";
                            break;
                        case 14:
                            timeFormat = "02 PM";
                            break;
                        case 15:
                            timeFormat = "03 PM";
                            break;
                        case 16:
                            timeFormat = "04 PM";
                            break;
                        case 17:
                            timeFormat = "05 PM";
                            break;
                        case 18:
                            timeFormat = "06 PM";
                            break;
                        case 19:
                            timeFormat="07 PM";
                            break;
                        case 20:
                            timeFormat = "08 PM";
                            break;
                        case 21:
                            timeFormat = "09 PM";
                            break;
                        case 22:
                            timeFormat = "10 PM";
                            break;
                        case 23:
                            timeFormat = "11 PM";
                            break;
                        default:
                            break;
                    }

                    textViewLocation.setText(AddCityActivity.cityName+", "+AddCityActivity.stateName + "("+ timeFormat +")");



                    textViewMax.setText(String.valueOf(temperature.get(temperature.size()-1))+" F");
                    textViewMin.setText(String.valueOf(temperature.get(1))+" F");
                    textViewTemp.setText(HourlyDataActivity.listWeatherDetails.get(index).getTemperature()+ " F");
                    textViewClimatetype.setText(HourlyDataActivity.listWeatherDetails.get(index).getClimateType());
                    Picasso.with(DetailsActivity.this).load(HourlyDataActivity.listWeatherDetails.get(index).getIconUrl()).into(imageView);
                    tvFeelsLike.setText(HourlyDataActivity.listWeatherDetails.get(index).getFeelsLike() + " Fahrenheit");
                    tvWinds.setText(HourlyDataActivity.listWeatherDetails.get(index).getWindSpeed()+"mPh" + " ," + weather.getWindDirection());
                    tvHumidity.setText(HourlyDataActivity.listWeatherDetails.get(index).getHumidity() + "%");
                    tvClouds.setText(HourlyDataActivity.listWeatherDetails.get(index).getClouds());
                    tvDewpoint.setText(HourlyDataActivity.listWeatherDetails.get(index).getDewpoint()+" Fahrenheit");
                    tvPressure.setText(HourlyDataActivity.listWeatherDetails.get(index).getPressure()+"hPa");
                    index ++;
                }
                if(index >= HourlyDataActivity.listWeatherDetails.size())
                    index = 0;
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(start == 1 && index >= 0){


                    hour = Integer.parseInt(HourlyDataActivity.listWeatherDetails.get(index).getTime());

                    switch (hour){
                        case 0:
                            timeFormat = "12 AM";
                            break;
                        case 1:
                            timeFormat = "01 AM";
                            break;
                        case 2:
                            timeFormat = "02 AM";
                            break;
                        case 3:
                            timeFormat = "03 AM";
                            break;
                        case 4:
                            timeFormat = "04 AM";
                            break;
                        case 5:
                            timeFormat = "05 AM";
                            break;
                        case 6:
                            timeFormat = "06 AM";
                            break;
                        case 7:
                            timeFormat = "07 AM";
                            break;
                        case 8:
                            timeFormat = "08 AM";
                            break;
                        case 9:
                            timeFormat="09 AM";
                            break;
                        case 10:
                            timeFormat = "10 AM";
                            break;
                        case 11:
                            timeFormat = "11 AM";
                            break;
                        case 12:
                            timeFormat = "12 PM";
                            break;
                        case 13:
                            timeFormat = "01 PM";
                            break;
                        case 14:
                            timeFormat = "02 PM";
                            break;
                        case 15:
                            timeFormat = "03 PM";
                            break;
                        case 16:
                            timeFormat = "04 PM";
                            break;
                        case 17:
                            timeFormat = "05 PM";
                            break;
                        case 18:
                            timeFormat = "06 PM";
                            break;
                        case 19:
                            timeFormat="07 PM";
                            break;
                        case 20:
                            timeFormat = "08 PM";
                            break;
                        case 21:
                            timeFormat = "09 PM";
                            break;
                        case 22:
                            timeFormat = "10 PM";
                            break;
                        case 23:
                            timeFormat = "11 PM";
                            break;
                        default:
                            break;
                    }

                    textViewLocation.setText(AddCityActivity.cityName+", "+AddCityActivity.stateName + "("+ timeFormat +")");


                    textViewMax.setText(String.valueOf(temperature.get(temperature.size()-1))+" F");
                    textViewMin.setText(String.valueOf(temperature.get(1))+" F");
                    textViewTemp.setText(HourlyDataActivity.listWeatherDetails.get(index).getTemperature()+ " F");
                    textViewClimatetype.setText(HourlyDataActivity.listWeatherDetails.get(index).getClimateType());
                    Picasso.with(DetailsActivity.this).load(HourlyDataActivity.listWeatherDetails.get(index).getIconUrl()).into(imageView);
                    tvFeelsLike.setText(HourlyDataActivity.listWeatherDetails.get(index).getFeelsLike() + " Fahrenheit");
                    tvWinds.setText(HourlyDataActivity.listWeatherDetails.get(index).getWindSpeed()+"mPh" + " ," + weather.getWindDirection());
                    tvHumidity.setText(HourlyDataActivity.listWeatherDetails.get(index).getHumidity() + "%");
                    tvClouds.setText(HourlyDataActivity.listWeatherDetails.get(index).getClouds());
                    tvDewpoint.setText(HourlyDataActivity.listWeatherDetails.get(index).getDewpoint()+" Fahrenheit");
                    tvPressure.setText(HourlyDataActivity.listWeatherDetails.get(index).getPressure()+"hPa");
                    index -- ;
                }
                if(index<0)
                    index=HourlyDataActivity.listWeatherDetails.size()-1;
            }
        });






    }
}
