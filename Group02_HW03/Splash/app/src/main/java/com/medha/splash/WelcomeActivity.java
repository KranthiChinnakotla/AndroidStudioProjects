package com.medha.splash;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WelcomeActivity extends AppCompatActivity {

    Button btnWelStart, btnWelExit;
    ProgressDialog progressDialog;
    //LinkedList<Questions> listOfQuestions = new LinkedList<Questions>();
    ArrayList<Question> listOfQuestions = new ArrayList<Question>();
    HashMap<String,Integer> options ;
    //HashMap<Integer, Integer> optValues = new HashMap<Integer, Integer>();
    String question, imageUrl;
    int qNo;
    final static String baseUrl = "http://dev.theappsdr.com/apis/spring_2016/hw3/index.php?qid=";
    final static String QUESTIONS = "Questions";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnWelStart = (Button) findViewById(R.id.button_WelStart);
        btnWelExit = (Button) findViewById(R.id.button_Wel_Exit);

        btnWelStart.setEnabled(false);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Questions ....");
        progressDialog.show();



        new GetQuestions().execute(baseUrl + "0");
        new GetQuestions().execute(baseUrl + "1");
        new GetQuestions().execute(baseUrl + "2");
        new GetQuestions().execute(baseUrl + "3");
        new GetQuestions().execute(baseUrl + "4");
        new GetQuestions().execute(baseUrl + "5");
        new GetQuestions().execute(baseUrl + "6");





        btnWelStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, QuizActivity.class);
                Bundle bundle = new Bundle();
                /*for (Question q : listOfQuestions) {
                    bundle.putParcelable(QUESTIONS, q);
                }*/

                bundle.putParcelableArrayList(QUESTIONS, listOfQuestions);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    class GetQuestions extends AsyncTask<String, Void, String> {
        BufferedReader reader = null;

        @Override
        protected void onPreExecute() {

            if (listOfQuestions.size() == 7) {
                progressDialog.dismiss();
                btnWelStart.setEnabled(true);

            }


        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                return stringBuilder.toString();


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
            super.onPostExecute(s);
            options= new HashMap<String,Integer>();
            String quizQuestions[] = s.split(";");
            qNo = Integer.parseInt(quizQuestions[0]);
            question = quizQuestions[1];
            if (quizQuestions[(quizQuestions.length) - 1].contains("http")) {
                imageUrl = quizQuestions[quizQuestions.length - 1];
            } else
                imageUrl = "";
            for (int i = 2; i < quizQuestions.length-2; i=i+2) {
                    options.put(quizQuestions[i].toString(),Integer.parseInt(quizQuestions[i + 1]));
            }
            Question qObject = new Question(qNo,question,options,imageUrl);
            listOfQuestions.add(qObject);
            if (listOfQuestions.size() == 7) {
                progressDialog.dismiss();
                btnWelStart.setEnabled(true);

            }

        }
    }
}
