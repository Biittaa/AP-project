package com.example.demo43;

import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {
    private Socket socket;
    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private User user;
    Controller c;

    public ClientHandler() {
        clientHandlers.add(this);
    }

    @Override
    public void run() {
        c = new Controller();
        Enter.readUsers();
        Buffer.write(c);
    }

    public static ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }
}


