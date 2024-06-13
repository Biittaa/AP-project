package com.example.demo43;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        BorderPane borderPane = new BorderPane();
        ToolBar toolBar = new ToolBar();
        Button button1 = new Button("TimeLine");
        Button button2 = new Button("Profile");
        Button button3 = new Button("Search");
        Button button4 = new Button("Direct");
        VBox vbox = new VBox();
        vbox.setPrefHeight(600);
        vbox.setPrefWidth(700);
        ArrayList<String> tweetsList = new ArrayList<>();
        ArrayList<String> commentList = new ArrayList<>();
        tweetsList.add("hello");
        System.out.println(tweetsList.size());
         toolBar.getItems().addAll(button1, button2, button3, button4);
        borderPane.setBottom(toolBar);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefHeight(700);
        scrollPane.setPrefWidth(600);
        scrollPane.setContent(vbox);
        borderPane.setCenter(scrollPane);
        Scene scene = new Scene(borderPane,700,700);
        stage.setTitle("Tweeter!");
        stage.setScene(scene);
        stage.show();
        for (int i = 0; i < tweetsList.size(); i++) {
            BorderPane rowPane = new BorderPane();
            Label label = new Label(tweetsList.get(i));
            rowPane.setCenter(label);
            ToolBar toolBar2 = new ToolBar();
            Button b1 = new Button("comment");
            Button b2 = new Button("retweet");
            Button b3 = new Button("like");
            Button b4 = new Button("Direct");
            b1.setOnAction(e-> {
                Button backButton = new Button("back");
                ScrollPane scrollPane1 = new ScrollPane();
                System.out.println("comment");
                VBox vbox2 = new VBox();
                vbox2.getChildren().addAll(backButton);
                for(int j=0;j<commentList.size();j++){
                    Label label1 = new Label(commentList.get(j));
                    vbox2.getChildren().addAll(label1);
                }
                TextField textField = new TextField();
                vbox2.getChildren().addAll(textField);
                textField.setOnAction(l ->{
                    String text = textField.getText();
                    Label label2 = new Label("comment saved!");
                    Label label3 = new Label(text);
                    vbox2.getChildren().add(commentList.size()+1,label3);
                    vbox2.getChildren().addAll(label2);
                    textField.clear();
                });
                backButton.setOnAction(l ->{
                    stage.setScene(scene);
                });
                VBox.setVgrow(vbox2, Priority.ALWAYS);
                vbox2.setPrefHeight(600);
                vbox2.setPrefWidth(700);
                scrollPane1.setContent(vbox2);
                Scene scene2 = new Scene(scrollPane1,600,600);
                stage.setScene(scene2);
            });
            b2.setOnAction(e->{
                b2.setStyle("-fx-background-color: blue");
            });
            b3.setOnAction(new ButtonHandler(b3));
            toolBar2.getItems().addAll(b1, b2, b3, b4);
            rowPane.setBottom(toolBar2);
            vbox.getChildren().add(rowPane);
        }
        button3.setOnAction(e->
        {
            VBox vBox3 = new VBox();
            vBox3.setPrefHeight(600);
            vBox3.setPrefWidth(700);
            Scene searchScene = new Scene(vBox3);
            TextField textField = new TextField("Search");
            vBox3.getChildren().addAll(textField);
            stage.setScene(searchScene);
        });
    }
    private class ButtonHandler implements EventHandler<ActionEvent>{
        private Button button;
        public ButtonHandler (Button button){
            this.button = button;
        }
        @Override
        public void handle(ActionEvent actionEvent){
            System.out.println("like button pressed!");
            button.setStyle("-fx-background-color: red;");
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
