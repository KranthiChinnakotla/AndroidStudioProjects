package com.medha.fragmentsdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Demo", "before inflating");
        setContentView(R.layout.activity_main);
        Log.d("Demo","After inflating");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Demo","In onstart of main acitivty");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Demo","inside onResume of main activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Demo","inside onDestroy of main activity");
    }
}
