package com.inclass.raja.homework5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Prathyusha on 3/18/16.
 */
public class ForecastAdapter extends ArrayAdapter<Forecast> {
    Context aContext;
    int aResource;
    List<Forecast> aData;
    int hour;
    String timeFormat;

    public ForecastAdapter(Context context, int resource, List<Forecast> objects) {
        super(context, resource, objects);
        this.aContext = context;
        this.aResource = resource;
        this.aData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(aResource, parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_forecast);
        ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imageView_saveNote);
        if(aData.get(position).getNotes()!=null){
            imageView1.setVisibility(convertView.VISIBLE);
        }
        Picasso.with(aContext).load(aData.get(position).iconUrl).into(imageView);
        TextView textDate = (TextView) convertView.findViewById(R.id.textView_dateforecast);
        TextView textClouds = (TextView) convertView.findViewById(R.id.textView_clouds);
        TextView textTemp_high_low = (TextView) convertView.findViewById(R.id.textView_high_low);
        textDate.setText(aData.get(position).getDate());
        textClouds.setText(aData.get(position).getClouds());
        textTemp_high_low.setText(aData.get(position).getHighTemp() + "F/" + aData.get(position).getLowTemp() + "F");
        return convertView;
    }
}
