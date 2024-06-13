package com.example.demo43;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application {

    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;
    private static ObjectInputStream objectReader;
    public static Stage stage;
    static Controller controller;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6000);
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                InputStream inputStream = socket.getInputStream();
                objectReader = new ObjectInputStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            launch();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Controller readController() {
        Controller c = null;
        try {
            c = (Controller) objectReader.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void start(Stage s) throws IOException {
        controller = readController();
        stage = s;
        stage.setTitle("Tweeter!");
        Parent entryRoot = FXMLLoader.load(getClass().getResource("Entery2.fxml"));
        Scene entryScene = new Scene(entryRoot);
        stage.setScene(entryScene);
         stage.show();
    }


}
