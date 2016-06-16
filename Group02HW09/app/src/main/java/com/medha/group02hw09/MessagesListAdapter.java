package com.medha.group02hw09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.List;

/**
 * Created by tejakanchinadam on 4/17/16.
 */
public class MessagesListAdapter extends ArrayAdapter<Chat> {

    Firebase ref = new Firebase("https://group02hw09.firebaseio.com/");
    static Firebase usersRef;

    List<Chat> mData;

    Context mContext;

    int mResource;

    Chat chat;

    public MessagesListAdapter(Context context, int resource, List<Chat> objects) {
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

        //ConversationListAdapter.redBubble = mData.get(position).getMessageRead();

        //ConversationListAdapter.redBubble = true;

        chat = mData.get(position);

        TextView chatName = (TextView) convertView.findViewById(R.id.chatName);

        TextView chatText = (TextView) convertView.findViewById(R.id.chatText);

        TextView chatTime = (TextView) convertView.findViewById(R.id.chatTime);

        Button deleteBtn = (Button) convertView.findViewById(R.id.deleteBtn);



        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Firebase usersRef = ref.child("messages");

                Firebase alanRef = usersRef.child(mData.get(position).getMessageID());

                MessageFragment.messageIDList.remove(mData.get(position).getMessageID());

                MessageFragment.myChatList.remove(mData.get(position));

                alanRef.setValue(null);

                MessageFragment.adapter.notifyDataSetChanged();


            }
        });

        if((chat.getReceriver()).equals(MessageFragment.receivingUser.getName()) ){

            convertView.setBackgroundResource(R.color.grey);

            chatName.setText(MessageFragment.receivingUser.getName());

            chatText.setText(chat.getMessageText());

            chatTime.setText(chat.getTimeStamp());


        }else{

            chatName.setText(MessageFragment.receivingUser.getName());

            chatText.setText(chat.getMessageText());

            chatTime.setText(chat.getTimeStamp());

            deleteBtn.setVisibility(View.INVISIBLE);

        }

        return convertView;
    }


}
