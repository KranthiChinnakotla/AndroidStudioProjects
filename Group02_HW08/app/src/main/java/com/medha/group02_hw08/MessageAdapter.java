package com.medha.group02_hw08;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prathyusha on 4/17/16.
 */
public class MessageAdapter extends ArrayAdapter<Messages> {
    List<Messages> mData;
    Context mContext;
    int mResource;

    public MessageAdapter(Context context, int resource, List<Messages> objects) {
        super(context, resource, objects);
        this.mData = objects;
        this.mContext = context;
        this.mResource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(mResource, parent, false);

        }

        Messages messages = mData.get(position);
        TextView tvName = (TextView) convertView.findViewById(R.id.textView_full_name);
        tvName.setText(messages.getSender());
        TextView tvMessage = (TextView) convertView.findViewById(R.id.textView_message);
        tvMessage.setText(messages.getMessage_text());
        TextView tvTime = (TextView) convertView.findViewById(R.id.textView_timestamp);
        tvTime.setText(messages.getTime_stamp());





        return convertView;
    }
}
