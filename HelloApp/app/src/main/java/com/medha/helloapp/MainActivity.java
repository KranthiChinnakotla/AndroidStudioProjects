package com.medha.helloapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity  {

    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

      rg = (RadioGroup)findViewById(R.id.RadioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                Log.d("Demo",rb.getText().toString());
            }
        });

        Button btn = (Button) findViewById(R.id.buttonGetChecked);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId() == R.id.radioButtonBlue)
                    Log.d("Demo","Blue was clicked");
                else if (rg.getCheckedRadioButtonId() == R.id.radioButtonGreen)
                    Log.d("Demo","Green was clicked");
                else if (rg.getCheckedRadioButtonId()== R.id.radioButtonRed)
                    Log.d("Demo","Red was clicked");
                else if (rg.getCheckedRadioButtonId() == -1)
                    Log.d("Demo", "None was clicked");
            }
        });

    }

}
