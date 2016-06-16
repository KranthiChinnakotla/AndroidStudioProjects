package com.medha.itunestopgrossing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {

    TopApps apps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        TextView pAppname = (TextView) findViewById(R.id.textView);
        if(getIntent().getExtras()!=null){
             apps = (TopApps) getIntent().getExtras().getSerializable("top");
        }

        if(apps != null){
            pAppname.setText(apps.getName());
            Picasso.with(PreviewActivity.this).load(apps.getImage()).into(imageView2);
        }
    }
}
