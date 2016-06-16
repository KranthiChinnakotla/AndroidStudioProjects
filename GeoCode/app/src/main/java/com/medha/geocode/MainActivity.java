package com.medha.geocode;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Geocoder.isPresent()){

            new GeoTask(this).execute("CLT");
        }
        else{
            Toast.makeText(this,"No Geocoding",Toast.LENGTH_LONG).show();
        }
    }

    public class GeoTask extends AsyncTask<String,Void,List<Address>>{

        Context mContext;

        public GeoTask(Context context){

            this.mContext = context;
        }

        @Override
        protected List<Address> doInBackground(String... params) {

            List<Address> addresses = null;
            Geocoder geocoder = new Geocoder(mContext);

            try {
                addresses = geocoder.getFromLocationName(params[0],10);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return addresses;
        }

        @Override
        protected void onPostExecute(List<Address> result) {
            super.onPostExecute(result);
            if(result == null){
                Log.d("Demo","No results found");
            }
            else
                Log.d("Demo",result.toString());
        }
    }

}


