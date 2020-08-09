package com.moringaschool.hellozuz;

import com.moringaschool.hellozuz.models.MessageData;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageDataTest {

    @Test
    public void getMessage() {
        MessageData messageData = setMessageData();
        assertEquals("Hey come over here", messageData.getMessage());
    }

    @Test
    public void setMessage() {
        MessageData messageData = setMessageData();
        messageData.setMessage("Hey stop it");
        assertEquals("Hey stop it", messageData.getMessage());
    }

    @Test
    public void getSender() {
        MessageData messageData = setMessageData();
        assertEquals("Monsoon", messageData.getSender());
    }

    @Test
    public void setSender() {
        MessageData messageData = setMessageData();
        messageData.setSender("Koech");
        assertEquals("Koech", messageData.getSender());
    }

    //helpers
    public MessageData setMessageData() {
        return new MessageData("Hey come over here", "Monsoon");
    }
}