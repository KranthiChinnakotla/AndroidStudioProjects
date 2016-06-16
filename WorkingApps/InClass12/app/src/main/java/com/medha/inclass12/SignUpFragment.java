package com.medha.inclass12;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    EditText etSignUpUserName,etSignUpEmail,etSignUpPassword,etConfirmPassword;
    Button btnSignUp,btnCancel;
    MainActivity activity;
    GotoLogin mListener;
    Firebase fbIn12;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Firebase.setAndroidContext(getActivity());
        fbIn12 = new Firebase("https://inclass12-5180.firebaseio.com/");
        etSignUpUserName= (EditText) getActivity().findViewById(R.id.editTextSignUpUserName);
        etSignUpEmail= (EditText) getActivity().findViewById(R.id.editTextSignUpEmail);
        etSignUpPassword= (EditText) getActivity().findViewById(R.id.editTextSignUpPassword);
        etConfirmPassword= (EditText) getActivity().findViewById(R.id.editTextConfirmPassword);
        btnSignUp= (Button) getActivity().findViewById(R.id.signUp);
        btnCancel= (Button) getActivity().findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToLogin();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSignUpUserName.getText().toString().isEmpty() || etSignUpPassword.getText().toString().isEmpty() || etSignUpEmail.getText().toString().isEmpty() ||
                        etConfirmPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "The fileds can't be empty", Toast.LENGTH_SHORT).show();
                }
                else{



                    fbIn12.createUser(etSignUpEmail.getText().toString(), etSignUpPassword.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                        @Override
                        public void onSuccess(Map<String, Object> result) {
                            Toast.makeText(getActivity(), "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();

                            Map<String, Object> user = new HashMap<String, Object>();
                            user.put("email", etSignUpEmail.getText().toString());
                            user.put("password", etSignUpPassword.getText().toString());
                            user.put("name", etSignUpUserName.getText().toString());
                            fbIn12.child("users").child(result.get("uid").toString()).setValue(user);
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            switch (firebaseError.getCode()) {
                                case FirebaseError.EMAIL_TAKEN :{
                                    Toast.makeText(getActivity(), " Email exist !", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_EMAIL:{
                                    Toast.makeText(getActivity(), " Invalid email!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_PASSWORD:{
                                    Toast.makeText(getActivity(), " Invalid password!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                            }
                        }
                    });
                    /*ParseUser user = new ParseUser();
                    user.setEmail(etSignUpEmail.getText().toString());
                    user.setUsername(etSignUpEmail.getText().toString());
                    user.setPassword(etSignUpPassword.getText().toString());
                    user.put("FirstName", etSignUpUserName.getText().toString());
                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(getActivity(), "Sign Up successful", Toast.LENGTH_SHORT).show();
                                activity.moveToMessageFragment();

                            } else {
                                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });*/

                }
            }
        });


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener= (GotoLogin) activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    public interface GotoLogin{
        public void moveToLogin();
    }


}
