package com.medha.inclass11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText signUpName, signUpEMail, signUpPassword;

    Button signUpButton, cancelButton;
    String email,password,message;
    static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpName = (EditText) findViewById(R.id.signUpName);

        signUpEMail = (EditText) findViewById(R.id.signUpEmail);

        signUpPassword = (EditText) findViewById(R.id.signUpPassword);

        signUpButton = (Button) findViewById(R.id.singUpButton);

        cancelButton = (Button) findViewById(R.id.signUpCancel);
        Firebase.setAndroidContext(this);
        final Firebase fbInclass = new Firebase("https://inclass11-5180.firebaseio.com/");


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = signUpName.getText().toString();

                email = signUpEMail.getText().toString();

                password = signUpPassword.getText().toString();

                message = "";

                if(email!=null && password != null) {
                    fbInclass.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            Toast.makeText(SignUpActivity.this, "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            switch (firebaseError.getCode()) {
                                case FirebaseError.EMAIL_TAKEN :{
                                Toast.makeText(SignUpActivity.this, " Email exist !", Toast.LENGTH_LONG).show();
                                break;
                                }
                                case FirebaseError.INVALID_EMAIL:{
                                    Toast.makeText(SignUpActivity.this, " Invalid email!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_PASSWORD:{
                                    Toast.makeText(SignUpActivity.this, " Invalid password!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                        }
                    });}


            }
        });



            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
            }
        });


    }
}



/*
                if(!checkUser(email)){

                    addUser(name, email, password);

                    finish();

                    message = "Account Created Successfully";
                }else{

                    message = "User exists";
                }

                Toast.makeText(getApplicationContext(),message , Toast.LENGTH_LONG).show();
            }


        });*/










    /*public static void addUser(String name, String email, String password){


    }*/


    /*public static Boolean checkUser(String email){

        return false;
    }
*/

