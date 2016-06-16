package com.medha.group02hw09;

import android.app.Activity;
import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LoginFragments extends Fragment {

    OnFragmentInteractionListener mListener ;
    Firebase fbHw09;
    EditText etEmail,etPassword;
    Button btnLogin,btnCreateNewAcccount;


    public LoginFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_fragments, container, false);

    }


    @Override
    @SuppressWarnings("deprecation")
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnFragmentInteractionListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Firebase.setAndroidContext(getActivity());
        etEmail = (EditText) getActivity().findViewById(R.id.editTextEmail);
        etPassword = (EditText) getActivity().findViewById(R.id.editTextPassword);
        btnLogin = (Button) getActivity().findViewById(R.id.loginButton);
        btnCreateNewAcccount = (Button) getActivity().findViewById(R.id.createAccount);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                if (etEmail.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Email and Password fields can't be empty", Toast.LENGTH_SHORT).show();

                } else {
                    fbHw09 = new Firebase("https://group02hw09.firebaseio.com/");

                    fbHw09.authWithPassword(etEmail.getText().toString(), etPassword.getText().toString(), new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            Toast.makeText(getActivity(), "User ID: " + authData.getUid() + ", Provider: " + authData.getProvider(), Toast.LENGTH_LONG).show();
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("provider", authData.getProvider());
                            if (authData.getProviderData().containsKey("displayName")) {
                                map.put("displayName", authData.getProviderData().get("displayName").toString());
                            }
                            mListener.goToConversationsActivity();

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
                                case FirebaseError.USER_DOES_NOT_EXIST:{
                                    Toast.makeText(getActivity(),"User doesn't exist, please sign up",Toast.LENGTH_LONG).show();
                                }

                                default:
                                    break;
                            }

                        }
                    });
                }


            }
        });
        btnCreateNewAcccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToSingUpActivity();

            }
        });
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
        void goToSingUpActivity();
        void goToConversationsActivity();
    }
}
