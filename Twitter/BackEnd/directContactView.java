package com.example.demo43;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class directContactView {
    Scene lastScene;

    public void setLastScene(Scene lastScene) {
        this.lastScene = lastScene;
    }

    public void show(){
        try {
            Enter.readUsers();
            Parent root = FXMLLoader.load(getClass().getResource("DirectContacts.fxml"));
            VBox v = (VBox) ((ScrollPane)(((VBox)root).getChildren().get(1)) ).getContent();
            Button b =(Button) ((HBox)(((VBox)root).getChildren().get(0))).getChildren().get(0);
            b.setOnAction(e->{
                Client.stage.setScene(lastScene);
            });
            ArrayList<Contact> contacts = Client.controller.getUser().getDirect().contactShow();
            VBox box = new VBox();
            for(Contact c : contacts){
                HBox h = new HBox();
                SearchView s = new SearchView();
                h = s.userShow(c.getUser());
                Label label =(Label) h.getChildren().get(1);
                label.setOnMouseClicked(e->{
                    directView d = new directView();
                    d.setLastScene(label.getScene());
                    d.displayMessagePage(c.getUser());
                });
                box.getChildren().addAll(h);
            }
            v.getChildren().addAll(box);
            Client.stage.setScene(new Scene(root));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
