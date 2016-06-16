package com.medha.explicitintentsdemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final static String NAME_KEY = "NAME";
    final static String AGE_KEY = "AGE";
    final static String SERIALIZABLE_KEY = "USER";
    final private  int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_firstActivity).setOnClickListener(this);
        //findViewById(R.id.button_phone).setOnClickListener(this);
        Button button = (Button) findViewById(R.id.button_phone);
        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:7043401634"));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    requestPermissions(new String[] {Manifest.permission.CALL_PHONE},REQUEST_CODE);
                    return;
                }
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {


        //User user = new User("Kranthi Chinnakotla",31.10);

        if (v.getId() == R.id.button_firstActivity) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uncc.edu"));
            startActivity(intent);
        }
    }



        {





        }



        //Intent intent = new Intent();
        //intent.addCategory(Intent.CATEGORY_DEFAULT);
        //intent.putExtra(NAME_KEY,"Kranthi Chinnakotla");
        //intent.putExtra(AGE_KEY,25.9);
        //intent.putExtra(SERIALIZABLE_KEY,user);

        //startActivity(intent);

    }

