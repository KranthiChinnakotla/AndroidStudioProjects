package com.medha.group02hw09;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.firebase.client.annotations.Nullable;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConversationsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ConversationsFragment extends Fragment {

    Firebase myFirebaseRef;
    Firebase ref = new Firebase("https://group02hw09.firebaseio.com/");

    static User user;

    ListView listView;

    private ArrayList<User> list;

    String uid;

    static User primaryUser;

    String emailOfUser;

    ConversationListAdapter adapter;

    static String UID;

    ArrayList<String> namesList;
    private OnFragmentInteractionListener mListener;



    public ConversationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conversations, container, false);
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
        mListener = (OnFragmentInteractionListener) activity;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        list = new ArrayList<User>();

        namesList = new ArrayList<String>();

        user = new User();

        listView = (ListView) getActivity().findViewById(R.id.listView_contacts1);

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

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if(snapshot.getKey().equals("messages")){
                        Firebase fbHw09_bubble = myFirebaseRef.child("messages");
                        for(final User u: list){
                            if(!u.getName().equals(primaryUser.getName())){

                            Query qBubble = fbHw09_bubble.orderByChild("sender").equalTo(u.getName());
                                qBubble.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                        u.setMsgread(false);
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.findViewById(R.id.redBubble).setVisibility(View.INVISIBLE);

                user = list.get(position);
                final Firebase fbHw09_bubble = myFirebaseRef.child("messages");
                fbHw09_bubble.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                                Chat chat = snapshot.getValue(Chat.class);
                                if(chat.getSender().equals(user.getName())){
                                    fbHw09_bubble.child(chat.getMessageID()).child("messageRead").setValue(true);
                                }
                        }

                        try{mListener.conversations();}
                        catch (NullPointerException e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });



            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setPositiveButton("Archive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        final Firebase fb = new Firebase("https://group02hw09.firebaseio.com/conversations");
                        fb.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    Conversations conv = snapshot.getValue(Conversations.class);
                                    if(conv.getParticipant1().equals(list.get(position).getName())){
                                        if(!conv.getIsArchived_by_participant2().equals("true"))
                                        fb.child(conv.getConversationId()).child("isArchived_by_participant2").setValue("true");
                                    }else if(!conv.getIsArchived_by_participant1().equals("true"))
                                        fb.child(conv.getConversationId()).child("isArchived_by_participant1").setValue("true");
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });

                    }
                })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                final Firebase usersRef = new Firebase("https://group02hw09.firebaseio.com/conversations");
                                usersRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                            Conversations conv = snapshot.getValue(Conversations.class);
                                            if(conv.getDeletedBy().equals("None")){
                                               usersRef.child(conv.getConversationId()).child("deletedBy").setValue(primaryUser.getName());
                                                list.remove(position);
                                            }
                                            else if(conv.getDeletedBy().equals(primaryUser.getName())){
                                                list.remove(position);
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });




                               /* Firebase alanRef = usersRef.child(list.get(position).get);

                                MessageFragment.messageIDList.remove(mData.get(position).getMessageID());

                                MessageFragment.myChatList.remove(mData.get(position));
*/
//                                alanRef.setValue(null);

                                adapter.notifyDataSetChanged();

                            }
                        });

                Dialog alert = builder.create();
                alert.show();


                return false;
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name

        void conversations();

    }



}
