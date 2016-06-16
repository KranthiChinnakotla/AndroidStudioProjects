package com.medha.inclass11;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddExpense extends AppCompatActivity {
    EditText Name, Amount, dateEdit;

    Button addExpense;

    Expense expense;
    int day, myyear, month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        final Calendar c = Calendar.getInstance();

        day = c.get(Calendar.DAY_OF_MONTH);

        myyear = c.get(Calendar.YEAR);

        month = c.get(Calendar.MONTH);
        dateEdit = (EditText) findViewById(R.id.expenseDate);

        dateEdit.setFocusable(false);

        dateEdit.setClickable(false);

        findViewById(R.id.expenseDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // DatePickerDialog(this,mydate, year, month, day);

                showDialog(1000);

                StringBuffer myString = new StringBuffer();

                myString.append(day).append("/").append(month).append("/").append(myyear).toString();

                dateEdit.setText(myString);


            }
        });




        Firebase.setAndroidContext(this);
        Firebase fbExp = new Firebase("https://inclass11-5180.firebaseio.com/");
        final Firebase fbExpense = fbExp.child("Expenses");





        Name = (EditText) findViewById(R.id.expenseName);

        Amount = (EditText) findViewById(R.id.expenseAmount);

        dateEdit = (EditText) findViewById(R.id.expenseDate);

        addExpense = (Button) findViewById(R.id.expenseButton);

        final Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"Groceries", "Invoice", "Transportation", "Shopping", "Rent",
                "Trips", "Utilities", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                expense = new Expense();

                String name = Name.getText().toString();
                String amount = Amount.getText().toString();
                String date = dateEdit.getText().toString();

                if(name.equals("") || amount.equals("") || date.equals("")){

                    Toast.makeText(AddExpense.this, "Enter all the fields", Toast.LENGTH_LONG).show();
                }else {

                    expense.setName(name);




                    expense.setAmount(amount);

                    //Toast.makeText(AddExpense.this, "Enter amount", Toast.LENGTH_LONG).show();

                    String category = dropdown.getSelectedItem().toString();

                        expense.setCategory(category);

                        //Toast.makeText(AddExpense.this, "Enter category", Toast.LENGTH_LONG).show();


                    expense.setDate(date);

                    //Toast.makeText(AddExpense.this, "Enter date", Toast.LENGTH_LONG).show();

                    Map<String, String> post2 = new HashMap<String, String>();
                    post2.put("name", name);
                    post2.put("amount", amount);
                    post2.put("category", category);
                    post2.put("user", MainActivity.email);
                    post2.put("date", date);
                    fbExpense.push().setValue(post2);

                    ExpensesList.expenseList.add(expense);

                   Intent i = new Intent(AddExpense.this, ExpensesList.class);
                    startActivity(i);
                    //i.putExtra("expense",expense);
                //    setResult(RESULT_OK, i);
                   // finish();

                }

        }
        });



    }

    protected Dialog onCreateDialog(int id) {

        switch (id) {

            case 1000:
                return new DatePickerDialog(this, mydate, myyear, month, day);

        }

        return null;

    }

    DatePickerDialog.OnDateSetListener mydate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // departureDateText.setText();

            myyear = year;

            month = monthOfYear;

            day = dayOfMonth ;




        }
    };
}
