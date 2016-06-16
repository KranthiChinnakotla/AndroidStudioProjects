package com.medha.inclass042015;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity_threads extends AppCompatActivity {


    ExecutorService executorService;
    double result,avgResult,result1;
    int numHeavyWork;
    ProgressDialog progressDialog;
    SeekBar seekBar;
    TextView sbValue,showResult;
    Button buttonGenThread;
    Handler handler;
    int a[],b[];
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

        showResult = (TextView) findViewById(R.id.textView_randomNuVal);




        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setMessage("Retrieving the number");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        executorService = Executors.newFixedThreadPool(2);

        buttonGenThread = (Button) findViewById(R.id.button_genthread);
        buttonGenThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.execute(new PerfHeavyWork());
                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {

                        progressDialog.show();
                        b = msg.getData().getIntArray("progress");
                        for (int i=0;i< b.length;i++){
                            progressDialog.setProgress(b[i]);

                            if (progressDialog.getProgress() == 100){
                                progressDialog.dismiss();
                            }
                        }
                        result1 = msg.getData().getDouble("result");
                        showResult.setText(Double.toString(result1));

                        return false;
                    }
                });
            }
        });









    }

    class PerfHeavyWork implements Runnable{

        @Override
        public void run() {

            Bundle data = new Bundle();
            Message msg = new Message();
            a = new int[numHeavyWork];

            int j=0;
            result = 0.00;
            for(int i = 0; i< numHeavyWork; i++){
                result += HeavyWork.getNumber();
                if (i == numHeavyWork -1)
                    j=100;
                else
                    j+=20;
                    a[i] = j;

            }
            avgResult = (result/numHeavyWork);
            data.putIntArray("progress",a);
            data.putDouble("result", avgResult);
            msg.setData(data);
            handler.sendMessage(msg);



        }
    }
}
