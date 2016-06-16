package com.example.tejakanchinadam.computercalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.zip.DeflaterOutputStream;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg1;

    RadioGroup rg2;

    EditText enterBudget;

    String message3;

    SeekBar myseekbar;

    TextView myTextView;

    private CheckBox ch1, ch2, ch3, ch4;

    int noOfCheckboxes;

    int groupValue1 = 2, groupValue2 = 250, seekBarProgress = 15, switchValue;

    Switch valueOfSwitch;

    TextView myPriceView;

    TextView mystatusLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
     //   setSupportActionBar(toolbar);

       getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setIcon(R.drawable.computer);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
     //   setSupportActionBar(toolbar);

      //  toolbar.setNavigationIcon(R.drawable.ic_good);
     //   toolbar.setTitle("Title");
      //  toolbar.setSubtitle("Sub");
      //  toolbar.setLogo(R.mipmap.ic_launcher);



        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = (RadioButton) findViewById(checkedId);

                switch (checkedId) {

                    case R.id.rg1rb1:
                        groupValue1 = 2;
                        break;
                    case R.id.rg1rb2:
                        groupValue1 = 4;
                        break;
                    case R.id.rg1rb3:
                        groupValue1 = 8;
                        break;
                    case R.id.rg1rb4:
                        groupValue1 = 16;
                        break;

                }


            }
        });

        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);

        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);

                switch (checkedId) {

                    case R.id.rg2rb1:
                        groupValue2 = 250;
                        break;
                    case R.id.rg2rb2:
                        groupValue2 = 500;
                        break;
                    case R.id.rg2rb3:
                        groupValue2 = 750;
                        break;
                    case R.id.rg2rb4:
                        groupValue2 = 1000;
                        break;

                }


            }
        });

        myseekbar = (SeekBar) findViewById(R.id.seekBar);

        myTextView = (TextView) findViewById(R.id.seekbarView);

        final int stepSize = 5;

        myseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

              // message3 = Integer.toString(progress);

                progress = (int)(Math.round(progress/stepSize)) * stepSize;

                seekBar.setProgress(progress);

                myTextView.setText(progress + "%");

                seekBarProgress = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        valueOfSwitch = (Switch) findViewById(R.id.mySwitch);




        ch1 = (CheckBox) findViewById(R.id.ch1);

        ch2 = (CheckBox) findViewById(R.id.ch2);

        ch3 = (CheckBox) findViewById(R.id.ch3);

        ch4 = (CheckBox) findViewById(R.id.ch4);


        enterBudget = (EditText) findViewById(R.id.editText);

        myPriceView = (TextView) findViewById(R.id.priceView);

        mystatusLabel = (TextView) findViewById(R.id.statusLabel);

        findViewById(R.id.mainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (enterBudget.getText().toString().isEmpty() || enterBudget.getText().toString().matches("") || convertToDouble(enterBudget.getText().toString()) == 0) {

                    setError("Please enter a value");

                } else {

                    ArrayList<Boolean> OUTPUT = new ArrayList<Boolean>();

                    ArrayList checkboxList = new ArrayList();

                    OUTPUT.add(ch1.isChecked());

                    OUTPUT.add(ch2.isChecked());

                    OUTPUT.add(ch3.isChecked());

                    OUTPUT.add(ch4.isChecked());

                    for (int i = 0; i < OUTPUT.size(); i++) {

                        if (OUTPUT.get(i) == true) {

                            checkboxList.add(i);

                        }

                    }

                    noOfCheckboxes = checkboxList.size();

                    if (valueOfSwitch.isChecked()) {

                        switchValue = 1;

                    } else {

                        switchValue = 0;
                    }


                    message3 = Integer.toString(groupValue1) + Integer.toString(groupValue2) + Integer.toString(noOfCheckboxes) + Integer.toString(seekBarProgress) + Integer.toString(switchValue);


                    //mainFunction(groupValue1, groupValue2,noOfCheckboxes,seekBarProgress, switchValue);

                    // Toast.makeText(getApplication(), Double.toString(roundoff(mainFunction(groupValue1, groupValue2, noOfCheckboxes, seekBarProgress, switchValue), 2)),
                    //       Toast.LENGTH_LONG).show();


                    Double finalValue = roundoff(mainFunction(groupValue1, groupValue2, noOfCheckboxes, seekBarProgress, switchValue), 2);

                    myPriceView.setText(Double.toString(finalValue));

                    Double tempBudgetValue = convertToDouble(enterBudget.getText().toString());

                    if (finalValue <= tempBudgetValue) {

                        mystatusLabel.setText("Within Budget");

                        mystatusLabel.setBackgroundResource(R.color.green);

                    } else {

                        mystatusLabel.setText("Over Budget");
                        mystatusLabel.setBackgroundResource(R.color.red);
                    }

                }

            }
        });


        findViewById(R.id.resetButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enterBudget.setText("");

                myseekbar.setProgress(15);

                rg1.check(R.id.rg1rb1);

                rg2.check(R.id.rg2rb1);

                myPriceView.setText("");

                mystatusLabel.setText("");

                ch1.setChecked(false);

                ch2.setChecked(false);

                ch3.setChecked(false);

                ch4.setChecked(false);

                valueOfSwitch.setChecked(true);

                mystatusLabel.setBackgroundResource(0);

            }
        });



    }




    public Double mainFunction(int M, int S, int A, int T, int D) {

        Double cost1 = ((10 * M) + (0.75 * S) + (20 * A));


        Double cost2 =  (1 + ((double) T / 100));


        Double cost = (cost1 * cost2) + (5.95 * D);

        return cost;

    }

    public Double roundoff(Double someDouble, int places){

        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        someDouble = someDouble * factor;
        long tmp = Math.round(someDouble);
        return (double) tmp / factor;

    }

    public Double convertToDouble(String someString) {

        return Double.parseDouble(someString);


    }

    public String convertToString(Double someDouble){

        return Double.toString(someDouble);

    }

    public void setError(String message) {

        Toast.makeText(getApplication(),message,Toast.LENGTH_LONG).show();


    }




    }


