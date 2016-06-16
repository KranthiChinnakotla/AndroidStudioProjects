package com.medha.splash;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView textViewDesc,textViewResvalue;
    ImageView imageView;
    Button btnTryagain,btnQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewDesc = (TextView) findViewById(R.id.textView_descrp);
        textViewResvalue = (TextView) findViewById(R.id.textView_resvalues);
        imageView = (ImageView) findViewById(R.id.imageView_resultImage);

        int resultSum = getIntent().getExtras().getInt("result");
        int resultCase;

        if(resultSum < 10)
            resultCase = 1;
        else if (resultSum > 10 && resultSum < 50)
            resultCase = 2;
        else if (resultSum > 50 && resultSum < 73)
            resultCase = 3;
        else
        resultCase = 0;



        switch (resultCase){
            case 1:
                textViewResvalue.setText("NON-GEEK");
                imageView.setImageResource(R.drawable.non_geek);
                textViewDesc.setText(R.string.non_geek_desc);
                break;
            case 2:
                textViewResvalue.setText("SEMI-GEEK");
                imageView.setImageResource(R.drawable.semi_geek);
                textViewDesc.setText(R.string.semi_geek_desc);
                break;
            case 3:
                textViewResvalue.setText("UBER-GEEK");
                imageView.setImageResource(R.drawable.uber_geek);
                textViewDesc.setText(R.string.uber_geek_desc);
                break;

            default:
                Toast.makeText(ResultActivity.this,"Try Again",Toast.LENGTH_LONG).show();



        }

        btnQuit = (Button) findViewById(R.id.button_resQuit);
        btnTryagain = (Button) findViewById(R.id.button_resultTry);
        

        btnTryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
                Bundle bundle = new Bundle();
                /*for (Question q : listOfQuestions) {
                    bundle.putParcelable(QUESTIONS, q);
                }*/

                bundle.putParcelableArrayList(QUESTIONS, listOfQuestions);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,WelcomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
