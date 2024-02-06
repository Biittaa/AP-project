package com.example.demo43;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

public class NewTweetView {
    static Scene lastScene;
    static Controller c;
    @FXML
    Button backButton;
    @FXML
    TextArea field;
    @FXML
    Button postButton;
    @FXML
    Button imageButton;

    @FXML
    void onbackButtonPressed(ActionEvent event) {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        WritingFile.writingUsers();
        stage.setScene(lastScene);
    }

 

    @FXML
    void onFieldEntered(ActionEvent event) {
        //TextArea field = (TextArea) event.getSource();
        postButton.setDisable(false);
    }

    @FXML
    void setPostButton(ActionEvent event) {
        VBox vBox = null;
        BorderPane borderPane = null;
        if (((Node) event.getSource()).getParent().getParent() instanceof VBox) {
            vBox = (VBox) ((Node) event.getSource()).getParent().getParent();
            borderPane = (BorderPane) vBox.getChildren().get(1);
            if (borderPane.getBottom() instanceof Label) {
                ((Label) borderPane.getBottom()).setPrefHeight(0);
                ((Label) borderPane.getBottom()).setPrefWidth(0);
                ((Label) borderPane.getBottom()).setText("");

            }
        }
        if (field.getText().isEmpty()) {
            postButton.setDisable(true);
            Label warning = new Label("      Enter something!");
            warning.setTextFill(Color.RED);
            borderPane.setBottom(warning);
        } else {
            if (c.checkTextTweet(field.getText()) == -1) {
                Label warning = new Label("      Character limit");
                warning.setTextFill(Color.RED);
                borderPane.setBottom(warning);
            }
            else {
                ArrayList<String> imageAddress = new ArrayList<>();
                BorderPane rowPane = (BorderPane) vBox.getChildren().get(2);;
                VBox imageVBox =(VBox) ((ScrollPane)rowPane.getRight()).getContent();
                ObservableList<Node> images = imageVBox.getChildren();
                for(Node n : images){
                    if(n instanceof ImageView){
                        //System.out.println(((ImageView) n).getImage().getUrl().toString());
                        imageAddress.add(((ImageView) n).getImage().getUrl().toString());
                    }
                }
                WritingFile.writingUsers();
                c.addNewTweet(field.getText(),imageAddress);
                WritingFile.writingUsers();
                HomePage homePage = new HomePage();
                homePage.TimeLine();
            }
        }
    }

    @FXML
    void onImageButtonPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File!");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        BorderPane rowPane = (BorderPane) imageButton.getParent().getParent();
        VBox vbox = (VBox) ((ScrollPane) rowPane.getRight()).getContent();
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            ImageView imageView = new ImageView(image);
            if (image.getWidth() > 900 || image.getHeight() > 1600) {
                System.out.println("no");

            }
            if (imageButton.getParent().getParent() instanceof BorderPane) {
                imageView.setFitWidth(200);
                imageView.setFitHeight(250);
                vbox.getChildren().addAll(imageView);
                // gridPane.getChildren().addAll(imageView);
                //gridPane.add(imageView,i,j);
            }
            WritingFile.writingUsers();
        }
    }


    public static void setLastScene(Scene lastScenes, Controller c1) {
        lastScene = lastScenes;
        c = c1;
    }
}
