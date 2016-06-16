package com.inclass.raja.homework5;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AddCityActivity extends AppCompatActivity {

    EditText cityText,stateText;
   static String cityName,stateName;
    Boolean myflag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        cityText = (EditText) findViewById(R.id.cityText);
        stateText = (EditText) findViewById(R.id.stateText);


        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityName = cityText.getText().toString();
                cityName = cityName.replaceAll(" ", "_");
                stateName = stateText.getText().toString();

                String url = "http://api.wunderground.com/api/37af142f823f9ab7/hourly/q/"+stateName+"/"+cityName+".xml";

                new ValidateCity().execute(url);

            }
        });
    }


    public class ValidateCity extends AsyncTask<String,Void,String>{


        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                //return ParseWeather.PullParseWeather.parsing(con.getInputStream());
                return ValidateStateAndCity.parsing(con.getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            if (s != null){

                Log.d("Demo",s);

                if (s.equals("Valid")){

                    Intent intent = new Intent();
                    CityDetails objCityDetails = new CityDetails(cityName, stateName);
                    intent.putExtra("result", objCityDetails);
                    setResult(MainActivity.RESULT_OK, intent);
                    finish();


                }else if (s.equals("InvalidState")){


                    Toast.makeText(AddCityActivity.this, "Invalid State or City", Toast.LENGTH_LONG).show();
                }
            }



        }
    }

    static public class ValidateStateAndCity {
        static public String parsing(InputStream in) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            String type = null;
            int event = parser.getEventType();
            //String direction = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("response")){
                            type = "Start";
                        } else if (parser.getName().equals("type")) {
                            type = "InvalidState";
                        }else if(parser.getName().equals("hourly_forecast")){

                            type = "Valid";
                        }else if (parser.getName().equals("results")) {

                            type = "InvalidState";

                        }

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("response")){

                            break;
                        }
                    default:
                        break;



                }
                event = parser.next();
            }


            return type;
        }
    }

}