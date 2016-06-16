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
 * Created by Prathyusha on 3/8/16.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {
    Context aContext;
    int aResource;
    List<Weather> aData;
    int hour;
    String timeFormat;
    public WeatherAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);
        this.aContext = context;
        this.aResource = resource;
        this.aData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(aResource,parent,false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(aContext).load(aData.get(position).iconUrl).into(imageView);
        TextView textTime = (TextView) convertView.findViewById(R.id.textView_time);
        hour = Integer.parseInt(aData.get(position).time);
        switch (hour){
            case 0:
                timeFormat = "12 AM";
                break;
            case 1:
                timeFormat = "01 AM";
                break;
            case 2:
                timeFormat = "02 AM";
                break;
            case 3:
                timeFormat = "03 AM";
                break;
            case 4:
                timeFormat = "04 AM";
                break;
            case 5:
                timeFormat = "05 AM";
                break;
            case 6:
                timeFormat = "06 AM";
                break;
            case 7:
                timeFormat = "07 AM";
                break;
            case 8:
                timeFormat = "08 AM";
                break;
            case 9:
                timeFormat="09 AM";
                break;
            case 10:
                timeFormat = "10 AM";
                break;
            case 11:
                timeFormat = "11 AM";
                break;
            case 12:
                timeFormat = "12 PM";
                break;
            case 13:
                timeFormat = "01 PM";
                break;
            case 14:
                timeFormat = "02 PM";
                break;
            case 15:
                timeFormat = "03 PM";
                break;
            case 16:
                timeFormat = "04 PM";
                break;
            case 17:
                timeFormat = "05 PM";
                break;
            case 18:
                timeFormat = "06 PM";
                break;
            case 19:
                timeFormat="07 PM";
                break;
            case 20:
                timeFormat = "08 PM";
                break;
            case 21:
                timeFormat = "09 PM";
                break;
            case 22:
                timeFormat = "10 PM";
                break;
            case 23:
                timeFormat = "11 PM";
                break;
            default:
                break;
        }
        textTime.setText(timeFormat);
        TextView textClim = (TextView) convertView.findViewById(R.id.textView_climatetype);
        textClim.setText(aData.get(position).climateType);
        TextView textTemp = (TextView) convertView.findViewById(R.id.textView_temp);
        textTemp.setText(aData.get(position).temperature + " F");
        return convertView;



    }
}
