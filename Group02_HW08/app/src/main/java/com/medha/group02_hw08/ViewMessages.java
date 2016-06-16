package com.medha.group02_hw08;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewMessages extends AppCompatActivity {

    EditText editmessage;
    TextView textView;
    String message;
    Firebase fbHw08,fbMsg;
    String sendername, pushId,receivername;
    Button btnSend;
    Timestamp timestamp;
    Firebase fbHw08_push,new_push;
    ArrayList<Messages> messages;
    ArrayList<Messages> sendmessages;
    ListView listView;
    MessageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        fbHw08 = new Firebase("https://group02hw08.firebaseio.com/users");
        fbMsg = new Firebase("https://group02hw08.firebaseio.com");
        setContentView(R.layout.activity_view_messages);
        editmessage = (EditText) findViewById(R.id.editText_message);
        btnSend = (Button) findViewById(R.id.button_send);

        listView = (ListView) findViewById(R.id.listView_message);

        String userId = fbHw08.getAuth().getUid().trim();
        Firebase fbHw08_currentuser = fbHw08.child(userId);
        receivername = getIntent().getStringExtra("name");

        fbHw08_currentuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                sendername = users.getFull_name();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        sendername = fbHw08.getAuth().getUid();
        timestamp = new Timestamp(System.currentTimeMillis());


        fbHw08_push = fbMsg.child("messages");
        new_push = fbHw08_push.push();



        btnSend.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {

                message = editmessage.getText().toString();
                Map<String, String> msg1 = new HashMap<String, String>();
                msg1.put("time_stamp", timestamp.toString());
                msg1.put("message_read", "false");
                msg1.put("message_text", message);
                msg1.put("receiver", receivername);
                msg1.put("sender", sendername);
                new_push.setValue(msg1);
                pushId = new_push.getKey();
                /*if( message != null){
                    recreate();
                }*/
                if (message != null) {



                    Query query = fbHw08_push.orderByKey();
                    query.addChildEventListener(new ChildEventListener() {
                        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            fbHw08_push.child(dataSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    sendmessages = messages;
                                    Messages msgs = dataSnapshot.getValue(Messages.class);
                                    if (msgs.getSender().equals(sendername) && msgs.getReceiver().equals(receivername)) {
                                        sendmessages.add(msgs);
                                    }



                                    adapter = new MessageAdapter(ViewMessages.this, R.layout.message_adapter, sendmessages);
                                    listView.setAdapter(adapter);
                                    adapter.setNotifyOnChange(true);


                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });

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


                //push_id =fbHw08.child("messages").child(pushId);*/


               /* messages = new ArrayList<Messages>();


                        fbHw08_push.child(pushId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Messages msgs = dataSnapshot.getValue(Messages.class);
                                messages.add(msgs);
                                MessageAdapter adapter = new MessageAdapter(ViewMessages.this, R.layout.message_adapter, messages);
                                listView.setAdapter(adapter);
                                adapter.setNotifyOnChange(true);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });*/


            }


        });

            messages = new ArrayList<Messages>();
            Query query = fbHw08_push.orderByKey();
            query.addChildEventListener(new ChildEventListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    fbHw08_push.child(dataSnapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            Messages msgs = dataSnapshot.getValue(Messages.class);
                            if (msgs.getSender().equals(sendername)) {
                                messages.add(msgs);
                            }


                            adapter = new MessageAdapter(ViewMessages.this, R.layout.message_adapter, messages);
                            listView.setAdapter(adapter);
                            adapter.setNotifyOnChange(true);


                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

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

