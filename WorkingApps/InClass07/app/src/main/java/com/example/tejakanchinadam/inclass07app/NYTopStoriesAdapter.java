package com.example.tejakanchinadam.inclass07app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by tejakanchinadam on 2/29/16.
 */
public class NYTopStoriesAdapter extends ArrayAdapter<Stories> {

        Context mContext;
        int mResource;
        List<Stories> mData;

        public NYTopStoriesAdapter(Context context, int resource, List<Stories> objects) {
            super(context, resource, objects);
            this.mContext = context;
            this.mResource = resource;
            this.mData = objects;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if(convertView == null){
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(mResource,parent,false);
            }

            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(mData.get(position).storyTitle);
            TextView date = (TextView) convertView.findViewById(R.id.date);


            String myDate=(mData.get(position).getDate().substring(0,mData.get(position).getDate().indexOf("T")));

            String Month = myDate.substring(5,7);
            String Day = myDate.substring(8,10);

            date.setText(Month+"/"+Day);


            ImageView img = (ImageView) convertView.findViewById(R.id.thumbNail);

            Picasso.with(mContext).load(mData.get(position).thumbnail).into(img);


            return convertView;

        }
    }



