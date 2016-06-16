package com.inclass.raja.homework5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotesActivity extends AppCompatActivity {

    Button btnSave;
    EditText enterNotes;
    String notes;
    Forecast forecast;
    DatabaseDataManager dmNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        forecast = (Forecast) getIntent().getSerializableExtra("date");
        enterNotes = (EditText) findViewById(R.id.editText);
        btnSave = (Button) findViewById(R.id.button_saveNotes);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enterNotes.getText().toString().equals("")){
                    notes = enterNotes.getText().toString();
                    forecast.setNotes(notes);
                    dmNotes = new DatabaseDataManager(AddNotesActivity.this,"citynotes");
                    dmNotes.saveNotes(new Notes(forecast.getDate(), notes));

                    Intent intent = new Intent(AddNotesActivity.this,ForecastActivity.class);
                    intent.putExtra("notes", forecast);
                    setResult(ForecastActivity.RESULT_OK,intent);
                    finish();



                }
                else if(enterNotes.getText().toString().equals(""))
                    Toast.makeText(AddNotesActivity.this,"Enter notes to save",Toast.LENGTH_LONG).show();
            }
        });
    }
}
