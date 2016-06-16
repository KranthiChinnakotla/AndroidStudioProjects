package com.medha.inclass11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    EditText editTextEmail, editeTextPassword;

    Button loginButtn, createAccount;
    static String email, password;
    Firebase fbInclass,fbUsername,fbExpense;

    private static final String EMAIL_KEY = "Email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        editeTextPassword = (EditText) findViewById(R.id.editTextPassword);

        loginButtn = (Button) findViewById(R.id.loginButton);

        createAccount = (Button) findViewById(R.id.createAccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SignUpActivity.class);

                startActivity(i);

            }
        });


        loginButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editTextEmail.getText().toString();

                password = editeTextPassword.getText().toString();


                if (email != null && password != null) {
                    fbInclass.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(MainActivity.this, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_LONG).show();
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("provider", authData.getProvider());
                            if (authData.getProviderData().containsKey("displayName")) {
                                map.put("displayName", authData.getProviderData().get("displayName").toString());
                            }

                            Map<String, Object> user = new HashMap<String, Object>();
                            user.put("email", email);
                            user.put("password", password);
                            user.put("name", SignUpActivity.name);


                            fbInclass.child("users").child(authData.getUid()).setValue(user);


                            Intent i = new Intent(MainActivity.this, ExpensesList.class);



                            startActivity(i);

                            Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();


                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            switch (firebaseError.getCode()) {

                                case FirebaseError.INVALID_EMAIL: {
                                    Toast.makeText(MainActivity.this, " Invalid email!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_PASSWORD: {
                                    Toast.makeText(MainActivity.this, " Invalid password!", Toast.LENGTH_LONG).show();
                                break;
                            }

                            default:
                                break;
                        }

                    }
                });}


            }
        });


        Firebase.setAndroidContext(this);
        fbInclass = new Firebase("https://inclass11-5180.firebaseio.com/");
        fbUsername = fbInclass.child("username");
        fbExpense = fbInclass.child("Expenses");






       /* fbInclass.authWithPassword("kranthi2@hotmail.com", "medha", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("provider", authData.getProvider());
                if(authData.getProviderData().containsKey("displayName")) {
                    map.put("displayName", authData.getProviderData().get("displayName").toString());
                }
                fbInclass.child("users").child(authData.getUid()).setValue(map);
            }

             @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

                Toast.makeText(MainActivity.this,"User is not signed up",Toast.LENGTH_LONG).show();


            }
        });
*/





}
}
