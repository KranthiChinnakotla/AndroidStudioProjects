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
 * Created by Prathyusha on 4/23/16.
 */
public class ConversationListAdapter extends ArrayAdapter<User>  {
    List<User> mData;

    Context mContext;

    int mResource;


    //static boolean redBubble = false;

    public ConversationListAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);

        this.mContext = context;

        this.mData = objects;

        this.mResource = resource;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(mResource, parent, false);

        }

        //redBubble = false;

        User user = mData.get(position);

         TextView userName = (TextView) convertView.findViewById(R.id.userName);

        ImageView profilePicture = (ImageView) convertView.findViewById(R.id.profilePicture);

        ImageView bubbleRed = (ImageView) convertView.findViewById(R.id.redBubble);
        bubbleRed.setImageResource(R.drawable.bubblered);
        bubbleRed.setVisibility(View.INVISIBLE);

        ImageView phonePicture = (ImageView) convertView.findViewById(R.id.phonePicture);

        phonePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mData.get(position).getPhone()));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
                String uri = "tel:" + mData.get(position).getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;

                    //callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                }

                /*String uri = "tel:" + mData.get(position).getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse(uri));*/
                mContext.startActivity(intent);



            }
        });

        userName.setText(user.getName());



        if(mData.get(position).getPicture()!=null){
            String encodedImage = mData.get(position).getPicture();
            byte[] decodeString;
            Bitmap userImage = null;
            if(encodedImage != null){
                decodeString = Base64.decode(encodedImage, Base64.DEFAULT);
                userImage = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);}
            if(userImage != null)
                profilePicture.setImageBitmap(userImage);
        }


        if(!mData.get(position).isMsgread()) {

            bubbleRed.setVisibility(View.VISIBLE);

        }

        phonePicture.setImageResource(R.drawable.phone);

        return convertView;
    }
    public static Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
