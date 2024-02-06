package com.example.demo43;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class directView {
    @FXML
    Label contactName;
    @FXML
    VBox v2;
    Scene lastScene;

    public void setLastScene(Scene lastScene) {
        this.lastScene = lastScene;
    }

    @FXML
    public void displayMessagePage(User destination){
        try {
            Enter.readUsers();
            Parent root = FXMLLoader.load(getClass().getResource("DirectMessages.fxml"));
            Scene scene = new Scene(root);
            ArrayList<Message> messages = Client.controller.getAllMessages(destination);
            VBox v = new VBox();
            if(root instanceof VBox){
                v = (VBox) root;
            }
            ((Label)(((HBox)(v.getChildren().get(0)))).getChildren().get(1)).setText("     @"+destination.getID());
            Button button =(Button) (((HBox)(v.getChildren().get(0)))).getChildren().get(0);
            Button refresh = (Button) (((HBox)(v.getChildren().get(0)))).getChildren().get(2);
            button.setOnAction(e->{
                Client.stage.setScene(lastScene);
            });
            refresh.setOnAction(e->{
                directView f = new directView();
                Enter.readUsers();
                int i=0;
                for(User u : Enter.users){
                    if(u.getID().equals(destination.getID())){
                        f.setLastScene(lastScene);
                        f.displayMessagePage(u);
                        i=1;
                        break;
                    }
                }
                if(i!=1){
                    f.setLastScene(lastScene);
                    f.displayMessagePage(destination);
                }

            });
            if(v.getChildren().get(1) instanceof ScrollPane){
                ScrollPane s =(ScrollPane) v.getChildren().get(1);
                v2 =(VBox) s.getContent();
            }
            for (Message m : messages) {
                TextArea l = new TextArea();
                l.setMaxWidth(250);
                l.setMaxHeight(50);
                l.setEditable(false);
                if (m.getU().getID().equals(destination.getID())) {
                    l.setText(m.message);
                    l.setStyle("-fx-background-color: pink");
                } else {
                    l.setText(m.message);
                    l.setStyle("-fx-background-color: rgba(0,196,255,0.99)");
                }
                v2.getChildren().addAll(l);
            }
            Client.stage.setScene(v.getScene());
            HBox h = (HBox) v.getChildren().get(2);
            Button b =(Button) h.getChildren().get(1);
            TextArea r = (TextArea) h.getChildren().get(0);
            v2.setSpacing(4);
            b.setOnAction(e->{
                Enter.readUsers();
                TextArea l = new TextArea();
                l.setMaxWidth(250);
                l.setMaxHeight(50);
                if(r.getText().isEmpty()){
                    r.setText("Enter something!");
                }
                else {
                    l.setPrefHeight(Double.MIN_VALUE);
                    l.setText(r.getText());
                    Client.controller.sendChat(r.getText(),destination);
                    for(User u : Enter.users){
                        if(u.getID().equals(destination.getID())){
                            Enter.users.remove(u);
                            Enter.users.add(destination);
                            break;
                        }
                    }
                    WritingFile.writingUsers();
                    l.setStyle("-fx-background-color: rgba(0,196,255,0.99)");
                    v2.getChildren().addAll(l);
                    r.setText("");
                }
            });
        }
        catch(IOException r){
            r.printStackTrace();
        }
    }
}
