package com.medha.group02hw09;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactsFragment extends Fragment {

    Firebase myFirebaseRef;

    static User user,viewUser;

    ListView listView;

    private ArrayList<User> list;

    String uid;

    static User primaryUser;

    String emailOfUser;

    ConversationListAdapter adapter;

    static String UID;

    ArrayList<String> namesList;

    private OnFragmentInteractionListener mListener;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (OnFragmentInteractionListener) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onCreate(savedInstanceState);



        list = new ArrayList<User>();

        namesList = new ArrayList<String>();

        user = new User();

        listView = (ListView) getActivity().findViewById(R.id.listView_contacts);

        adapter = new ConversationListAdapter(getActivity().getApplicationContext(), R.layout.conversation_list_view, list);

        listView.setAdapter(adapter);

        Firebase.setAndroidContext(getActivity());

        myFirebaseRef = new Firebase("https://group02hw09.firebaseio.com/");

        UID = myFirebaseRef.getAuth().getUid();

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (!((postSnapshot.getKey()).equals("messages"))&& !((postSnapshot.getKey()).equals("conversations"))) {

                        for (DataSnapshot postPostSnaps : postSnapshot.getChildren()) {

                            User users = postPostSnaps.getValue(User.class);

                            if (!((postPostSnaps.getKey()).equals(UID))) {

                                if (!namesList.contains(users.getName())) {

                                    list.add(users);

                                    namesList.add(users.getName());

                                    adapter.notifyDataSetChanged();
                                }


                            } else {

                                primaryUser = users;

                            }


                        }

                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                viewUser = list.get(position);
                mListener.contacts();
                return false;
            }
        });



       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                user = list.get(position);

                if(view.findViewById( R.id.userName) != null){
                    mListener.contacts();
                }


                *//*Intent i = new Intent(Conversations.this, Messages.class);

                i.putExtra("currentUser", user);

                i.putExtra("primaryUser", primaryUser);

                startActivity(i);*//*

            }
        });*/

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        return true;
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
        void contacts();

    }
}
