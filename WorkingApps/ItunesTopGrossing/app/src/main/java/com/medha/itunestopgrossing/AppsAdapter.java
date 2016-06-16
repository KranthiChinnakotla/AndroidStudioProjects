package com.medha.itunestopgrossing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Prathyusha on 6/14/16.
 */
public class AppsAdapter extends ArrayAdapter<TopApps> {

    Context aContext;
    int aResource;
    List<TopApps> aData;

    public AppsAdapter(Context context, int resource, List<TopApps> objects) {
        super(context, resource, objects);

        this.aContext = context;
        this.aData = objects;
        this.aResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(aResource,parent,false);
        }
        TextView tvAppName,tvDevname,tvRelDate,tvPrice,tvCategory;
        ImageView imageView;
        tvAppName = (TextView) convertView.findViewById(R.id.textView_appname);
        tvDevname = (TextView) convertView.findViewById(R.id.textView_devname);
        tvCategory = (TextView) convertView.findViewById(R.id.textView_category);
        tvPrice = (TextView) convertView.findViewById(R.id.textView_price);
        tvRelDate = (TextView) convertView.findViewById(R.id.textView_reldate);
        imageView = (ImageView) convertView.findViewById(R.id.imageView);

        Picasso.with(aContext).load(aData.get(position).getImage()).into(imageView);
        tvAppName.setText(aData.get(position).getName());
        tvDevname.setText(aData.get(position).getDev_name());
        tvCategory.setText(aData.get(position).getCategory());
        tvPrice.setText(String.valueOf( aData.get(position).getPrice()));
        tvRelDate.setText(aData.get(position).getRel_date());

        return convertView;
    }
}
