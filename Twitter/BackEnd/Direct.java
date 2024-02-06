package com.example.demo43;

import java.io.Serializable;
import java.util.ArrayList;

public class Direct implements Serializable {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void sendChat(String myMessage, User u) {
        for (Contact c : contacts) {
            if (c.getUser().getID().equals(u.getID())) {
                c.sendMessage(myMessage);
                return;
            }
        }
        Contact c = new Contact(u);
        contacts.add(c);
        c.sendMessage(myMessage);
        for(User t : Enter.users){
            if(t.getID().equals(u.getID())){
                Enter.users.remove(t);
                Enter.users.add(u);
                break;
            }
        }
        WritingFile.writingUsers();
    }
    public void receiveChat(User u,String yourMessage){
        for (Contact c : contacts) {
            if (c.getUser().getID().equals(u.getID())) {
                c.receiveMessage(yourMessage,u);
                return;
            }
        }
        Contact c = new Contact(u);
        contacts.add(c);
        c.receiveMessage(yourMessage,u);
        for(User t : Enter.users){
            if(t.getID().equals(u.getID())){
                Enter.users.remove(t);
                Enter.users.add(u);
                break;
            }
        }
        WritingFile.writingUsers();
    }
    public  ArrayList<Message> getMessage(User u){
        for (Contact c : contacts) {
            if (c.getUser().getID().equals(u.getID())) {
                return c.getMessages();
            }
        }
        return new ArrayList<>();
    }
    public ArrayList<Contact> contactShow(){
        return contacts;
    }
}
