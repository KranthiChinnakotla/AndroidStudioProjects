package com.medha.splash;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {
    TextView qNum,questTV;
    ImageView imageUrl;
    RadioGroup radioGroup;
    RadioButton radioOptBtn;
    Button btnNext,btnQuit;
    ArrayList<Question> listOfQuestions = new ArrayList<Question>();
    ArrayList<Integer> optValues = new ArrayList<Integer>();
    Set<String> keySet;
    HashMap<Integer,Question> hashQuestions;
    Integer loadIndex=0;
    int count =0;
    int toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        listOfQuestions = bundle.getParcelableArrayList(WelcomeActivity.QUESTIONS);
        btnNext = (Button) findViewById(R.id.button_next);
        btnQuit = (Button) findViewById(R.id.button_Quit);
        qNum = (TextView) findViewById(R.id.textView_Qnum);
        questTV = (TextView) findViewById(R.id.textView_Question);
        hashQuestions = new HashMap<Integer, Question>();
        for(Question q: listOfQuestions){
            hashQuestions.put(q.getQuesId(),q);
        }

        //region Intial Load of the page

        qNum.setText("Q" + hashQuestions.get(loadIndex).getQuesId());
        questTV.setText(hashQuestions.get(loadIndex).getQuesText());
        btnNext.setEnabled(false);
        GenerateDynamicFields();


        //endregion
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (toggle==0){

                    Toast.makeText(getApplicationContext(), "Please select a value", Toast.LENGTH_LONG).show();

                }else {


                    loadIndex++;

                    if (loadIndex < listOfQuestions.size()) {
                        qNum.setText("Q" + hashQuestions.get(loadIndex).getQuesId());
                        questTV.setText(hashQuestions.get(loadIndex).getQuesText());
                        GenerateDynamicFields();


                    } else {
                        Toast.makeText(getApplicationContext(), "Reached End", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        intent.putExtra("result", count);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(QUESTIONS, listOfQuestions);
                        bundle.putParcelableArrayList(QUESTIONS, listOfQuestions);
                        startActivity(intent);


                    }
                    toggle=0;

                }
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
            }
        });
    }
    public void GenerateDynamicFields(){
        optValues.clear();
        radioGroup = (RadioGroup) findViewById(R.id.rg_Quiz);
        radioGroup.removeAllViews();
        Random random = new Random();
        keySet = hashQuestions.get(loadIndex).getQuesOptions().keySet();
        int index = random.nextInt(keySet.size());
        if(index == 0){
        for(Map.Entry<String,Integer> entry:  hashQuestions.get(loadIndex).getQuesOptions().entrySet()){
            radioOptBtn = new RadioButton(QuizActivity.this);
            radioOptBtn.setText(entry.getKey());
            radioOptBtn.setId(index);
            radioGroup.addView(radioOptBtn);
            optValues.add(entry.getValue());
            index++;}
        }

        if (index == keySet.size()-1){
            for(Map.Entry<String,Integer> entry:  hashQuestions.get(loadIndex).getQuesOptions().entrySet()){
                radioOptBtn = new RadioButton(QuizActivity.this);
                radioOptBtn.setText(entry.getKey());
                radioOptBtn.setId(index);
                radioGroup.addView(radioOptBtn);
                optValues.add(entry.getValue());
                index--;}
        }

        if(index > 0 && index < keySet.size()-1){
            index = 0;
            for(Map.Entry<String,Integer> entry:  hashQuestions.get(loadIndex).getQuesOptions().entrySet()){
                radioOptBtn = new RadioButton(QuizActivity.this);
                radioOptBtn.setText(entry.getKey());
                radioOptBtn.setId(index);
                radioGroup.addView(radioOptBtn);
                optValues.add(entry.getValue());
                index++;}
        }




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               count = count + optValues.get(checkedId);

                RadioButton rb = (RadioButton) findViewById(checkedId);
                if(rb.isChecked()) {
                    toggle = 1;
                }
                else {
                    toggle = 0;
                }

            }
        });


        if(!hashQuestions.get(loadIndex).getImgUrl().equals("")){
            new LoadImage().execute(hashQuestions.get(loadIndex).getImgUrl());
        }
        else {
            imageUrl = (ImageView) findViewById(R.id.imageView);
            imageUrl.setImageBitmap(null);
            Toast.makeText(getApplicationContext(),"No Image",Toast.LENGTH_LONG).show();
            btnNext.setEnabled(true);
        }
    }

    private class LoadImage extends AsyncTask<String,Void,Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnNext.setEnabled(false);

        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                Bitmap image= BitmapFactory.decodeStream(connection.getInputStream());
                return image;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageUrl = (ImageView) findViewById(R.id.imageView);
            imageUrl.setImageBitmap(bitmap);
            btnNext.setEnabled(true);
        }
    }

}