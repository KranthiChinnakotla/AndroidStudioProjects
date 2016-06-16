package com.example.tejakanchinadam.splash;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;


public class Welcome extends AppCompatActivity{

    public static String LIST_KEY = "LIST";

    Question question = new Question();

    ArrayList<Question> sendListOfQuestions;

    String urlString;

    int i;

    View pg;

    Button exit, startQuiz;


    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        exit = (Button) findViewById(R.id.button2);

        startQuiz = (Button) findViewById(R.id.button3);

        pg = findViewById(R.id.progressBar);
        sendListOfQuestions = new ArrayList<Question>();


        urlString = "http://dev.theappsdr.com/apis/spring_2016/hw3/index.php?qid=";


        i = 0;


        if (isConnectedOnline()){

            // new GetDataWithParams().execute("http://dev.theappsdr.com/apis/spring_2016/inclass5/URLs.txt");

           while (i < 7) {


                RequestParams params = new RequestParams("POST", urlString + i);

                new GetDataWithParams().execute(params);

               i++;

            }


        } else {

            Toast.makeText(getApplicationContext(), "Please connect to the Internet", Toast.LENGTH_LONG).show();


        }



        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });


        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Welcome.this, QuizActivity.class);

                intent.putParcelableArrayListExtra(LIST_KEY, sendListOfQuestions);

                startActivity(intent);

            }
        });






    }


    private class GetDataWithParams extends AsyncTask<RequestParams , Void, String> {


        @Override
        protected String doInBackground(RequestParams... params) {


            BufferedReader reader = null;

            try {
                HttpURLConnection con = params[0].starsConnection();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                StringBuilder sb = new StringBuilder();

                String line = "";

                while ((line = reader.readLine()) != null) {

                    sb.append(line + "\n");

                }

                return sb.toString();




            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {

                    if (reader != null) {

                        reader.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return null;
        }


        @Override
        protected void onPostExecute(String s) {

           if (s != null) {

               //s = s.replaceAll("â€¦","?");

               String[] wholeContent = s.split(";");

               String tempImageURl = null;


               if (wholeContent.length == 9){

                   tempImageURl = wholeContent[8];


                   String dummyQuestion = "A";

                   int dummyInt = 0;

                   question = new Question(Integer.parseInt(wholeContent[0]),wholeContent[1], wholeContent[2], Integer.parseInt(wholeContent[3]),
                           wholeContent[4], Integer.parseInt(wholeContent[5]), wholeContent[6], Integer.parseInt(wholeContent[7]), dummyQuestion,dummyInt ,dummyQuestion,dummyInt, tempImageURl);

                   sendListOfQuestions.add(question);




               } else if (wholeContent.length == 11) {

                   tempImageURl = wholeContent[10];

                   String dummyQuestion = "A";

                   int dummyInt = 0;

                   question = new Question(Integer.parseInt(wholeContent[0]),wholeContent[1], wholeContent[2], Integer.parseInt(wholeContent[3]),
                           wholeContent[4], Integer.parseInt(wholeContent[5]), wholeContent[6], Integer.parseInt(wholeContent[7]), wholeContent[8], Integer.parseInt(wholeContent[9]), dummyQuestion,dummyInt, tempImageURl);

                   sendListOfQuestions.add(question);


               } else if (wholeContent.length == 12){

                   tempImageURl = "A";

                   int mylastInteger = Integer.parseInt(wholeContent[11].replaceAll("\\s", ""));


                   question = new Question(Integer.parseInt(wholeContent[0]),wholeContent[1], wholeContent[2], Integer.parseInt(wholeContent[3]),
                           wholeContent[4], Integer.parseInt(wholeContent[5]), wholeContent[6], Integer.parseInt(wholeContent[7]), wholeContent[8], Integer.parseInt(wholeContent[9]),wholeContent[10], mylastInteger, tempImageURl);

                   sendListOfQuestions.add(question);

               } else if (wholeContent.length == 8){

                   tempImageURl = "A";

                   String dummyQuestion = "A";

                   int dummyInt = 0;

                   String myString = wholeContent[1];

                   int mylastInteger = Integer.parseInt(wholeContent[7].replaceAll("\\s", ""));

                   question = new Question(Integer.parseInt(wholeContent[0]),myString, wholeContent[2], Integer.parseInt(wholeContent[3]),
                           wholeContent[4], Integer.parseInt(wholeContent[5]), wholeContent[6], mylastInteger, dummyQuestion,dummyInt ,dummyQuestion,dummyInt, tempImageURl);

                   sendListOfQuestions.add(question);

               }

           }


            if (sendListOfQuestions.size() == 7){

                pg.setVisibility(View.INVISIBLE);

                startQuiz.setClickable(true);

                startQuiz.setAlpha(1f);

            }

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            startQuiz.setClickable(false);

            startQuiz.setAlpha(.5f);


        }
    }

    private boolean isConnectedOnline(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo =  cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            return true;

        } else {

            return false;
        }

    }

}
