package com.medha.group02hw09;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Prathyusha on 4/23/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conversations {

    String deletedBy,participant1,participant2,conversationId,isArchived_by_participant1,isArchived_by_participant2;

    public Conversations(String deletedBy, String participant1, String participant2, String isArchived_by_participant1, String isArchived_by_participant2,String conversationId) {
        this.deletedBy = deletedBy;
        this.participant1 = participant1;
        this.participant2 = participant2;
        this.isArchived_by_participant1 = isArchived_by_participant1;
        this.isArchived_by_participant2 = isArchived_by_participant2;
        this.conversationId = conversationId;
    }

    public Conversations() {
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getParticipant1() {
        return participant1;
    }

    public void setParticipant1(String participant1) {
        this.participant1 = participant1;
    }

    public String getParticipant2() {
        return participant2;
    }

    public void setParticipant2(String participant2) {
        this.participant2 = participant2;
    }

    public String getIsArchived_by_participant1() {
        return isArchived_by_participant1;
    }

    public void setIsArchived_by_participant1(String isArchived_by_participant1) {
        this.isArchived_by_participant1 = isArchived_by_participant1;
    }

    public String getIsArchived_by_participant2() {
        return isArchived_by_participant2;
    }

    public void setIsArchived_by_participant2(String isArchived_by_participant2) {
        this.isArchived_by_participant2 = isArchived_by_participant2;
    }
    /*public boolean isArchived_by_participant1() {
        return isArchived_by_participant1;
    }

    public void setArchived_by_participant1(boolean isArchived_by_participant1) {
        this.isArchived_by_participant1 = isArchived_by_participant1;
    }

    public boolean isArchived_by_participant2() {
        return isArchived_by_participant2;
    }

    public void setArchived_by_participant2(boolean isArchived_by_participant2) {
        this.isArchived_by_participant2 = isArchived_by_participant2;
    }*/

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
