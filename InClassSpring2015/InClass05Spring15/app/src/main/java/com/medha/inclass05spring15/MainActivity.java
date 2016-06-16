package com.medha.inclass05spring15;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    Switch parserSwitch;
    String searchText;
    String completeUrl;
    static ArrayList<String> imageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSearch = (EditText) findViewById(R.id.editText_search);
        if (editTextSearch.getText()!= null){
            searchText = editTextSearch.getText().toString();
        }
        parserSwitch= (Switch) findViewById(R.id.switch_parser);



        if(isboolean()){

            parserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    isChecked = false;
                    if(isChecked)
                        new FlickrXml().execute("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=808e97046a232c85deaf7aa1735a7026&text=UNCC&extras=url_m&per_page=20&format=rest&auth_token=72157662582385784-3885b88c2eba42f9&api_sig=d01db0baf9304db7f0ee9e122610198a");
                    else if(!isChecked)
                    new ConstructUrl().execute("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=808e97046a232c85deaf7aa1735a7026&text=UNCC&extras=url_m&per_page=20&format=rest&auth_token=72157662582385784-3885b88c2eba42f9&api_sig=d01db0baf9304db7f0ee9e122610198a");

                }
            });
        }
        else
            Toast.makeText(MainActivity.this,"Not Connected",Toast.LENGTH_LONG).show();

    }






    public boolean isboolean(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(MainActivity.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo()!= null && cm.getActiveNetworkInfo().isConnected())
        {return true;}
        else
        {return false;}
    }

    class FlickrXml extends AsyncTask<String,Void,ArrayList<String>>{

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            try {
                SSLContext context = SSLContext.getDefault();
                URL url = new URL(params[0]);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setSSLSocketFactory(context.getSocketFactory());
                con.connect();
                int statusCode = con.getResponseCode();
                if(statusCode == HttpsURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return ImageUtils.ImageParser.parser(in);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);
            imageUrls = new ArrayList<String>();
            imageUrls = strings;


        }
    }

}
