package com.medha.inclass3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    final static String STUD_KEY = "STUDENT";
    EditText editTextName,editTextEmail;
    RadioGroup radioGroupLanguages;
    RadioButton rbJava,rbC,rbCSharp;
    Switch actSearch;
    SeekBar studentMood;
    Button buttonSubmit;
    int seekbarProgress = 15;
    int increment = 5;
    String java, c, cSharp,  language;
    boolean actsrchSwitch;
    Student student;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextName = (EditText) findViewById(R.id.editText_studentName);
        editTextEmail = (EditText) findViewById(R.id.editText_email);
        radioGroupLanguages = (RadioGroup) findViewById(R.id.rg_languages);
        rbJava = (RadioButton) findViewById(R.id.radioButton_java);
        rbC = (RadioButton) findViewById(R.id.radioButton_C);
        rbCSharp = (RadioButton) findViewById(R.id.radioButton_CSharp);
        actSearch = (Switch) findViewById(R.id.switch_actsrch);
        studentMood = (SeekBar) findViewById(R.id.seekBar_mood);
        buttonSubmit = (Button) findViewById(R.id.button_Submit);



        actSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    actsrchSwitch = true;
                else
                    actsrchSwitch = false;
            }
        });

        studentMood.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = (int) Math.round(progress / increment) * increment;
                seekBar.setProgress(progress);
                seekbarProgress = progress;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

       radioGroupLanguages = (RadioGroup) findViewById(R.id.rg_languages);


        radioGroupLanguages.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.radioButton_java:
                            java = rbJava.getText().toString();
                        break;
                    case R.id.radioButton_C:
                            c = rbC.getText().toString();
                        break;
                    case R.id.radioButton_CSharp:
                        cSharp = rbCSharp.getText().toString();
                        break;
                    default:
                        java = rbJava.getText().toString();
                }

                if (java != null)
                    language = java;
                if (c != null)
                    language = c;
                if (cSharp != null)
                    language = cSharp;

            }
        });


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextName.length() == 0 || editTextName.toString() == null) {
                    Toast.makeText(MainActivity.this, "Enter Student name", Toast.LENGTH_LONG).show();
                } else if (editTextEmail.length() == 0 || editTextEmail.toString() == null) {
                    Toast.makeText(MainActivity.this, "Enter Student eMail", Toast.LENGTH_LONG).show();
                } else if (!rbJava.isChecked() && !rbC.isChecked() && !rbCSharp.isChecked()) {
                    Toast.makeText(MainActivity.this, "Must select a language for each student", Toast.LENGTH_LONG).show();
                } else if (studentMood.getProgress() == 0) {
                    Toast.makeText(MainActivity.this, "Enter the mood of the student", Toast.LENGTH_LONG).show();
                } else {

                    student = new Student(editTextName.getText().toString(),editTextEmail.getText().toString(),language,actsrchSwitch,seekbarProgress);
                    Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                    intent.putExtra(STUD_KEY,student);
                    startActivity(intent);

                }

            }
        });






    }


}
