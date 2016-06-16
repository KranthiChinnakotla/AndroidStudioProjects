package com.inclass.raja.homework5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathyusha on 3/21/16.
 */
public class NotesAdapter extends ArrayAdapter<Notes> {

    Context aContext;
    int aResource;
    List<Notes> aData;


    public NotesAdapter(Context context, int resource, List<Notes> objects) {
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

        TextView tvNotes = (TextView) convertView.findViewById(R.id.textView_Notes);
        TextView tvDate = (TextView) convertView.findViewById(R.id.textView_NotesDate);
        tvNotes.setText(aData.get(position).getNote());
        tvDate.setText(aData.get(position).getDate());
        return convertView;
    }

}
