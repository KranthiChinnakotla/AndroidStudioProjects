package com.medha.group02_hw08;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class ConversationsActivity extends AppCompatActivity {

    ArrayList<String> userIds;
    ArrayList<Users> userlist;
    ListView listView;
    ConversationsAdapter adapter;
    Firebase fbHw08;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        userIds = new ArrayList<String>();
        userlist = new ArrayList<Users>();

        Firebase.setAndroidContext(this);
         fbHw08 = new Firebase("https://group02hw08.firebaseio.com/users");

        Query query = fbHw08.orderByKey();
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (!dataSnapshot.getKey().equals(fbHw08.getAuth().getUid())) {
                    userIds.add(dataSnapshot.getKey().toString());
                    fbHw08.child(dataSnapshot.getKey().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Users users = dataSnapshot.getValue(Users.class);
                            userlist.add(users);
                            adapter = new ConversationsAdapter(ConversationsActivity.this, R.layout.conversations_listview_adapter, userlist);
                            listView.setAdapter(adapter);
                            adapter.setNotifyOnChange(true);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                                    ImageView iv = (ImageView) view.findViewById(R.id.imageView_call);
                                    TextView tv = (TextView) view.findViewById(R.id.textView_listname);

                                    iv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            String uri = "tel:" + userlist.get(position).getPhone_number().trim();



                                            if (ActivityCompat.checkSelfPermission(ConversationsActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                                                //ActivityCompat.requestPermissions(ConversationsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                                                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse(uri));
                                                startActivity(intent);
                                            }

                                        }
                                    });
                                    tv.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Intent intent = new Intent(ConversationsActivity.this,ViewMessages.class);
                                            intent.putExtra("name",userlist.get(position).getFull_name().trim());
                                            startActivity(intent);
                                        }
                                    });


                                }
                            });
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });


                }
                listView = (ListView) findViewById(R.id.listView);



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






/*

            for(String s: userIds){
                fbHw08.child(s).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Users users = dataSnapshot.getValue(Users.class);
                        userlist.add(users);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
*/



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_changes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit_profile:
                 Intent intent = new Intent(ConversationsActivity.this,EditProfile.class);
                 /*Bundle bundle = new Bundle();
                 for(Users u: userlist){
                     bundle.putSerializable();
                 }*/
                 //intent.putExtra("users",userlist);
                 startActivity(intent);
                 break;
            case R.id.logout:
                fbHw08.unauth();



        }

        return super.onOptionsItemSelected(item);

    }


}
