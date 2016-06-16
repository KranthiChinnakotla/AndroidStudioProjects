package com.medha.listviewdemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prathyusha on 2/28/16.
 */
public class ColorsAdapter extends ArrayAdapter<Colors>{

    Context mContext;
    int mResource;
    List<Colors> mColors;

    public ColorsAdapter(Context context, int resource, List<Colors> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mColors = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }

        Colors color = mColors.get(position);
        TextView textViewColorName = (TextView) convertView.findViewById(R.id.textView_ColorName);
        TextView textViewColorHex = (TextView) convertView.findViewById(R.id.textView_ColorHex);

        textViewColorName.setText(mColors.get(position).colorName);
        textViewColorHex.setText(mColors.get(position).colorHex);
        textViewColorHex.setTextColor(android.graphics.Color.parseColor(color.colorHex));
        return convertView;
    }
}
