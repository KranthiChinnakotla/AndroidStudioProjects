package com.medha.inclass042015;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    SeekBar seekBar;
    TextView sbValue,showResult;
    int numHeavyWork;
    Double result ,avgResult;
    Button buttonGenAsynctask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sbValue = (TextView) findViewById(R.id.textView_seekBarValue);
        seekBar = (SeekBar) findViewById(R.id.seekBar_setCmplx);
        seekBar.setMax(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbValue.setText(Integer.toString(progress));
                numHeavyWork = Integer.parseInt(sbValue.getText().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



        buttonGenAsynctask = (Button) findViewById(R.id.button_genAsync);
        buttonGenAsynctask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PerHeavyWork().execute();
            }
        });

    }

    class PerHeavyWork extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            int j=0;
            result = 0.00;
            for(int i = 0; i< numHeavyWork; i++){
                result += HeavyWork.getNumber();
                if(i == numHeavyWork-1){
                    publishProgress(100);
                }
                publishProgress(j);
                j=j+20;
            }
            avgResult = (result/numHeavyWork);
            return avgResult;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Retrieving the number");
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            showResult = (TextView) findViewById(R.id.textView_randomNuVal);
            showResult.setText(result.toString());
            progressDialog.dismiss();


        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress((Integer) values[0]);




        }
    }
}
