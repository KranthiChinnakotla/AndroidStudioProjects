package com.medha.inclass14;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText signName, signEmail, signPhone, signPassword, signConPassword;

    Button signUp, signUpCancel;

    Firebase myFirebaseRef;
    //String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Firebase.setAndroidContext(this);

        myFirebaseRef = new Firebase("https://inclass14-5180.firebaseio.com/");

        /*Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,1);*/
        signName = (EditText) findViewById(R.id.signName);

        signEmail = (EditText) findViewById(R.id.signEmail);

        signPhone = (EditText) findViewById(R.id.signNumber);

        signPassword = (EditText) findViewById(R.id.signPassword);

        signConPassword = (EditText) findViewById(R.id.conPassword);

        signUp = (Button) findViewById(R.id.signUpBtn);

        signUpCancel = (Button) findViewById(R.id.signCancel);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = signName.getText().toString();

                final String email = signEmail.getText().toString();

                final String password = signPassword.getText().toString();

                String conPassword = signConPassword.getText().toString();

                final String number = signPhone.getText().toString();

                String message = null;

                if(name.equals("") || email.equals("") || password.equals("") || conPassword.equals("") || number.equals("")){

                    message = "Please enter all the values";

                }else {

                /*

                myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
                    }
                    @Override public void onCancelled(FirebaseError error) { }
                }); */

                    if (password.equals(conPassword)) {

                        myFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {
                                //System.out.println("Successfully created user account with uid: " + result.get("uid"));

                                //Log.d("Demo", result.get("uid").toString());

                               /* Bitmap bitmapImage = BitmapFactory.decodeResource(getResources(), R.drawable.placeholder);

                                String picture = bitmapToBase64(bitmapImage);*/

                                User user = new User(email, name, password, number );

                                Firebase userRef = myFirebaseRef.child("users").child("" + result.get("uid"));
                                //User alan = new User("Alan Turing", 1912);
                                userRef.setValue(user);

                                finish();

                            }

                            @Override
                            public void onError(FirebaseError firebaseError) {
                                // there was an error
                            }
                        });

                    } else {

                        message = "Passwords do not match";

                    }

                }

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }


        });

        signUpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                // myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");
            }
        });
    }
}
