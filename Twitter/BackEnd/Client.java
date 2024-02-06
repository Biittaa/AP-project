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
    //private Controller controller;
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
       /* User u1 = new User("Bita", "Bita", "Asheghi", "9999");
        u1.setPassword("1212qw");
        User u2 = new User("Kosar", "Kosar", "Sohani", "9999");
        User u3 = new User("ElonMusk", "jennie", "kim", "9999");
        User u4 = new User("m;)", "mike", "jonas", "9999");
        Enter.addUser(u1);
        Enter.addUser(u2);
        Enter.addUser(u3);
        Enter.addUser(u4);
        ArrayList<Tweet> commentReply = new ArrayList<>();
        commentReply.add(new Tweet("nice! bye", 5, 2, commentReply, u1, LocalDateTime.now()));
        commentReply.add(new Tweet("not your business bye", 5, 2, commentReply, u2, LocalDateTime.now()));
        commentReply.add(new Tweet("nice fantastic  bye", 5, 2, commentReply, u3, LocalDateTime.now()));
        commentReply.add(new Tweet("nice good to hear your voice bye no no no no\nbye", 5, 2, commentReply, u4, LocalDateTime.of(2017, 2, 13, 15, 56)));
        commentReply.add(new Tweet("nicegoodbyehi hi hi hi  hi you are not me ha  #daily ha ha bye", 5, 2, commentReply, u1, LocalDateTime.of(2018, 6, 23, 15, 6)));
        Tweet t0 = new Tweet("hello  what are you doing bye!\n #love #free #bye #daily", 5, 2, commentReply, u2, LocalDateTime.of(2023, 6, 27, 21, 50));
        u2.getAbility().addTweet(t0);
        Tweet t1 = new Tweet("hello + i am very busy today dont bother me #daily  bye!", 30, 8, commentReply, u3, LocalDateTime.of(2023, 6, 27, 21, 50));
        u3.getAbility().addTweet(t1);
        Tweet t2 = new Tweet("hello  future past lord speak\nsee you on speech! #hello  bye!", 1000, 2, commentReply, u4, LocalDateTime.of(2023, 6, 27, 19, 50));
        u4.getAbility().addTweet(t2);
        ReTweet t3 = new ReTweet(u4, t1);
        u4.getAbility().addTweet(t3);
        Quote t6 = new Quote("hello \n see see see  look look look look bye #hello  bye!", 1000, 2, commentReply, u1, LocalDateTime.of(2023, 6, 27, 20, 50));
        t6.setTweet(t2);
        u3.getAbility().addTweet(t6);
        TimeLine.addAllTweets(t0);
        TimeLine.addAllTweets(t1);
        TimeLine.addAllTweets(t2);
        TimeLine.addAllTweets(t3);
        TimeLine.addAllTweets(t6);*/
        controller = readController();
        stage = s;
        stage.setTitle("Tweeter!");
        Parent entryRoot = FXMLLoader.load(getClass().getResource("Entery2.fxml"));
        Scene entryScene = new Scene(entryRoot);
        stage.setScene(entryScene);
         stage.show();
    }


}
