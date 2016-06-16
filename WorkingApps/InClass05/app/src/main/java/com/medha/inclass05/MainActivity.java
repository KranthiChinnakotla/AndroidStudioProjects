package com.medha.inclass05;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    String result[];
    String category;
    String splitResult[];
    ArrayList<String> resultList;
    HashMap<String, ArrayList<String>> photos;
    ArrayList<String> uncc, android, wonders, winters, aurora;
    EditText editText;
    Button buttonGo;
    ImageView imageView;
    ImageButton imNext, imPrev;
    int i, j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText_search);
        buttonGo = (Button) findViewById(R.id.button_go);
        imageView = (ImageView) findViewById(R.id.imageView);
        new GetList().execute("http://dev.theappsdr.com/apis/spring_2016/inclass5/URLs.txt");

        j = 1;

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isConnectedOnline()) {

                    for (String s : photos.keySet()) {

                        if (editText.getText().toString().equals(s)) {
                            new RequestParams(MainActivity.this).execute(String.valueOf(photos.get(s).get(0)));
                            category = editText.getText().toString();
                            i = photos.get(category).size() - 1;
                        }
                    }
                } else
                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_LONG).show();
            }
        });


        imNext = (ImageButton) findViewById(R.id.imageButton_next);
        imNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnectedOnline()) {
                    if (category != null) {

                        if (j < photos.get(category).size()) {
                            new RequestParams(MainActivity.this).execute(String.valueOf(photos.get(category).get(j)));
                            j++;
                        } else {
                            j=0;
                            new RequestParams(MainActivity.this).execute(String.valueOf(photos.get(category).get(j)));

                        }


                    }

                } else
                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_LONG).show();

            }
        });

        imPrev = (ImageButton) findViewById(R.id.imageButton_prev);
        imPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnectedOnline()) {
                    if (category != null) {
                        if (i >= 0) {
                            new RequestParams(MainActivity.this).execute(String.valueOf(photos.get(category).get(i)));
                            i--;
                        }
                        else {
                            i =  photos.get(category).size() - 1;
                            new RequestParams(MainActivity.this).execute(String.valueOf(photos.get(category).get(i)));
                        }
                    }


                } else
                    Toast.makeText(MainActivity.this, "Not Connected", Toast.LENGTH_LONG).show();

            }
        });


    }

    private boolean isConnectedOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private class GetList extends AsyncTask<String, Void, String> {

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
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
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

            resultList = new ArrayList<String>();
            photos = new HashMap<String, ArrayList<String>>();
            //uncc,android,wonders,winters,aurora;
            uncc = new ArrayList<String>();
            android = new ArrayList<String>();
            wonders = new ArrayList<String>();
            winters = new ArrayList<String>();
            aurora = new ArrayList<String>();


            if (s != null) {
                result = s.split(";");

                for (String str : result) {
                    splitResult = str.split(",");
                    for (String stri : splitResult) {
                        resultList.add(stri);
                    }
                }
            }


            for(int i = 0; i <resultList.size();i++) {
                switch (resultList.get(i).toString()) {
                    case "UNCC":
                        uncc.add(resultList.get(i + 1));
                        break;
                    case "Android":
                        android.add(resultList.get(i + 1));
                        break;
                    case "aurora":
                        aurora.add(resultList.get(i + 1));
                        break;
                    case "winter":
                        winters.add(resultList.get(i + 1));
                        break;
                    case "wonders":
                        wonders.add(resultList.get(i + 1));
                        break;
                    default:
                        break;

                }
            }
            if(uncc.size()>0)
            photos.put("UNCC", uncc);
            if(android.size()>0)
            photos.put("Android", android);
            if(winters.size()>0)
            photos.put("winter", winters);
            if(wonders.size()>0)
            photos.put("wonders", wonders);
            if(aurora.size()>0)
            photos.put("aurora", aurora);

        }
    }
}


