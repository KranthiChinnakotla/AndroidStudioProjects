package com.medha.group02hw09;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements LoginFragments.OnFragmentInteractionListener ,SignUpFragment.OnFragmentInteractionListener, AdapterView.OnItemClickListener,ContactsFragment.OnFragmentInteractionListener,MessageFragment.OnFragmentInteractionListener,ConversationsFragment.OnFragmentInteractionListener,
Logout.OnFragmentInteractionListener,Exit.OnFragmentInteractionListener,Settings.OnFragmentInteractionListener,Archive.OnFragmentInteractionListener,
ViewContact.OnFragmentInteractionListener{


    private ActionBarDrawerToggle drawerListener;
    private String [] planets;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    NavigatorAdapter navAdapter;
    String userName, userPicture;
    String [] navContents;
    Firebase fbHw09;

    Firebase myFirebaseRef;

    User user;

    ListView listView;

    private ArrayList<User> list;

    String uid;

    User primaryUser;

    String emailOfUser;

    ConversationListAdapter adapter;

    static String UID;

    ArrayList<String> namesList;






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerListener.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        Firebase.setAndroidContext(this);
        fbHw09 = new Firebase("https://group02hw09.firebaseio.com/");
        /*if(user != null){
        Firebase fbtemp = fbHw09.child("users");

            fbtemp.child(fbHw09.getAuth().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                userName=user.getName();
                navContents = new String[]{userName,"Contacts","Conversations","Archived","Settings","Logout","Exit"};
                navAdapter = new NavigatorAdapter(MainActivity.this,R.layout.navdraw_contents_adapter,navContents);
                mDrawerList.setAdapter(navAdapter);
                navAdapter.setNotifyOnChange(true);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        }*/


        mDrawerList.setOnItemClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        drawerListener = new ActionBarDrawerToggle(this,mDrawerLayout,R.drawable.ic_view_headline_black_24dp,R.string.Open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                Toast.makeText(MainActivity.this,"Drawer Opened",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(MainActivity.this,"Drawer Closed",Toast.LENGTH_LONG).show();
            }
        };
        if (mDrawerLayout != null) {
            mDrawerLayout.setDrawerListener(drawerListener);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.drawable.ic_view_headline_black_24dp);

        }






        getFragmentManager().beginTransaction().add(R.id.container,new LoginFragments(),"LoginFragment").commit();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerListener.syncState();
    }

    @Override
    public void goToSingUpActivity() {
        getFragmentManager().beginTransaction().replace(R.id.container,new SignUpFragment(),"SignupFragment").commit();
    }

    @Override
    public void goToConversationsActivity() {

        fbHw09 = new Firebase("https://group02hw09.firebaseio.com/");

        try {

            String uid = fbHw09.getAuth().getUid();
            if (uid != null) {
                Firebase fbtemp = fbHw09.child("users");

                fbtemp.child(fbHw09.getAuth().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                        userName = user.getName();
                        userPicture = user.getPicture();
                        navContents = new String[]{userPicture+","+userName, "Contacts", "Conversations", "Archived", "Settings", "Logout", "Exit"};
                        navAdapter = new NavigatorAdapter(MainActivity.this, R.layout.navdraw_contents_adapter, navContents);
                        mDrawerList.setAdapter(navAdapter);
                        navAdapter.setNotifyOnChange(true);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        }
        catch (NullPointerException e) {
               e.printStackTrace();
             }
   finally {

            Log.d("Demo","Login Success");

              }
            }
    @Override
    public void returnTologin() {
        getFragmentManager().beginTransaction().replace(R.id.container,new LoginFragments(),"LoginFragment").commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,navContents[position]+" was clicked",Toast.LENGTH_LONG).show();
        selectedItem(position);
    }

    public void selectedItem(int position) {
        mDrawerList.setItemChecked(position,true);

        if(navContents[position].equals("Contacts"))
            getFragmentManager().beginTransaction().replace(R.id.container,new ContactsFragment(),"ContactsFragment").commit();
        if(navContents[position].equals("Conversations"))
            getFragmentManager().beginTransaction().replace(R.id.container,new ConversationsFragment(),"ConversationsFragment").commit();

        if(navContents[position].equals("Logout"))
            getFragmentManager().beginTransaction().replace(R.id.container,new Logout(),"Logout").commit();
        if(navContents[position].equals("Exit"))
            getFragmentManager().beginTransaction().replace(R.id.container,new Exit(),"Exit").commit();
        if(navContents[position].equals("Archived"))
            getFragmentManager().beginTransaction().replace(R.id.container,new Archive(),"Archive").commit();
        if(navContents[position].equals("Settings"))
            getFragmentManager().beginTransaction().replace(R.id.container,new Settings(),"Settings").commit();

    }

    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void contacts() {


        getFragmentManager().beginTransaction().replace(R.id.container,new ViewContact(),"ViewContactFragment").commit();

    }

    @Override
    public void conversations() {
        getFragmentManager().beginTransaction().replace(R.id.container,new MessageFragment(),"MessageFragment").commit();

    }

    @Override
    public void refresh() {

        getFragmentManager().beginTransaction().replace(R.id.container,new MessageFragment(),"MessageFragment").commit();


    }

    @Override
    public void logOut() {
        fbHw09.unauth();
        getFragmentManager().beginTransaction().replace(R.id.container,new LoginFragments(),"LoginFragment").commit();

    }

    @Override
    public void exit() {
        finish();
    }

    @Override
    public void update() {
        getFragmentManager().beginTransaction().replace(R.id.container,new ConversationsFragment(),"ConversationsFragment").commit();
    }

    @Override
    public void cancel() {

        getFragmentManager().beginTransaction().replace(R.id.container,new ConversationsFragment(),"ConversationsFragment").commit();
    }

    @Override
    public void archived() {
        getFragmentManager().beginTransaction().replace(R.id.container,new ConversationsFragment(),"ConversationsFragment").commit();
    }
}
