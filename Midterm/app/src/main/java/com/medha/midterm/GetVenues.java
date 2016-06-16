package com.medha.midterm;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.SSLContext;

/**
 * Created by Prathyusha on 3/21/16.
 */
public class GetVenues extends AsyncTask<String,Void,ArrayList<Venue>> {


    DisplayVenues displayVenues;

    public GetVenues( DisplayVenues displayVenues) {
        this.displayVenues = displayVenues;
    }
    @Override
    protected ArrayList<Venue> doInBackground(String... params) {

        BufferedReader reader;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while (line != null) {
                line = reader.readLine();
                sb.append(line);
            }


            return ParsingVenues.venueParser.parseNews(sb.toString());

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Venue> venueList) {
        super.onPostExecute(venueList);
        VenueActivity.progressDialog.dismiss();
        displayVenues.showVenue(venueList);
    }

    public  interface DisplayVenues{
        void showVenue(ArrayList<Venue> venueList);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        VenueActivity.progressDialog.show();
    }
}
