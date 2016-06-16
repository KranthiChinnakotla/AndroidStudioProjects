package com.medha.mapsdemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    Button btnGo;
    ArrayList<String> selectedItems;
    private GoogleApiClient mGoogleApiClient;
    ArrayList<LatLng> listLatLngs;
    LatLng latLngAirport,latLngAtm,latLngBank,latLngCafe,latLngParking,latLngFood,latLngSchool;

    ArrayList<Place> places;
    ArrayList<Integer> placetype;

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();






        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        listLatLngs = new ArrayList<LatLng>();
        places = new ArrayList<Place>();
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                    Log.d("Demo", String.format("Place '%s' has likelihood: %g",
                            placeLikelihood.getPlace().getName(),
                            placeLikelihood.getLikelihood()));
                            listLatLngs.add(placeLikelihood.getPlace().getLatLng());
                    if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_AIRPORT)){
                        latLngAirport = placeLikelihood.getPlace().getLatLng();
                    }
                    else if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_ATM)){
                        latLngAtm = placeLikelihood.getPlace().getLatLng();
                    }
                    else if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_SCHOOL)){
                        latLngSchool = placeLikelihood.getPlace().getLatLng();
                    }
                    else if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_BANK)){
                        latLngBank = placeLikelihood.getPlace().getLatLng();
                    }
                    else if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_CAFE)){
                        latLngCafe = placeLikelihood.getPlace().getLatLng();
                    }
                    else if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_PARKING)){
                        latLngParking = placeLikelihood.getPlace().getLatLng();
                    }
                    else if(placeLikelihood.getPlace().getPlaceTypes().contains(Place.TYPE_FOOD)){
                        latLngFood = placeLikelihood.getPlace().getLatLng();
                    }

                }
                likelyPlaces.release();
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       setContentView(R.layout.activity_main);

        btnGo = (Button) findViewById(R.id.button_go);

        final Spinner dropdown = (Spinner) findViewById(R.id.spinner);
        final String[] items = new String[]{"airport", "atm", "bank", "cafe", "parking", "food", "school"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        selectedItems = new ArrayList<String>();
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItems.add(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItems != null) {

                    setContentView(R.layout.activity_maps);

                    mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);


                }


            }
        });




        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);*/


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

        for(LatLng latLng: listLatLngs){
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,16));
        }

        for(String s: selectedItems){

            switch (s){
                case "airport":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngAirport).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngAirport,16));
                    break;
                case "atm":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngAtm).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngAtm,16));
                    break;
                case "bank":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngBank).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngBank,16));
                    break;
                case "cafe":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngCafe).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngCafe,16));
                    break;
                case "parking":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngParking).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngParking,16));
                    break;
                case "food":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngFood).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngFood,16));
                    break;
                case "school":
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    mMap.addMarker(new MarkerOptions().position(latLngSchool).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngSchool,16));
                    break;

            }


        }





    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
