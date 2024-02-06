package com.example.demo43;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RetweetView {
    static Scene lastScene;
    @FXML
    Button backButton;
    @FXML
    Button enterButton;
    @FXML
    TextArea textArea;
    static Tweet tweet;
    @FXML
    void onBackButtonPressed(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(lastScene);
        HomePage h = new HomePage();
        h.TimeLine();
    }
    @FXML
    void onEnterButtonPressed(ActionEvent event){
        VBox vBox = (VBox) textArea.getParent();
        if(vBox.getChildren().get(3) instanceof Label){
            vBox.getChildren().remove(3);
        }
        String text = textArea.getText();
        if(text.isEmpty()){
            Label warning = new Label("   Enter something");
            warning.setTextFill(Color.RED);
            vBox.getChildren().add(3,warning);
            return;
        }
        if(Client.controller.checkTextTweet(text) == -1){
            Label warning = new Label("      Character limit");
            warning.setTextFill(Color.RED);
            vBox.getChildren().add(3,warning);
            return;
        }
        Client.controller.addNewQuote(text,tweet);
        HomePage l =new HomePage();
        l.TimeLine();
    }

    public static void setLastScene(Scene lastScenes) {
        lastScene = lastScenes;
    }

    public static void setTweet(Tweet tweets) {
        tweet = tweets;
    }
}

