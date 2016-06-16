package com.medha.itunestopgrossing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetTopApps.AppData{

    ListView appsView;
    AppsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appsView = (ListView) findViewById(R.id.listView);

        String url = "https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml";
        new GetTopApps(this).execute(url);

    }



    @Override
    public void forListView(final ArrayList<TopApps> topAppList) {

        adapter = new AppsAdapter(MainActivity.this,R.layout.adapter_layout,topAppList);
        appsView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        appsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,PreviewActivity.class);
                intent.putExtra("top",topAppList.get(position));
                startActivity(intent);
            }
        });



    }
}
