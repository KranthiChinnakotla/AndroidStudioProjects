package com.medha.midterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    final static String choice_key = "Choice";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = (Button) findViewById(R.id.button_select);

        final Spinner dropdown = (Spinner)findViewById(R.id.spinner_entercity);
        String[] items = new String[]{"Chicago,IL", "Atlanta,GA", "Charlotte,NC", "San Francisco,CA”", "“Seattle,WA", "Orlando,FL"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = dropdown.getSelectedItem().toString();
                Intent i = new Intent(MainActivity.this, VenueActivity.class);
                i.putExtra(choice_key, text);
                startActivity(i);

            }
        });


    }
}
