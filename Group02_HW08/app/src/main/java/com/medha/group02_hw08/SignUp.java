package com.medha.group02_hw08;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    Button btnSignup,btnCancel;
    EditText textUserName,textPhone,textPassword,textConfirmpwd,textEmail;
    Map<String, Object> user;
    String name = null;
    String phone,encodedImage;
    //final String CAMERA_PIC_DIR = "/DCIM/Camera/";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case 1:
                    if (resultCode == RESULT_OK) {
                        Uri selectedImage = data.getData();
                        InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap picture = BitmapFactory.decodeStream(inputStream);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte [] b = baos.toByteArray();
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);



                    } else
                        Toast.makeText(getApplicationContext(), "Pick an Image from gallery", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignup = (Button) findViewById(R.id.button_signUp);
        btnCancel = (Button) findViewById(R.id.button_Cancel);
        textConfirmpwd = (EditText) findViewById(R.id.editText_ConfirmPassword);
        textEmail = (EditText) findViewById(R.id.editText_emailSignup);
        textPassword = (EditText) findViewById(R.id.editText_password);
        textPhone = (EditText) findViewById(R.id.editText_phoneNumber);
        textUserName = (EditText) findViewById(R.id.editText_userFirstlastname);


        Firebase.setAndroidContext(this);
        final Firebase fbHw08 = new Firebase("https://group02hw08.firebaseio.com");
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,1);




        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textEmail.getText() != null && textPassword.getText() != null && textPhone.getText() != null && textUserName.getText() != null) {
                    final String password = textPassword.getText().toString();
                    String confirm = textConfirmpwd.getText().toString();
                    final String email = textEmail.getText().toString();
                    phone = textPhone.getText().toString();

                    user = new HashMap<String, Object>();


                    if (confirm.equals(password)) {

                    } else
                        Toast.makeText(getApplicationContext(), "Password and Confirm password not matching, please enter again", Toast.LENGTH_LONG).show();
                    fbHw08.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            Toast.makeText(SignUp.this, "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();
                            name = textUserName.getText().toString();




                            user.put("email",email);
                            user.put("full_name", name);
                            user.put("password", password);
                            user.put("phone_number",phone);
                            user.put("picture",encodedImage);
                            fbHw08.child("users").child(result.get("uid").toString()).setValue(user);



                            Intent intent = new Intent();
                            intent.putExtra("name",name);
                            setResult(RESULT_OK,intent);
                            finish();

                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            switch (firebaseError.getCode()) {
                                case FirebaseError.EMAIL_TAKEN: {
                                    Toast.makeText(SignUp.this, " Email exist !", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_EMAIL: {
                                    Toast.makeText(SignUp.this, " Invalid email!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_PASSWORD: {
                                    Toast.makeText(SignUp.this, " Invalid password!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                        }
                    });
                }
                else
                 Toast.makeText(getApplicationContext(),"Enter all the required fields",Toast.LENGTH_LONG).show();


            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
