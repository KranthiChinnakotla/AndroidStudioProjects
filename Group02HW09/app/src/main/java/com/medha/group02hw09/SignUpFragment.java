package com.medha.group02hw09;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignUpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SignUpFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    Firebase fbHw09;
    EditText name,email,password,phone;
    String encodedImage;

    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            switch (requestCode) {
                case 1:
                    if (resultCode == getActivity().RESULT_OK) {
                        Uri selectedImage = data.getData();
                        InputStream inputStream = getActivity().getContentResolver().openInputStream(selectedImage);
                        Bitmap picture = BitmapFactory.decodeStream(inputStream);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte [] b = baos.toByteArray();
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);



                    } else
                        Toast.makeText(getActivity().getApplicationContext(), "Pick an Image from gallery", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
        catch (Exception e){
            Toast.makeText(getActivity().getApplicationContext(),"Something is wrong",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Firebase.setAndroidContext(getActivity());

        fbHw09 = new Firebase("https://group02hw09.firebaseio.com/");
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,1);


        name = (EditText)getActivity().findViewById(R.id.editText_userFirstlastname);

        email = (EditText)getActivity().findViewById(R.id.editText_emailSignup);
        phone = (EditText) getActivity().findViewById(R.id.editText_phoneNumber);

        password = (EditText)getActivity().findViewById(R.id.editText_password);


        getActivity().findViewById(R.id.button_Cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.returnTologin();

            }
        });


        getActivity().findViewById(R.id.button_signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fbHw09.createUser(email.getText().toString(), password.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {

                        User user = new User(email.getText().toString(), name.getText().toString(), password.getText().toString(),phone.getText().toString(),encodedImage);

                        Firebase userRef = fbHw09.child("users").child("" + result.get("uid"));
                        userRef.setValue(user);

                        mListener.returnTologin();

                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {

                    }
                });

            }
        });

    }

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
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener= (OnFragmentInteractionListener) activity;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
      void returnTologin();
    }
}
