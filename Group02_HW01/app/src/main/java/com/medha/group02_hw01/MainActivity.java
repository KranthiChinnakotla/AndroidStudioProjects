package com.medha.group02_hw01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // Declaring Objects for the widgets
    EditText editText ;
    TextView textViewBudget,textViewStorage,textViewMemory,textViewAccessories,textViewDelivery;
    TextView textViewTip,textViewPrice,textViewDisplayStatus;
    RadioGroup radioGroupMemory,radioGroupStorage;
    CheckBox checkBoxMouse,checkBoxCoolingPad,checkBoxFlashDrive,checkBoxCarryingCase;
    SeekBar seekBarTip;
    Switch swDelivery;
    Button buttonCalculate,buttonReset;
    // * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    float editTextValue;
    int numAccessoriesChecked ;
    int costDevices ,costMemory;
    double costStorage,costDelivery,costTip,totalCost;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        checkBoxMouse = (CheckBox) findViewById(R.id.checkBox_Mouse);
        checkBoxCarryingCase = (CheckBox) findViewById(R.id.checkBox_carryingcase);
        checkBoxCoolingPad = (CheckBox) findViewById(R.id.checkBox_coolingpad);
        checkBoxFlashDrive = (CheckBox) findViewById(R.id.checkBox_FlashDrive);
        radioGroupMemory = (RadioGroup) findViewById(R.id.radioGroup_Memory);
        radioGroupStorage = (RadioGroup) findViewById(R.id.radioGroup_Storage);
        seekBarTip = (SeekBar) findViewById(R.id.seekBar_Tip);
        textViewTip = (TextView) findViewById(R.id.textView_percentage);
        textViewPrice = (TextView) findViewById(R.id.textView_price0$);
        textViewDisplayStatus = (TextView) findViewById(R.id.textView_color);
        swDelivery = (Switch) findViewById(R.id.switch_delivery);
        costCalculation();

        buttonCalculate = (Button) findViewById(R.id.button_calculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( editText.getText().length()<=0) {

                    editText.setError(" Enter the dollar amount");
                }
                else {
                    editTextValue = Float.parseFloat(editText.getText().toString());
                    if (editTextValue == 0) {
                        editText.setError("Enter the amount greater than 0");
                    }
                }

                totalCost = ((costMemory+costStorage+costDevices)*(1+costTip))+costDelivery;
                textViewPrice.setText("Price: " + totalCost);
                if (totalCost == 0)
                    textViewPrice.setText("Price: " + totalCost);

            }
        });

        if(totalCost <= editTextValue){
            textViewDisplayStatus.setText("Within budget");
        } else if (totalCost>editTextValue)
            textViewDisplayStatus.setText("Over budget");
    }

    private void costCalculation (){


        if(checkBoxMouse.isChecked()  ){
            numAccessoriesChecked++;
        }
        if(checkBoxCarryingCase.isChecked()){
            numAccessoriesChecked++;
        }
        if(checkBoxCoolingPad.isChecked()){
            numAccessoriesChecked++;
        }
        if (checkBoxFlashDrive.isChecked()){
            numAccessoriesChecked++;
        }

        switch (numAccessoriesChecked){
            case 1:
                costDevices = numAccessoriesChecked*20;
            case 2:
                costDevices = numAccessoriesChecked*20;
            case 3:
                costDevices = numAccessoriesChecked*20;
            case 4:
                costDevices = numAccessoriesChecked*20;
            default:
                costDevices = numAccessoriesChecked*20;


        }
            radioGroupMemory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (findViewById(checkedId) == findViewById(R.id.radioButton_2GB)) {
                    costMemory = 10 * 2;
                }
                if (findViewById(checkedId) == findViewById(R.id.radioButton_4GB)) {
                    costMemory = 10 * 4;

                }
                if (findViewById(checkedId) == findViewById(R.id.radioButton_8GB)) {
                    costMemory = 10 * 8;

                }
                if (findViewById(checkedId) == findViewById(R.id.radioButton_16GB)) {
                    costMemory = 10 * 16;

                }
            }
        });

        radioGroupStorage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (findViewById(checkedId) == findViewById(R.id.radioButton_250GB)) {
                    costStorage = (float) (0.75 * 250);
                }
                if (findViewById(checkedId) == findViewById(R.id.radioButton_500GB)) {
                    costStorage = (float) (0.75 * 500);
                }
                if (findViewById(checkedId) == findViewById(R.id.radioButton_750GB)) {
                    costStorage = (float) (0.75 * 750);
                }
                if (findViewById(checkedId) == findViewById(R.id.radioButton_1TB)) {
                    costStorage = (float) (0.75 * 1000);
                }

            }
        });
        seekBarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if (progress <= 25) {
                    textViewTip.setText(progress + "%");
                    costTip = progress / 100;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        swDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    costDelivery = (float) 5.95;
                } else if (!isChecked)
                    costDelivery = 0;
            }
        });

    }
}
