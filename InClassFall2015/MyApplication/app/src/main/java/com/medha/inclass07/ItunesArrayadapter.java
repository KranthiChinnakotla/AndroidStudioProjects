package com.medha.inclass07;

import android.content.Context;
import android.net.ConnectivityManager;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prathyusha on 2/29/16.
 */
public class ItunesArrayadapter extends ArrayAdapter<Itunes> {
    Context mContext;
    int mResource;
    List<Itunes> mData;

    public ItunesArrayadapter(Context context, int resource, List<Itunes> objects) {
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

        TextView tvAppname = (TextView) convertView.findViewById(R.id.textView_appname);
        tvAppname.setText(mData.get(position).appName);
        TextView tvDevname = (TextView) convertView.findViewById(R.id.textView_developername);
        tvDevname.setText(mData.get(position).developerName);
        TextView tvReleaseDate = (TextView) convertView.findViewById(R.id.textView_releasedate);
        tvReleaseDate.setText(mData.get(position).releaseDate);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.textView_price);
        tvPrice.setText( mData.get(position).price.toString());
        TextView tvCategory = (TextView) convertView.findViewById(R.id.textView_Category);
        tvCategory.setText(mData.get(position).category);
        RatingBar bar = (RatingBar) convertView.findViewById(R.id.ratingBar);


        return convertView;

    }
}
