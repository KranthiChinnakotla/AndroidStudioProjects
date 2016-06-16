package com.medha.group02hw09;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Prathyusha on 4/21/16.
 */
public class NavigatorAdapter extends ArrayAdapter<String>{

    Context mContext;
    int mResource;
    String [] mData;

    public NavigatorAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
        mData=objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(mResource, parent, false);

        }

        TextView tvNavContent = (TextView) convertView.findViewById(R.id.textView_navbar);
        ImageView user_image = (ImageView) convertView.findViewById(R.id.imageView_navbar);
        if(position == 0){
            String [] name_image = mData[position].split(",");
            String encodedImage = name_image[0];
            byte[] decodeString;
            Bitmap userImage = null;
            if(encodedImage != null){
                decodeString = Base64.decode(encodedImage, Base64.DEFAULT);
                userImage = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);}
            if(userImage != null)
                user_image.setImageBitmap(userImage);
            tvNavContent.setText(name_image[1]);

        }
        if(position!=0)
        tvNavContent.setText(mData[position]);


       /* ImageView user_image = (ImageView) convertView.findViewById(R.id.imageView);
        TextView name = (TextView) convertView.findViewById(R.id.textView_nav_name);*/
       /* String encodedImage = user.getPicture();
        byte[] decodeString;
        Bitmap userImage = null;
        if(encodedImage != null){
            decodeString = Base64.decode(encodedImage, Base64.DEFAULT);
            userImage = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);}
        if(userImage != null)
            user_image.setImageBitmap(userImage);

        msg_image.setImageResource(R.drawable.red_bubble_clipart_1);
        phone_image.setImageResource(R.drawable.phone_icon_hi);

        name.setText(users.getFull_name());*/


        return convertView;
     }
    }

