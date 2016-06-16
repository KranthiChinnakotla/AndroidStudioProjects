package com.medha.group02_hw08;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {
    ImageView updatePic;
    TextView profile_name;
    EditText update_name,update_email,update_phone,update_password;
    Button btnUpdate,btnCancel;
    String encodedImage;
   // ArrayList<Users> usersArrayList;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            try {
                switch (requestCode) {
                    case 1:
                        if (resultCode == RESULT_OK) {
                            Uri selectedImage = data.getData();
                            InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                            Bitmap picture = BitmapFactory.decodeStream(inputStream);
                            updatePic.setImageBitmap(picture);
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
        setContentView(R.layout.activity_edit_profile);

      //  usersArrayList = (ArrayList<Users>) getIntent().getExtras().getSerializable("users");


        Firebase.setAndroidContext(this);
        Firebase fbHw08 = new Firebase("https://group02hw08.firebaseio.com/users/");
        String userId = fbHw08.getAuth().getUid().trim();
        final Firebase fbHw08_currentuser = fbHw08.child(userId);
        updatePic = (ImageView) findViewById(R.id.imageView_profile);
        profile_name = (TextView) findViewById(R.id.textView_profilename);
        update_email = (EditText) findViewById(R.id.editText_profilemail);
        update_name = (EditText) findViewById(R.id.editText_profilename);
        update_phone= (EditText) findViewById(R.id.editText_profilephone);
        update_password = (EditText) findViewById(R.id.editText_profilepassword);
        btnUpdate = (Button) findViewById(R.id.button_update);
        btnCancel= (Button) findViewById(R.id.button_profilecancel);

        fbHw08_currentuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users current_user = dataSnapshot.getValue(Users.class);

                String encodedImage = current_user.getPicture();
                byte[] decodeString = Base64.decode(encodedImage, Base64.DEFAULT);
                Bitmap userImage = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                if(userImage != null)
                    updatePic.setImageBitmap(userImage);
                profile_name.setText(current_user.getFull_name());
                update_email.setText(current_user.getEmail());
                update_phone.setText(current_user.getPhone_number());
                update_password.setText(current_user.getPassword());
                update_name.setText(current_user.getFull_name());


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        updatePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eMail = update_email.getText().toString();
                String newPhone = update_phone.getText().toString();
                String newPassword = update_password.getText().toString();
                String newName = update_name.getText().toString();
                Map<String,Object> update = new HashMap<String, Object>();
                update.put("email",eMail);
                update.put("full_name",newName);
                update.put("phone_number",newPhone);
                update.put("password",newPassword);
                if(encodedImage!=null)
                update.put("picture",encodedImage);
                fbHw08_currentuser.updateChildren(update);
                Toast.makeText(getApplicationContext(),"Saved Succesfully !",Toast.LENGTH_SHORT).show();



            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfile.this,ConversationsActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        profile_name =


       /* Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1);*/
    }
}
