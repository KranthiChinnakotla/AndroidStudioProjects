package com.medha.inclass2b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double eTextInch;
    float  eTextMile;
    float eTextFeet;
    double eText;

    TextView textViewResult;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = (EditText) findViewById(R.id.editText);
        textViewResult= (TextView)findViewById(R.id.textView_resultenter);
        rg = (RadioGroup)findViewById(R.id.rgID);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (editText.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Number Invalid", Toast.LENGTH_LONG).show();
                } else {

                    if (findViewById(checkedId) == findViewById(R.id.rb_Inches)){
                        Button btnCv = (Button)findViewById(R.id.button_convert);
                        btnCv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eText = Double.parseDouble(editText.getText().toString());
                                eTextInch = eText * 39.3701;
                                textViewResult.setText("" + eTextInch);

                            }
                        });


                    }
                    else if (findViewById(checkedId) == findViewById(R.id.rb_feet)){

                        Button btnCv = (Button)findViewById(R.id.button_convert);
                        btnCv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eText = Float.parseFloat(editText.getText().toString());
                                eTextFeet = (float) (eText * 3.28);
                                textViewResult.setText("" + eTextFeet);


                            }
                        });


                    }

                    else if (findViewById(checkedId)== findViewById(R.id.rb_Miles)){

                        Button btnCv = (Button)findViewById(R.id.button_convert);
                        btnCv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                eText = Float.parseFloat(editText.getText().toString());
                                eTextMile = (float) (eText * 0.0006);
                                textViewResult.setText("" + eTextMile);


                            }
                        });

                    }

                    else if (findViewById(checkedId) == findViewById(R.id.rb_ClearAll)){

                        Button btnCv = (Button)findViewById(R.id.button_convert);
                        btnCv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                textViewResult.setText("");
                                editText.setText("");
                            }
                        });


                    }
                }


            }
        });
    }
}
