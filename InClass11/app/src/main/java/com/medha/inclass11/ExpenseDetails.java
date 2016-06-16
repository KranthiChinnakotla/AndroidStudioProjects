package com.medha.inclass11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.Firebase;

public class ExpenseDetails extends AppCompatActivity {

    TextView tvName,tvDate,tvCategory,tvAmount;
    static String expId;
    Firebase fbExpDet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);
        Firebase.setAndroidContext(this);
        fbExpDet = new Firebase("https://inclass11-5180.firebaseio.com/Expenses");
        Expense detExp = (Expense) getIntent().getExtras().getSerializable("racha");

        tvName = (TextView) findViewById(R.id.tvNameValue);
        tvCategory = (TextView) findViewById(R.id.tVCategoryValue);
        tvAmount = (TextView) findViewById(R.id.tVAmountValue);
        tvDate = (TextView) findViewById(R.id.tVDateValue);

        tvName.setText(detExp.getName());
        tvCategory.setText(detExp.getCategory());
        tvAmount.setText("$ "+detExp.getAmount());
        tvDate.setText(detExp.getDate());
        //Log.d("Demo", detExp.getId());
        expId=detExp.getId();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.deleteExpense:
                fbExpDet.child(expId).removeValue();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
