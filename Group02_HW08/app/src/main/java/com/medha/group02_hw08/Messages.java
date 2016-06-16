package com.medha.group02_hw08;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Prathyusha on 4/17/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Messages {
    String time_stamp,message_read,message_text,receiver,sender;

    public Messages() {
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getMessage_read() {
        return message_read;
    }

    public void setMessage_read(String message_read) {
        this.message_read = message_read;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
