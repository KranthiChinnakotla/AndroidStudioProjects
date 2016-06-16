package com.medha.inclass05;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {


    String result[];
    String splitResult[] ;
    ArrayList<String> resultList = new ArrayList<String>();
    String keys ;
    ArrayList<String> valuesUNCC = new ArrayList<String>();
    ArrayList<String> valuesAndroid = new ArrayList<String>();
    ArrayList<String> valuesWinter = new ArrayList<String>();
    ArrayList<String> valuesAurora = new ArrayList<String>();
    ArrayList<String> valuesWonders = new ArrayList<String>();
    HashMap<String,ArrayList<String>> photos = new HashMap<>();
    EditText editText;
    Button buttonGo;
    ImageView imageView;
    ImageButton imNext,imPrev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText_search);
        buttonGo = (Button) findViewById(R.id.button_go);
        imageView = (ImageView) findViewById(R.id.imageView);

        photos.put("UNCC",valuesUNCC);
        photos.put("Android",valuesAndroid);
        photos.put("aurora",valuesAurora);
        photos.put("wonders",valuesWonders);
        photos.put("winter",valuesWinter);


        new GetList().execute("http://dev.theappsdr.com/apis/spring_2016/inclass5/URLs.txt");

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isConnectedOnline()) {
                    switch (editText.getText().toString()){
                        case "UNCC":
                        {new RequestParams(MainActivity.this).execute(photos.get("UNCC"));
                            break;}

                        case "Android":
                        { new RequestParams(MainActivity.this).execute(photos.get("Android"));
                            break;}

                        case  "aurora":
                        {new RequestParams(MainActivity.this).execute(photos.get("aurora"));
                            break;}

                        case "winter":
                        {new RequestParams(MainActivity.this).execute(photos.get("winter"));
                            break;}

                        case "wonders":
                        {new RequestParams(MainActivity.this).execute(photos.get("wonders"));
                            break;}

                        default:
                            Toast.makeText(MainActivity.this,"Search is invalid",Toast.LENGTH_LONG).show();

                    }


                } else
                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_LONG).show();
            }
        });


        imNext = (ImageButton) findViewById(R.id.imageButton_next);


        imPrev = (ImageButton) findViewById(R.id.imageButton_prev);




    }

    private boolean isConnectedOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }
        return false;
    }

    private class GetList extends AsyncTask<String,Void,String>{

        BufferedReader reader = null;
        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                StringBuilder sb = new StringBuilder();
                while((line=reader.readLine())!=null){
                    sb.append(line);
                }
               return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            if(s!=null) {
                result = s.split(";");

                for(String str: result){
                    splitResult = str.split(",");
                     for(String stri: splitResult){
                         resultList.add(stri);
                     }
                }
            }
            int j = 0,k=0,p=0,r=0,t=0;
         for (int i=0; i <resultList.size();i++){

             while (j < resultList.size()){
                 if(resultList.get(j).equals("UNCC")){
                     valuesUNCC.add(resultList.get(j+1));
                 }
                 j++;
             }
         }

            for (int i=0; i <resultList.size();i++){
                while (k<resultList.size()){
                    if(resultList.get(k).equals("Android")){
                        valuesAndroid.add(resultList.get(k+1));
                    }
                    k++;
                }
            }

            for (int i=0; i <resultList.size();i++){
                while (p<resultList.size()){
                    if(resultList.get(p).equals("winter")){
                        valuesWinter.add(resultList.get(p+1));
                    }
                    p++;
                }
            }

            for (int i=0; i <resultList.size();i++){
                while (r<resultList.size()){
                    if(resultList.get(r).equals("aurora")){
                        valuesAurora.add(resultList.get(r+1));
                    }
                    r++;
                }
            }

            for (int i=0; i <resultList.size();i++){
                while (t<resultList.size()){
                    if(resultList.get(t).equals("wonders")){
                        valuesWonders.add(resultList.get(t+1));
                    }
                    t++;
                }
            }
        }
      }
    }



