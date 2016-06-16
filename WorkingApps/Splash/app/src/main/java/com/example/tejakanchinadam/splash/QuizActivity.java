package com.example.tejakanchinadam.splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    Question question = new Question();

    ArrayList<Question> al ;

    Bundle bundle;

    HashMap<Integer, Question> hp = new HashMap<Integer,Question>();

    TextView tx1, tx2;

    int counter;

    ImageView image1;

    ProgressBar pg;

    RadioGroup rg;

    ArrayList<Integer> scores = new ArrayList<Integer>();

    int[] newX;

    int id;

    int[] myArray = {0,1,2,3,4,5,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        counter = 0;

        if (isConnectedOnline()){


        }else {

            Toast.makeText(getApplicationContext(), "Please connect to the Internet", Toast.LENGTH_LONG).show();

        }


        if (getIntent().getExtras() != null) {


            al = getIntent().getExtras().getParcelableArrayList(Welcome.LIST_KEY);


            for (int i = 0; i < al.size(); i++) {

                hp.put(i, al.get(i));

            }
        }


        rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
            }
        });

        tx1 = (TextView) findViewById(R.id.questionText);

        tx2 = (TextView) findViewById(R.id.questionNumber);

        image1 = (ImageView) findViewById(R.id.questionImage);

        pg = (ProgressBar) findViewById(R.id.pg2);

        findViewById(R.id.quitBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();

            }

        });



        tx1.setText(hp.get(counter).questionText);

        if (!(hp.get(counter).imageURL).equals("A")) {

            RequestParams params = new RequestParams("POST", hp.get(counter).imageURL);

            new GetImage().execute(params);

        }

        if (hp.get(counter).option4.equals("A")){

            String[] radioButtonsArray = {hp.get(counter).option1, hp.get(counter).option2, hp.get(counter).option3};

            //newX = null;

            newX = new int[radioButtonsArray.length];

            newX = AddRadioButtons(radioButtonsArray);

            printNumbers(newX);


        }

        tx2.setText("Q1");

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = rg.getCheckedRadioButtonId();

                if (id == -1) {

                    Toast.makeText(getApplicationContext(), "Please select a value", Toast.LENGTH_LONG).show();

                } else {

                    if (counter == 0) {

                        if (id == newX[0]) {
                            //Log.d("Demo", newX[0] + "");
                            //scores.add(hp.get(0).score1);
                            scores.add(getScores(getIndex(myArray, newX[0]), 0));

                        } else if (id == newX[1]) {
                            //Log.d("Demo", newX[1] + "");
                            //scores.add(hp.get(0).score2);
                            scores.add(getScores(getIndex(myArray, newX[1]), 0));

                        } else if (id == newX[2]) {
                            //Log.d("Demo", newX[2] + "");
                            //scores.add(hp.get(0).score3);
                            scores.add(getScores(getIndex(myArray, newX[2]), 0));
                        } else {
                            //Log.d("Demo", "Null");

                        }

                        removeButtons();

                        rg.clearCheck();


                    }


                    counter++;

                    if (counter == 7) {

                        if (id == newX[0]) {

                            scores.add(getScores(getIndex(myArray, newX[0]), 6));

                        } else if (id == newX[1]) {
                            //Log.d("Demo", newX[1] + "");
                            //scores.add(hp.get(6).score2);
                            scores.add(getScores(getIndex(myArray, newX[1]), 6));

                        } else if (id == newX[2]) {
                            //Log.d("Demo", newX[2] + "");
                            //scores.add(hp.get(6).score3);
                            scores.add(getScores(getIndex(myArray, newX[2]), 6));
                        } else {
                            //Log.d("Demo", "Null");
                        }


                        //Intent intent = new Intent(QuizActivity.this, );

                        int sum = 0;
                        for (int d : scores) {
                            sum += d;
                        }

                        Intent mainIntent = new Intent(QuizActivity.this, ResultActivity.class);

                        mainIntent.putExtra("score", sum);

                        startActivityForResult(mainIntent, 1);



                    } else if (counter != 0 && counter < 7) {

                        if (id == newX[0]) {
                                //Log.d("Demo", newX[0] + "");

                                scores.add(getScores(getIndex(myArray, newX[0]), counter));

                                //scores.add(hp.get(counter).score1);

                            } else if (id == newX[1]) {
                                //Log.d("Demo", newX[1] + "");
                                scores.add(getScores(getIndex(myArray, newX[1]), counter));
                                //scores.add(hp.get(counter).score2);

                            } else if (id == newX[2]) {
                                //Log.d("Demo", newX[2] + "");
                                scores.add(getScores(getIndex(myArray, newX[2]), counter));
                                //scores.add(hp.get(counter).score3);
                            } else if (id == newX[3]) {
                                //Log.d("Demo", newX[3] + "");
                                scores.add(getScores(getIndex(myArray, newX[3]), counter));
                                //scores.add(hp.get(counter).score4);
                            } else if (id == newX[4]) {
                                //Log.d("Demo", newX[4] + "");
                                scores.add(getScores(getIndex(myArray, newX[4]), counter));
                                //scores.add(hp.get(counter).score5);
                            } else {
                                //Log.d("Demo", "Null");
                            }

                        removeButtons();

                        rg.clearCheck();

                            tx1.setText(hp.get(counter).questionText);

                            tx2.setText("Q" + (counter + 1));

                            if (!(hp.get(counter).imageURL).equals("A")) {

                                RequestParams params = new RequestParams("POST", hp.get(counter).imageURL);

                                new GetImage().execute(params);

                            } else {

                                Toast.makeText(getApplicationContext(), "No Image", Toast.LENGTH_LONG).show();

                            }

                            if (hp.get(counter).option4.equals("A")) {

                                String[] radioButtonsArray = {hp.get(counter).option1, hp.get(counter).option2, hp.get(counter).option3};

                                //newX = null;

                                newX = new int[radioButtonsArray.length];

                                newX = AddRadioButtons(radioButtonsArray);
                                printNumbers(newX);


                            } else if (hp.get(counter).option5.equals("A")) {

                                String[] radioButtonsArray = {hp.get(counter).option1, hp.get(counter).option2, hp.get(counter).option3, hp.get(counter).option4};

                                //newX = null;

                                newX = new int[radioButtonsArray.length];

                                newX = AddRadioButtons(radioButtonsArray);
                                printNumbers(newX);

                            } else if (hp.get(counter).option3.equals("A")) {

                                String[] radioButtonsArray = {hp.get(counter).option1, hp.get(counter).option2};

                                //newX = null;

                                newX = new int[radioButtonsArray.length];

                                newX = AddRadioButtons(radioButtonsArray);
                                printNumbers(newX);

                            } else {

                                String[] radioButtonsArray = {hp.get(counter).option1, hp.get(counter).option2, hp.get(counter).option3, hp.get(counter).option4, hp.get(counter).option5};

                                //newX = null;

                                newX = new int[radioButtonsArray.length];

                                newX = AddRadioButtons(radioButtonsArray);

                                printNumbers(newX);

                        }

                        }else {

                        Toast.makeText(getApplicationContext(), "Enough", Toast.LENGTH_LONG).show();

                        counter = counter - 1;



                    }
                }

            }
        });

    }


    private class GetImage extends AsyncTask<RequestParams , Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(RequestParams... params) {


            BufferedReader reader = null;

            try {
                HttpURLConnection con = params[0].starsConnection();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

               Bitmap image = BitmapFactory.decodeStream(con.getInputStream());

                return image;




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
        protected void onPostExecute(Bitmap s) {

            if (s != null) {

                pg.setVisibility(View.INVISIBLE);

                image1.setImageBitmap(s);

            }

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pg.setVisibility(View.VISIBLE);


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

    public int[] AddRadioButtons(String[] array){

        rg = (RadioGroup) findViewById(R.id.radioGroup);
        RadioGroup.LayoutParams rprms;

        String[] newA = new String[array.length];

        //ArrayList<Integer> newB = new ArrayList<Integer>();

        int[] newB = new int[array.length];

        Object[] newAB = {newA, newB};

        newAB = ShuffleArray(array);

        newA = (String[]) newAB[0];

        newB = (int[]) newAB[1];

        for(int i=0;i<newA.length;i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(newA[i]);
            radioButton.setId(i);
            rprms= new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            rg.addView(radioButton, rprms);
        }

        return newB;


    }


    public void removeButtons(){

        rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.removeAllViews();

        image1.setImageResource(0);

    }




    private Object[] ShuffleArray(String[] array) {
        int index, temp1;
        String temp;
        Random random = new Random();

        int[] tempArray = new int[array.length];

        for (int j = 0; j < array.length; j++){

            tempArray[j] = j;
        }



        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            temp1 = tempArray[index];
            array[index] = array[i];
            tempArray[index] = tempArray[i];
            array[i] = temp;
            tempArray[i] = temp1;
        }


        return new Object[]{array, tempArray};


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){

               finish();

                startActivity(getIntent());

            }
            if (resultCode == Activity.RESULT_CANCELED) {

                Intent i = new Intent(QuizActivity.this, Welcome.class);

                startActivity(i);

                finish();

            }
        }


    }


    public int getIndex(int[] ind, int id) {
        for (int i = 0; i < ind.length; i++) {
            if (ind[i] == id) {
                return i;
            }
        }

        return Integer.parseInt(null);
    }

    public int getScores(int i, int counter ){

        if (i == 0){

            return hp.get(counter).score1;

        }else if(i==1){

            return hp.get(counter).score2;
        }else if(i==2){

            return hp.get(counter).score3;
        }else if(i==3){

            return hp.get(counter).score4;
        }else if(i==4){

            return hp.get(counter).score5;
        }else {

            return Integer.parseInt(null);
        }


    }


    public void printNumbers(int[] array){


        for(int i=0; i<array.length; i++){

            Log.d("Demo",array[i]+"");

        }

    }


}
