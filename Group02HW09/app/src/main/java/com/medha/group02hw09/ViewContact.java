package com.medha.group02hw09;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewContact.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ViewContact extends Fragment {

    TextView viewName, viewName2, viewEmail, viewNumber;

    ImageView viewProfile;

    private OnFragmentInteractionListener mListener;

    public ViewContact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_contact, container, false);
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewEmail = (TextView) getActivity().findViewById(R.id.viewEmail);

        viewName = (TextView) getActivity().findViewById(R.id.viewName);

        viewName2 = (TextView) getActivity().findViewById(R.id.viewName2);

        viewNumber = (TextView) getActivity().findViewById(R.id.viewNumber);

        viewProfile = (ImageView) getActivity().findViewById(R.id.viewPicture);

        User user = ContactsFragment.viewUser;

        viewEmail.setText("Email : " + user.getEmail());

        viewName2.setText("Username : " + user.getName());

        viewName.setText(user.getName());

        viewNumber.setText("Phone # : " + user.getPhone());

        Bitmap profileImage = ConversationListAdapter.base64ToBitmap(user.getPicture());

        viewProfile.setImageBitmap(profileImage);
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

    }
}
