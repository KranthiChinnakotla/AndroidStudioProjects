package com.medha.inclass14;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.batch.android.Batch;
import com.batch.android.BatchUserProfile;
import com.batch.android.Config;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class MainActivity extends AppCompatActivity {

    EditText loginEmail, loginPassword;

    Button loginBtn, loginCreateNew;

    Firebase fbIn14;

    static String emailOfUser;


    @Override
    protected void onStart()
    {
        super.onStart();
        Batch.onStart(this);
    }

    @Override
    protected void onStop()
    {
        Batch.onStop(this);

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        Batch.onDestroy(this);

        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        Batch.onNewIntent(this, intent);

        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        fbIn14 = new Firebase("https://inclass14-5180.firebaseio.com/");

        Batch.Push.setGCMSenderId("1062154600689");

        // TODO : switch to live Batch Api Key before shipping
        Batch.setConfig(new Config("DEV5726720D4F83DD3EB3F4DEB1180"));
        //Batch.Push.displayNotification();
        loginEmail = (EditText) findViewById(R.id.loginEmail);

        loginPassword = (EditText) findViewById(R.id.loginPassword);

        loginBtn = (Button) findViewById(R.id.LoginBtn);

        loginCreateNew = (Button) findViewById(R.id.LoginNewAcc);

        fbIn14.addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData != null) {
                    //Intent i = new Intent(MainActivity.this, Conversations.class);

                    emailOfUser = (String) authData.getProviderData().get("email");

                    //i.putExtra("uid", authData.getUid());

                    //startActivity(i);
                } else {
                    loginCreateNew.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, SignUp.class);


                            startActivity(i);
                        }
                    });

                    loginBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            emailOfUser = loginEmail.getText().toString();

                            String password = loginPassword.getText().toString();

                            if (emailOfUser.equals("") || password.equals("")) {

                                Toast.makeText(getApplicationContext(), "Please enter all the Values", Toast.LENGTH_LONG).show();
                            } else {

                                fbIn14.authWithPassword(emailOfUser, password, new Firebase.AuthResultHandler() {
                                    @Override
                                    public void onAuthenticated(AuthData authData) {

                                        Log.d("Demo", authData.getProviderData().toString());

                                        Log.d("Demo", authData.getAuth().toString());

                                        //Log.d("Demo", authData.getProviderData().toString());

                                        //Intent i = new Intent(MainActivity.this, Conversations.class);

                                      //  i.putExtra("uid",authData.getUid());

                                        BatchUserProfile batchUser = Batch.getUserProfile();
                                        if (batchUser != null) {
                                            batchUser.setCustomID(fbIn14.getAuth().getUid());
                                        } else {
                                            Toast.makeText(getApplicationContext(),"Error while getting BatchUserProfile, custom ID will not work.",Toast.LENGTH_LONG).show();
                                        }
                                        Toast.makeText(getApplicationContext(), "You are Logged in", Toast.LENGTH_LONG).show();


                                        // startActivity(i);

                                    }

                                    @Override
                                    public void onAuthenticationError(FirebaseError firebaseError) {

                                        switch (firebaseError.getCode()) {
                                            case FirebaseError.USER_DOES_NOT_EXIST:

                                                Toast.makeText(getApplicationContext(), "User does not Exist", Toast.LENGTH_LONG).show();
                                                break;
                                            case FirebaseError.INVALID_PASSWORD:
                                                Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                                                break;
                                            default:
                                                // handle other errors
                                                break;
                                        }

                                    }
                                });
                            }


                        }

                    });

                }
            }
        });

    }
}
