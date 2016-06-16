package com.medha.midterm;

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
 * Created by Prathyusha on 3/21/16.
 */
public class VenueAdapter extends ArrayAdapter<Venue> {

    Context aContext;
    int aResource;
    List<Venue> aData;

    public VenueAdapter(Context context, int resource, List<Venue> objects) {
        super(context, resource, objects);
        this.aContext = context;
        this.aResource = resource;
        this.aData = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(aResource, parent, false);
        }
        TextView tvVenueName = (TextView) convertView.findViewById(R.id.textView_venuename);
        TextView tvCategoryName = (TextView) convertView.findViewById(R.id.textView_categoryname);
        tvVenueName.setText(aData.get(position).getVenueName());
        tvCategoryName.setText(aData.get(position).getCategoryName());
        ImageView imageViewBadge = (ImageView) convertView.findViewById(R.id.imageView_badge);
        ImageView imageViewChoice = (ImageView) convertView.findViewById(R.id.imageView_choice);
        ImageView imageViewVenue = (ImageView) convertView.findViewById(R.id.imageView_venue);

        Picasso.with(aContext).load(aData.get(position).categoryIcon).into(imageViewVenue);

        imageViewChoice.setImageResource(R.drawable.unvisited);
        if (Long.parseLong(aData.get(position).getCheckinCount()) <= 100)
            imageViewBadge.setImageResource(R.drawable.bronze);
        else if ((Long.parseLong(aData.get(position).getCheckinCount()) > 100) && (Long.parseLong(aData.get(position).getCheckinCount()) <= 500))
            imageViewBadge.setImageResource(R.drawable.silver);
        else if((Long.parseLong(aData.get(position).getCheckinCount()) > 500))
            imageViewBadge.setImageResource(R.drawable.gold);
          return convertView;

    }
}
