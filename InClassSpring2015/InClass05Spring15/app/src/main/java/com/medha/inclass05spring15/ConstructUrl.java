package com.medha.inclass05spring15;


import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/**
 * Created by Prathyusha on 2/21/16.
 */
public class ConstructUrl extends AsyncTask<String,Void,ArrayList<String>> {


    @Override
    protected ArrayList<String> doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            SSLContext context = SSLContext.getDefault();
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setSSLSocketFactory(context.getSocketFactory());
            con.connect();
            int status_code = con.getResponseCode();
            if(status_code == HttpsURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return ImageUtilsPull.PhotoPullParser.parsePhotos(in);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        MainActivity.imageUrls = new ArrayList<String>();
        MainActivity.imageUrls = strings;

    }
}
