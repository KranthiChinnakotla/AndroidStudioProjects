package com.medha.finals;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Firebase finals;
    private GoogleMap mMap;
    double lat,lon;
    String myLocation;
    SupportMapFragment mapFragment;
    Address firstAddress;
    Random rn ;
    AlertDialog.Builder builder1;
    AlertDialog alert;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Firebase.setAndroidContext(this);
        finals = new Firebase("https://finals.firebaseio.com/");



        final EditText input = new EditText(this);
        builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Enter Address")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       if(input.getText()!=null){
                           myLocation = input.getText().toString();
                        new GeoTask(MapsActivity.this).execute(input.getText().toString());}
                        else
                           Toast.makeText(getApplicationContext(),"Enter the city or address",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                });
         alert = builder1.create();
        alert.show();
        if(Geocoder.isPresent()){


        }
        else{
            Toast.makeText(this,"No Geocoding",Toast.LENGTH_LONG).show();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        if(lat != 0 && lon != 0 ){
        final LatLng selected = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(selected).title(firstAddress.getAddressLine(1)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selected,5));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Marker nmarker = mMap.addMarker(new MarkerOptions().position(selected).title(firstAddress.toString()));
                marker.showInfoWindow();
                return false;
            }
        });

            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {

                    rn = new Random();

                    Map<String, Object> loc = new HashMap<String, Object>();
                    loc.put(String.valueOf(rn.nextInt()), myLocation);
                    finals.child("value").updateChildren(loc);
                    mMap.addMarker(new MarkerOptions().position(selected).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_changes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.clear:
                mMap.clear();
                finals.child("value").removeValue();

                return true;
            case R.id.enter_address:
                alert.show();
                return true;
            case R.id.Zoom:
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon),0));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    public class GeoTask extends AsyncTask<String,Void,List<Address>> {

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
                Toast.makeText(MapsActivity.this,"Invalid Address entered",Toast.LENGTH_LONG).show();
            }
            else{
                firstAddress = result.get(0);
                lat = firstAddress.getLatitude();
                lon = firstAddress.getLongitude();
                mapFragment.getMapAsync(MapsActivity.this);
                }

        }
    }

}
