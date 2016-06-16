package com.medha.inclass12;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
import android.app.Activity;
import android.content.Context;
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

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends android.app.Fragment {
    EditText etEmail,etPassword;
    Button btnLogin,btnCreateNewAcccount;
    fragmentNavigation mListener;
    Firebase fbIn12;

    // private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fbIn12.setAndroidContext(getActivity());
        etEmail = (EditText) getActivity().findViewById(R.id.editTextLoginEmail);
        etPassword = (EditText) getActivity().findViewById(R.id.editTextPassword);
        btnLogin = (Button) getActivity().findViewById(R.id.loginButton);
        btnCreateNewAcccount = (Button) getActivity().findViewById(R.id.buttonCreateNewAccount);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                if(etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Email and Password fields can't be empty",Toast.LENGTH_SHORT).show();

                }
                else{
                    fbIn12 = new Firebase("https://inclass12-5180.firebaseio.com/");

                    fbIn12.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(getActivity(), "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_LONG).show();
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("provider", authData.getProvider());
                            if (authData.getProviderData().containsKey("displayName")) {
                                map.put("displayName", authData.getProviderData().get("displayName").toString());
                            }
                            mListener.moveToMessageFragment();

                        }

                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {

                            switch (firebaseError.getCode()) {

                                case FirebaseError.INVALID_EMAIL: {
                                    Toast.makeText(getActivity(), " Invalid email!", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                case FirebaseError.INVALID_PASSWORD: {
                                    Toast.makeText(getActivity(), " Invalid password!", Toast.LENGTH_LONG).show();
                                    break;
                                }

                                default:
                                    break;
                            }

                        }
                    });}







            }
        });
        btnCreateNewAcccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.moveToSignUpFragment();
            }
        });




    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener= (fragmentNavigation) activity;

        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString() + "Should implement fragmentNavigation");

        }
    }

    public interface fragmentNavigation{
        public void moveToSignUpFragment();
        // public void moveToDoFragment();
        public void moveToMessageFragment();
    }
}