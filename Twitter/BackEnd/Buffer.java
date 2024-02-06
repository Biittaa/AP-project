package com.example.demo43;

import java.io.*;
import java.net.Socket;

public class Buffer {
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private static ObjectOutputStream o;
    public static void setBuffer(Socket socket){
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            o = new ObjectOutputStream(socket.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void write(String message){
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void write(int message){
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void write(Controller c){
        try {
            o.writeObject(c);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String readString(){
        try{
            return bufferedReader.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static int readInt(){
        try{
            int h = bufferedReader.read();
            bufferedReader.readLine();
            return h;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }
}

