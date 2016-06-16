package com.medha.inclass3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {


    ImageView imageViewName,imageVieweMail,imageViewProgramLanguage,imageViewAccountState,imageViewMood;
    TextView tvName,tvEmail,tvlang,tvActSrch,tvMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvName = (TextView) findViewById(R.id.textView_displayName);
        tvEmail = (TextView) findViewById(R.id.textView_displayEmail);
        tvlang = (TextView) findViewById(R.id.textView_displayLanguage);
        tvActSrch = (TextView) findViewById(R.id.textView_displayactst);
        tvMood = (TextView) findViewById(R.id.textView_mood);


        if (getIntent().getExtras()!= null){
            Student student = (Student) getIntent().getExtras().getSerializable(MainActivity.STUD_KEY);
            tvName.setText(student.getName());
            tvEmail.setText(student.geteMail());
            tvlang.setText(student.getProgrammingLanguage());

            if(student.isAcctState())
                tvActSrch.setText("Searchable");
            else
                tvActSrch.setText("Un Searchable");
            if(student.getMood()>= 50)
                tvMood.setText(student.getMood()+" %  Positive");
            else
                tvMood.setText(student.getMood()+ " % Negative");



        }
        imageViewName = (ImageView) findViewById(R.id.imageView_Name);
        imageViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageVieweMail = (ImageView) findViewById(R.id.imageView_email);
        imageVieweMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageViewProgramLanguage = (ImageView) findViewById(R.id.imageView_proglan);
        imageViewProgramLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageViewAccountState = (ImageView) findViewById(R.id.imageView_AccountState);
        imageViewAccountState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageViewMood = (ImageView) findViewById(R.id.imageView_mood);
        imageViewMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

}
