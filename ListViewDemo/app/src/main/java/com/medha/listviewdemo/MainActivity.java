package com.medha.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Colors> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // final String [] colors = {"Red","Green","Blue","Orange","Yellow","Black"};

        colors = new ArrayList<Colors>();
        colors.add(new Colors("Black","#000000"));
        colors.add(new Colors("Blue","#0000FF"));
        colors.add(new Colors("Brown","#654321"));
        colors.add(new Colors("Green","#006600"));
        colors.add(new Colors("Orange","#FF6600"));
        colors.add(new Colors("Red","#FF0000"));
        colors.add(new Colors("Grey","#BBBBBB"));


        ListView listView = (ListView) findViewById(R.id.listView);
        //ArrayAdapter<Colors> arrayAdapter = new ArrayAdapter<Colors>(this,R.layout.colors_layout,colors);
        ColorsAdapter adapter = new ColorsAdapter(this,R.layout.colors_layout,colors);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
      //  arrayAdapter.add(new Colors("Purple"));
     //   arrayAdapter.remove(colors.get(0));
      //  arrayAdapter.insert(new Colors("Brown"),0);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Color: " + colors.get(position)+ " at position " + position,Toast.LENGTH_LONG).show();
            }
        });

    }
}
