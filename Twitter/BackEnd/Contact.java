package com.example.demo43;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable {
    private User user;
    private ArrayList<Message> Messages = new ArrayList<>();

    public Contact(User user) {
        this.user = user;
    }

    public void sendMessage(String message){
        Messages.add(new Message(Client.controller.getUser(),message));
        WritingFile.writingUsers();
    }
    public void receiveMessage(String message,User u){
        Messages.add(new Message(u,message));
    }
    public User getUser() {
        return user;
    }

    public ArrayList<Message> getMessages() {
        System.out.println(Messages.size());
        return Messages;
    }
}
