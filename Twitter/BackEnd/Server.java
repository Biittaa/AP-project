package com.example.demo43;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static void main(String[] args){
		ExecutorService pool = Executors.newCachedThreadPool();
		try (ServerSocket serverSocket = new ServerSocket(6000)){
			while(true){
				Socket client = serverSocket.accept();
				Buffer.setBuffer(client);
				ClientHandler clientHandler = new ClientHandler();
				pool.execute(clientHandler);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
