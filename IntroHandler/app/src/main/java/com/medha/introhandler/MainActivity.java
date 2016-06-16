package com.medha.introhandler;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Computing Progress");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);

        setContentView(R.layout.activity_main);
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                switch (msg.what){
                    case DoWork.STATUS_START:
                        progressDialog.show();
                        break;
                    case DoWork.STATUS_DONE:
                        progressDialog.dismiss();
                        break;
                    case DoWork.STATUS_STEP:
                        progressDialog.setProgress(msg.getData().getInt("progress"));
                        //progressDialog.setProgress((Integer)msg.obj);
                        break;

                }
                return false;
            }
        });

        new Thread(new DoWork()).start();



    }

    class DoWork implements Runnable {

        static final int STATUS_START = 0x00;
        static final int STATUS_STEP = 0x01;
        static final int STATUS_DONE = 0x02;

        @Override
        public void run() {

            Message msg = new Message();
            msg.what = STATUS_START;
            handler.sendMessage(msg);


            for (int i=0; i < 100 ; i++){
                for(int j=0;j< 1000;j++){

                    msg = new Message();
                    msg.what = STATUS_STEP;
                    //msg.obj = i+1;
                    Bundle data = new Bundle();
                    data.putInt("progress",i+1);
                    msg.setData(data);
                    handler.sendMessage(msg);


                }
            }

            msg = new Message();
            msg.what = STATUS_DONE;
            handler.sendMessage(msg);

        }
    }
}
