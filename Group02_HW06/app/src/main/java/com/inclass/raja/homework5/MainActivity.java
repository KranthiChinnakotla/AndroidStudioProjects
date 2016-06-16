package com.inclass.raja.homework5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseDataManager dm;
    static ArrayList<CityDetails> listOfCities = new ArrayList<CityDetails>();
    TextView defaultText;
    ListView listView;
    static final String CITY_KEY = "City_key";
    static final String STATE_KEY = "State_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultText = (TextView) findViewById(R.id.defaultText);
        listView = (ListView) findViewById(R.id.listView);
        if (listOfCities.size() > 0) {
            SetContents();
        } else {
            defaultText.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.addcity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_city:
                AddCity();
                return true;
            case R.id.saved_cities:
                if (listOfCities.size() > 0) {
                    listOfCities.clear();
                    dm = new DatabaseDataManager(MainActivity.this, "cities");
                    dm.deleteAllCity();
                    listView.setVisibility(View.INVISIBLE);
                }
                return true;
            case R.id.view_notes:
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void AddCity() {
        Intent intent = new Intent(MainActivity.this, AddCityActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == MainActivity.RESULT_OK) {
                listOfCities.add((CityDetails) data.getExtras().get("result"));
                SetContents();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void SetContents() {

        defaultText.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        final ArrayAdapter<CityDetails> cityDetailsAdapter = new ArrayAdapter<CityDetails>(this, android.R.layout.simple_list_item_1, listOfCities);
        listView.setAdapter(cityDetailsAdapter);
        cityDetailsAdapter.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, CityData_Activity.class);
                intent.putExtra(CITY_KEY, listOfCities.get(position).getCityName());
                intent.putExtra(STATE_KEY, listOfCities.get(position).getStateName());
                startActivity(intent);
                Log.d("Demo", listOfCities.get(position).toString());
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dm = new DatabaseDataManager(MainActivity.this, "cities");
                dm.deleteCity(new CityDetails(listOfCities.get(position).getCityName(), listOfCities.get(position).getStateName()));
                listOfCities.remove(position);
                cityDetailsAdapter.notifyDataSetChanged();

                if (listOfCities.size() == 0) {
                    defaultText.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.INVISIBLE);
                }
                return true;
            }
        });
    }
}
