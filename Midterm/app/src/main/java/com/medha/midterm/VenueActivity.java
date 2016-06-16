package com.medha.midterm;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VenueActivity extends AppCompatActivity implements GetVenues.DisplayVenues {


    ListView listView;
    VenueAdapter venueAdapter;
    static ProgressDialog progressDialog;
    SharedPreferences pref;
    SharedPreferenc sPref;
    ArrayList<Venue> newVenueList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        progressDialog = new ProgressDialog(VenueActivity.this);
        String cityState = getIntent().getExtras().getString(MainActivity.choice_key);
        String url = "https://api.foursquare.com/v2/venues/search?client_id=VSGVHBSZBHHSO2KVBMIQJLEMS22YJKFXGSTEE5K3PD2JLUZO&client_secret=KIS2VPPRUZFTFYAEPSV2MLFRJ0EHVYIRZOC35YIAW5SN3BQI&v=20160321&near=" + cityState;
        new GetVenues(this).execute(url);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.venues_menu, menu);
        return true;
    }

    @Override
    public void showVenue(final ArrayList<Venue> venueList) {

        newVenueList = venueList;
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);


        listView = (ListView) findViewById(R.id.listView_venue);
        venueAdapter = new VenueAdapter(VenueActivity.this, R.layout.venue_adapter, venueList);

        listView.setAdapter(venueAdapter);
        venueAdapter.setNotifyOnChange(true);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageViewGreen = (ImageView) view.findViewById(R.id.imageView_choice);
                imageViewGreen.setImageResource(R.drawable.visited);
                sPref = new SharedPreferenc(pref, venueList.get(position).getVenueID(), venueList.get(position));
                return true;
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.marked_venues:
                sPref.showAll();
                return true;
            case R.id.clear_all:
                sPref.removeAll();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class SharedPreferenc {

        SharedPreferences.Editor editor;
        Venue venue;
        String venueId;
        SharedPreferences pref;

        public SharedPreferenc(SharedPreferences pref, String venueId, Venue venue) {
            editor = pref.edit();
            this.venue = venue;
            this.venueId = venueId;
            this.pref = pref;
            editor.putString(venueId, venue.getVenueName());
            editor.putString(venueId, venue.getCategoryIcon());
            editor.putString(venueId, venue.getCategoryName());
            editor.putString(venueId, venue.getCheckinCount());
        }


        public void removeAll() {
            editor.clear();
            editor.commit();
        }

        public Map<String, ?> showAll() {
            return pref.getAll();
        }


    }


}
