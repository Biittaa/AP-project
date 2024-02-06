package com.example.demo43;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SearchDisplay {
    public void show(){
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("SearchView2.fxml"));
        try {
            Parent r = fxmlLoader.load();
            Scene searchScene = new Scene(r);
            BorderPane root = (BorderPane) ((ScrollPane) r).getContent();
            if (root instanceof BorderPane) {
                BorderPane b =  root;
                ToolBar toolBar2 = new ToolBar();
                Button s1 = new Button("TimeLine");
                s1.setPrefWidth(80);
                s1.setPrefHeight(30);
                s1.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
                Button s2 = new Button("Profile");
                s2.setPrefWidth(80);
                s2.setPrefHeight(30);
                s2.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
                Button s5 = new Button(" + ");
                s5.setPrefWidth(80);
                s5.setPrefHeight(30);
                s5.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
                Button s3 = new Button("Search");
                s3.setPrefWidth(80);
                s3.setPrefHeight(30);
                s3.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
                Button s4 = new Button("Direct");
                s4.setPrefWidth(80);
                s4.setPrefHeight(30);
                s4.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
                toolBar2.getItems().addAll(s1, s2, s5, s3, s4);
                b.setBottom(toolBar2);
                Client.stage.setScene(searchScene);
                s1.setOnAction(p -> {
                    HomePage j = new HomePage();
                    j.TimeLine();
                });
                s2.setOnAction(p->{
                    try {
                        Parent r1 = FXMLLoader.load(getClass().getResource("OwnerProfile.fxml"));
                        Scene scene = new Scene(r1);
                        Client.stage.setScene(scene);
                    }
                    catch (IOException l){
                        l.printStackTrace();
                    }
                });
                s5.setOnAction(p -> {
                    try {
                        Scene s = ((Node)p.getSource()).getScene();
                        NewTweetView.setLastScene(s, Client.controller);
                        Parent r1 = FXMLLoader.load(HelloApplication.class.getResource("newTweetView.fxml"));
                        Scene scene = new Scene(r1);
                        Client.stage.setScene(scene);
                        NewTweetView.setLastScene(searchScene, Client.controller);
                    } catch (IOException o) {
                        o.printStackTrace();
                    }
                });
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
