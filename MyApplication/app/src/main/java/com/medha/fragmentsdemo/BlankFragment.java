package com.medha.fragmentsdemo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Demo","Fragment OnCreateView");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Demo", "Fragment onActivity ");


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("Demo", "Fragment onAttach ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Demo", "Fragment onCreate ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Demo", "Fragment onStart ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Demo", "Fragment onResume ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Demo", "Fragment onDestroy ");
    }
}
