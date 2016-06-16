package com.medha.summermidterm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;


import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText eCityState;
    Switch metricSwitch;
    Button checkWeather;
    String wUrl;
    String units="metric";
    static String [] cityState;
    Weather wthr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eCityState = (EditText) findViewById(R.id.editText);
        metricSwitch = (Switch) findViewById(R.id.switch1);
        checkWeather = (Button) findViewById(R.id.button);



        metricSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    units="imperial";
                    if( !eCityState.getText().toString().equals("") ){
                        cityState = eCityState.getText().toString().split(",");
                        wUrl = "http://api.openweathermap.org/data/2.5/forecast/city?q="+eCityState.getText().toString()+"&units="+units+"&APPID=6fa095269bd23927da7f4fe8f3ef5f68";
                        new GetWeather().execute(wUrl);}
                }
                else{
                    units="metric";
                    if( !eCityState.getText().toString().equals("") ){
                        cityState = eCityState.getText().toString().split(",");
                        wUrl = "http://api.openweathermap.org/data/2.5/forecast/city?q="+eCityState.getText().toString()+"&units="+units+"&APPID=6fa095269bd23927da7f4fe8f3ef5f68";
                        new GetWeather().execute(wUrl);}
                }
            }

        });





        checkWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,WeatherSummary.class);
                if(wthr!=null){
                    intent.putExtra("value",wthr);
                    startActivity(intent);}


                }


        });






       //

    }


    class GetWeather extends AsyncTask<String,Void,Weather>{

        @Override
        protected void onPostExecute(Weather weather) {

            super.onPostExecute(weather);
            wthr = weather;
        }

        BufferedReader reader;
        InputStreamReader iReader;

        @Override
        protected Weather doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                iReader = new InputStreamReader(con.getInputStream());
                if(iReader!=null)
                reader = new BufferedReader(iReader);
                StringBuilder sb = new StringBuilder();
                String line = "";
                while(line != null){
                    line = reader.readLine();
                    sb = sb.append(line);
                }
                return ParseWeather.parseWeather(sb.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
