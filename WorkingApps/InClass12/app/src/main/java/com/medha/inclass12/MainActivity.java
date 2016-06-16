package com.medha.inclass12;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoginFragment.fragmentNavigation{
    Map<String, Object> currentUser;
    Firebase fbIn12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction()
                .add(R.id.container, new LoginFragment(), "tag_loginfragment")
                .commit();

        //moveToLogin();


    }
    /*public void moveToComposeFragment(){
        setTitle("Compose Message" );
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new ComposeFragment(), "tag_composefragment")
                .addToBackStack(null)
                .commit();
    }*/

    /*public void moveToMessageFragment(){
        setTitle("Message Me");
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new MessagesFragment(), "tag_messagefragment")
                .commit();

    }*/

    public void moveToLogin() {
        setTitle("Message Me (Login)");
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new LoginFragment(), "tag_loginfragment")
                .commit();
    }

    @Override
    public void moveToSignUpFragment() {
        setTitle("Message Me (Sign Up)");
        getFragmentManager().beginTransaction()
                .replace(R.id.container,new SignUpFragment(),"tag_signupfragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void moveToMessageFragment() {

    }






    //@Override
    public void displayUpdatedContent() {
        recreate();
    }

    //@Override
    /*public void goToMessageFrament() {
        setTitle("Message Me");
        getFragmentManager().beginTransaction()
                .replace(R.id.container, new MessagesFragment(), "tag_messagefragment")
                .commit();
    }*/

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }

    }
}
