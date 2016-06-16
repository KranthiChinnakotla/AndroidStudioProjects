package com.example.tejakanchinadam.inclass07app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {


    final static String choice_key = "Choice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //home, world, national, politics, nyregion, business, opinion, technology,
          //      science, health, sports, arts, fashion, dining, travel, magazine, realestate

        final Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"home", "world", "national", "politics", "nyregion", "business", "opinion", "technology",
        "science", "health", "sports", "arts", "fashion", "dining", "travel", "magazine", "realestate"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = dropdown.getSelectedItem().toString();

                //Log.d("Demo", text);

               Intent i = new Intent(MainActivity.this, TopStories.class);

                i.putExtra(choice_key, text);

                startActivity(i);
                //i.putExtra(choice_key, text);


            }
        });




    }
}
