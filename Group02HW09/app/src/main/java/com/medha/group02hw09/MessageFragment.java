package com.medha.group02hw09;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    Firebase myFirebaseRef;

    Chat chat;

    static ListView listView;

    static ArrayList<Chat> myChatList;

    static MessagesListAdapter adapter;

    EditText sendMessage;

    Button sendBtn;

    static User receivingUser;

    static User primaryUser;

    static ArrayList<String> messageIDList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
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
        void refresh();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        myChatList = new ArrayList<Chat>();

        messageIDList = new ArrayList<String>();

        chat = new Chat();

        listView = (ListView) getActivity().findViewById(R.id.listView2);

        adapter = new MessagesListAdapter(getActivity(), R.layout.chats, myChatList);

        adapter.setNotifyOnChange(true);

        listView.setAdapter(adapter);

        Firebase.setAndroidContext(getActivity());

        sendMessage = (EditText) getActivity().findViewById(R.id.sendMessage);

        sendBtn = (Button) getActivity().findViewById(R.id.sendBtn);

        receivingUser = ConversationsFragment.user;

        primaryUser = ConversationsFragment.primaryUser;

        myFirebaseRef = new Firebase("https://group02hw09.firebaseio.com/");

        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    if (((postSnapshot.getKey()).equals("messages"))) {

                        for (DataSnapshot postPostSnaps : postSnapshot.getChildren()) {

                            Chat chats = postPostSnaps.getValue(Chat.class);

                            if (!messageIDList.contains(chats.getMessageID())) {

                                if (((chats.getSender()).equals(primaryUser.getName())) && ((chats.getReceriver()).equals(receivingUser.getName()))) {

                                    // if (!messageIDList.contains(chats.getMessageID())) {

                                    myChatList.add(chats);

                                    messageIDList.add(chats.getMessageID());

                                    adapter.notifyDataSetChanged();

                                    //}

                                } else if (((chats.getSender()).equals(receivingUser.getName())) && ((chats.getReceriver()).equals(primaryUser.getName()))) {


                                    //if (!messageIDList.contains(chats.getMessageID())) {

                                    myChatList.add(chats);

                                    messageIDList.add(chats.getMessageID());

                                    adapter.notifyDataSetChanged();
                                    //}

                                }

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

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage(receivingUser, primaryUser);

            }
        });
    }

    private void sendMessage(User receivingUser, User primaryUser) {
        String input = sendMessage.getText().toString();
        //Map timestamp = ServerValue.TIMESTAMP;

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        String timeStamp = df.format(Calendar.getInstance().getTime());

        // Log.d("Date", date);

        Chat chat;
        Conversations conversations;

        if (!input.equals("")) {
            Boolean messageRead = false;

            Firebase postRef = myFirebaseRef.child("messages");

            Firebase newPostRef = postRef.push();
            Firebase convRef = myFirebaseRef.child("conversations");
            Firebase newConvRef = convRef.push();

            chat = new Chat(messageRead, input, receivingUser.getName(), primaryUser.getName(), timeStamp, newPostRef.getKey());

            conversations = new Conversations("None",primaryUser.getName(),receivingUser.getName(),"false","false",newConvRef.getKey());


            newPostRef.setValue(chat);
            newConvRef.setValue(conversations);

            sendMessage.setText("");

            mListener.refresh();

        }

        Toast.makeText(getActivity().getApplicationContext(), "Please enter Text", Toast.LENGTH_LONG).show();

    }



}
