package com.medha.group02hw09;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tejakanchinadam on 4/17/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Chat {

    String messageText, receriver, sender, timeStamp, messageID;

    Boolean messageRead;

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public Boolean getMessageRead() {
        return messageRead;
    }

    public void setMessageRead(Boolean messageRead) {
        this.messageRead = messageRead;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getReceriver() {
        return receriver;
    }

    public void setReceriver(String receriver) {
        this.receriver = receriver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Chat(Boolean messageRead, String messageText, String receriver, String sender, String timeStamp, String messageID) {
        this.messageRead = messageRead;
        this.messageText = messageText;
        this.receriver = receriver;
        this.sender = sender;
        this.timeStamp = timeStamp;
        this.messageID = messageID;
    }

    public Chat(){


    }
}
