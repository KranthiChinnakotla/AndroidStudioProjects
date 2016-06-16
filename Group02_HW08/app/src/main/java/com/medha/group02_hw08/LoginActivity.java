package com.medha.group02_hw08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText textLogin, textloginPwd;
    Button btnLogin, btnCreateUser;
    String name = null;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                name = data.getStringExtra("name");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textLogin = (EditText) findViewById(R.id.editText_loginEmail);
        textloginPwd = (EditText) findViewById(R.id.editText_loginPassword);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnCreateUser = (Button) findViewById(R.id.button_create);

        Firebase.setAndroidContext(this);
        final Firebase fbHw08 = new Firebase("https://group02hw08.firebaseio.com/");
        final Query query = fbHw08.orderByChild("email");



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = textLogin.getText().toString();
                final String password = textloginPwd.getText().toString();



                if (email != null && !email.equals("") && !password.equals("") && password != null) {
                    fbHw08.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(LoginActivity.this, "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_LONG).show();
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("provider", authData.getProvider());
                            if (authData.getProviderData().containsKey("displayName")) {
                                map.put("displayName", authData.getProviderData().get("displayName").toString());
                            }

                            Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginActivity.this,ConversationsActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            switch (firebaseError.getCode()) {

                                case FirebaseError.INVALID_EMAIL: {
                                    Toast.makeText(LoginActivity.this, " Invalid email!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_PASSWORD: {
                                    Toast.makeText(LoginActivity.this, " Invalid password!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.USER_DOES_NOT_EXIST:{
                                    Toast.makeText(LoginActivity.this,"User doesn't exist, please sign up",Toast.LENGTH_LONG).show();
                                }

                                default:
                                    break;
                            }

                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(),"Enter eMail and Password",Toast.LENGTH_LONG).show();

            }
        });


        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUp.class);
                startActivityForResult(i, 1);

            }
        });


    }
}
