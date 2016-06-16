package com.medha.explicitintentsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.button_secondActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (getIntent().getExtras() != null){
            String name =  getIntent().getExtras().getString(MainActivity.NAME_KEY);
            double age = getIntent().getExtras().getDouble(MainActivity.AGE_KEY, 23.8);
            User user = (User) getIntent().getExtras().getParcelable(MainActivity.SERIALIZABLE_KEY);
            Toast.makeText(Main2Activity.this,user.toString() + " " + age,Toast.LENGTH_LONG).show();
        }

    }
}
