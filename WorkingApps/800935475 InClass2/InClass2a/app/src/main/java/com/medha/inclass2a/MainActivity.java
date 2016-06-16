package com.medha.inclass2a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double eTextInch;
    float  eTextMile;
    float eTextFeet;
    TextView textViewResult;
    double eText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        textViewResult= (TextView)findViewById(R.id.tv_result);


        Button btnInches = (Button) findViewById(R.id.button_inches);
        btnInches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Number Invalid", Toast.LENGTH_LONG).show();
                } else {
                    eText = Double.parseDouble(editText.getText().toString());
                    eTextInch = eText * 39.3701;
                    textViewResult.setText("" + eTextInch);
                }
            }
        });

        Button btnMiles = (Button) findViewById(R.id.button_miles);
        btnMiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Number Invalid", Toast.LENGTH_LONG).show();
                }else {

                eText = Float.parseFloat(editText.getText().toString());
                eTextMile = (float) (eText * 0.0006);
                textViewResult.setText("" + eTextMile);}

            }
        });

        Button btnFeet = (Button) findViewById(R.id.button_feet);
        btnFeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Number Invalid", Toast.LENGTH_LONG).show();
                }else {
                    eText = Float.parseFloat(editText.getText().toString());
                    eTextFeet = (float) (eText * 3.28);
                    textViewResult.setText("" + eTextFeet);
                }
            }
        });

        Button btnClear = (Button) findViewById(R.id.btn_clr);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Number Invalid", Toast.LENGTH_LONG).show();
                }else {
                    textViewResult.setText("");
                    editText.setText("");
                }

            }
        });
    }
}
