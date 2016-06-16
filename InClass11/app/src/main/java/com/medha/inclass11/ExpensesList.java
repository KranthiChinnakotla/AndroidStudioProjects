package com.medha.inclass11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class ExpensesList extends AppCompatActivity {

    static ArrayList<Expense> expenseList ;

    Expense posts;

     ListView listView;

    ExpenseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

       listView = (ListView) findViewById(R.id.listView);
        Firebase.setAndroidContext(this);
        Firebase fbExpen = new Firebase("https://inclass11-5180.firebaseio.com/Expenses");
        expenseList=new ArrayList<Expense>();
         adapter = new ExpenseListAdapter(this, R.layout.listview, expenseList);

        adapter.setNotifyOnChange(true);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posts = expenseList.get(position);
                Intent intent = new Intent(ExpensesList.this,ExpenseDetails.class);
                intent.putExtra("racha",posts);
                startActivity(intent);

            }
        });
        fbExpen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    posts = postSnapshot.getValue(Expense.class);
                    if (posts.getUser().equals(MainActivity.email)) {
                        expenseList.add(posts);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed " + firebaseError.getMessage());

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_values, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuExpense:
                Intent i = new Intent(ExpensesList.this, AddExpense.class);
                //startActivityForResult(i,1);
                startActivity(i);
                return true;
            case R.id.menuLogOut:
                //clearAll();
                Firebase fbLogout = new Firebase("https://inclass11-5180.firebaseio.com");
                fbLogout.unauth();
                Intent i1 = new Intent(ExpensesList.this,MainActivity.class);
                startActivity(i1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void loadList(ArrayList<Expense> expenseList ){
        adapter = new ExpenseListAdapter(this, R.layout.listview, expenseList);
        adapter.setNotifyOnChange(true);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
                //Expense expense= (Expense) getIntent().getExtras().getSerializable("expense");
                //Log.d("Demo","thjkkk");
                //expenseList.add(expense);
                adapter = new ExpenseListAdapter(this, R.layout.listview, expenseList);
                adapter.setNotifyOnChange(true);
                listView.setAdapter(adapter);

            }
        }
    }
}
