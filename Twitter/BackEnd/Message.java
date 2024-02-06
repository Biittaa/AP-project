package com.example.demo43;

import java.io.Serializable;

public class Message implements Serializable {
    User u;
    String message;

    public Message(User u, String message) {
        this.u = u;
        this.message = message;
    }

    public User getU() {
        return u;
    }
}
