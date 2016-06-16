package com.medha.group02hw09;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prathyusha on 4/27/16.
 */
public class ArchiveAdapter extends ArrayAdapter<Conversations> {
    List<Conversations> mData;

    Context mContext;

    int mResource;

    public ArchiveAdapter(Context context, int resource, List<Conversations> objects) {
        super(context, resource, objects);
        this.mContext = context;

        this.mData = objects;

        this.mResource = resource;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(mResource, parent, false);

        }

        //redBubble = false;

        Conversations user = mData.get(position);

        TextView userName = (TextView) convertView.findViewById(R.id.userName);

        ImageView profilePicture = (ImageView) convertView.findViewById(R.id.profilePicture);

        ImageView bubbleRed = (ImageView) convertView.findViewById(R.id.redBubble);
        bubbleRed.setImageResource(R.drawable.bubblered);
        bubbleRed.setVisibility(View.INVISIBLE);

        ImageView phonePicture = (ImageView) convertView.findViewById(R.id.phonePicture);



        userName.setText(user.getParticipant1());

        phonePicture.setImageResource(R.drawable.phone);

        return convertView;
    }
    public static Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
