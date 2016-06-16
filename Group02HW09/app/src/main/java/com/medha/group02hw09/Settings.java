package com.medha.group02hw09;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Settings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Settings extends Fragment {

    EditText editName, editPassword, editNumber, editEmail;

    ImageView editProfilePic;

    TextView editFUllName;

    Firebase myFirebaseRef;

    Button updateBtn, editCancelBtn;

    String enCodedImage;
    User mainUser;


    private OnFragmentInteractionListener mListener;

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener= (OnFragmentInteractionListener) activity;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            switch (requestCode) {
                case 1:
                    if (resultCode == getActivity().RESULT_OK) {
                        Uri selectedImage = data.getData();
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(selectedImage);
                        Bitmap picture = BitmapFactory.decodeStream(inputStream);
                        editProfilePic.setImageBitmap(picture);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] b = baos.toByteArray();
                        enCodedImage = Base64.encodeToString(b, Base64.DEFAULT);

                    } else
                        Toast.makeText(getActivity().getApplicationContext(), "Pick an Image from gallery", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editProfilePic = (ImageView) getActivity().findViewById(R.id.editProfilePicture);
        editFUllName = (TextView) getActivity().findViewById(R.id.editTextView);
        updateBtn = (Button) getActivity().findViewById(R.id.editUpdateBtn);
        editCancelBtn = (Button) getActivity().findViewById(R.id.editCancelBtn);
        editName = (EditText) getActivity().findViewById(R.id.editName);
        editPassword = (EditText) getActivity().findViewById(R.id.editPassword);
        editEmail = (EditText) getActivity().findViewById(R.id.editEmail);
        editNumber = (EditText) getActivity().findViewById(R.id.editNumber);

        Firebase.setAndroidContext(getActivity());

        myFirebaseRef = new Firebase("https://group02hw09.firebaseio.com/");

        myFirebaseRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (snapshot.getKey().equals(myFirebaseRef.getAuth().getUid())) {
                        mainUser = snapshot.getValue(User.class);
                    }
                }

                editName.setText(mainUser.getName());
                editPassword.setText(mainUser.getPassword());
                editEmail.setText(mainUser.getEmail());
                editNumber.setText(mainUser.getPhone());
                editProfilePic.setImageBitmap(ConversationListAdapter.base64ToBitmap(mainUser.getPicture()));
                enCodedImage = mainUser.getPicture();
                editFUllName.setText(mainUser.getName());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        editProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = editEmail.getText().toString();

                final String name = editName.getText().toString();

                final String password = editPassword.getText().toString();

                final String number = editNumber.getText().toString();

                User user = new User(email, name, password, number, enCodedImage);

                Firebase userRef = myFirebaseRef.child("users").child(myFirebaseRef.getAuth().getUid());

                userRef.setValue(user);


                myFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        User user = new User(email, name, password, number, enCodedImage);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                    }
                });


                Toast.makeText(getActivity().getApplicationContext(), "Saved Successfully !", Toast.LENGTH_LONG).show();

                mListener.update();


            }
        });

        editCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void update();

        void cancel();
    }
}
