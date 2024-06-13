package com.example.demo43;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SearchView {
    @FXML
    Button ID;
    @FXML
    Button hashtag;
    @FXML
    TextField field;


    public void IDButtonPressed(ActionEvent event) {
        ID.setStyle("-fx-background-color: yellow");
        hashtag.setStyle("-fx-background-color: pink");
        //for check
        Enter.readUsers();
        ArrayList<User> users = Enter.users;
        System.out.println(users.size());
        Node source = (Node) event.getSource();
        if (source.getParent().getParent() instanceof VBox) {
            VBox vBox = (VBox) source.getParent().getParent();
            ObservableList<Node> children = vBox.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                Node child = children.get(i);
                if (child instanceof HBox || child instanceof BorderPane) {
                    vBox.getChildren().remove(child);
                } else {
                    break;
                }
            }
            String t = field.getText();
            for (int i = 0; i < users.size(); i++) {
                if (!users.get(i).toString().toLowerCase().contains(t.toLowerCase())) {
                    continue;
                }
                User u = users.get(i);
                HBox hBox = userShow(u);
                vBox.getChildren().addAll(hBox);
            }
        }
        WritingFile.writingUsers();
    }

    public void hashtagButtonPressed(ActionEvent event) {
        Enter.readUsers();
        hashtag.setStyle("-fx-background-color: yellow");
        ID.setStyle("-fx-background-color: orange");
        Node source = (Node) event.getSource();
        if (source.getParent().getParent() instanceof VBox) {
            ObservableList<Node> children;
            VBox vBox2 = (VBox) source.getParent().getParent();
            children = vBox2.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                Node child = children.get(i);
                if (child instanceof HBox || child instanceof BorderPane) {
                    vBox2.getChildren().remove(child);
                } else {
                    break;
                }
            }
            String text = field.getText();
            ArrayList<Tweet> tweets = Client.controller.searchedHashtag(text);
            HomePage fakeClient = new HomePage();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = (Scene) ((Node) event.getSource()).getScene();
            for (Tweet t : tweets) {
                if (t.getTweetMessage().toLowerCase().contains(text.toLowerCase())) {
                    System.out.println("1");
                    BorderPane rowPane = fakeClient.displayTweet(stage, t,Client.controller);
                    vBox2.getChildren().addAll(rowPane);
                }
            }
            stage.setScene(scene);
        }
    }

    public void trendButtonPressed() {
        readingHashtag.read();
        VBox v = new VBox();
        Button back = new Button("back");
        back.setPrefHeight(20);
        back.setPrefWidth(20);
        v.getChildren().addAll(back);
        back.setOnAction(e->{
            Client.stage.setScene(ID.getScene());
        });
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<String, Number> l = new LineChart<>(xAxis, yAxis);
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ArrayList<Hashtag> chart = Hashtag.chart();
        System.out.println(Hashtag.getHashtags().size());
        String str="";
        int i=0;
        for (Hashtag h : chart) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (LocalDateTime p = time.minusDays(10); p.isBefore(time) || p.equals(time); p = p.plusDays(1)) {
                series.getData().add(new XYChart.Data<>(f.format(p), h.percent(p)));
            }
            i++;
            System.out.println(i);
            l.getData().add(series);
            str= str+"\n"+"  "+h.getName();
        }
        Label n = new Label(str);
        n.setFont(new Font(15));
        v.getChildren().addAll(l);
        v.getChildren().addAll(n);
        Client.stage.setScene(new Scene(v));
    }

    public HBox userShow(User u) {
        HBox hBox = new HBox();
        ImageView imageView;
        String avatar = u.getProfile().getAvatar();
        if (avatar != null) {
            imageView = new ImageView(new Image(avatar));
        }
        else {
            imageView = new ImageView(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\f7b400.png"));
        }
        Label l = new Label("   " + u.getFirstName() + " " + u.getLastName() + "\n" + "   @" + u.getID() + "\n" + "   "+u.getProfile().getBio());
        Button b;
        if (Controller.getCurrentUser().getID().equals(u.getID())) {
            b = new Button("  you  ");
            b.setDisable(true);
        } else if (Controller.getCurrentUser().getAbility().checkIfFollowing(u)) {
            b = new Button("Following");
        } else {
            b = new Button("Follow");
        }
        imageView.setOnMouseClicked(e -> {
            OthersProfileController o = new OthersProfileController();
            OthersProfileController.setU(u,imageView.getScene());
            o.show();

        });
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        hBox.getChildren().addAll(imageView);
        hBox.getChildren().addAll(l);
        b.setStyle("-fx-background-color: orange");
        b.setOnAction(e -> {
            if (b.getText().equals("Follow")) {
               Client.controller.follow(u);
                b.setText("Following");
                WritingFile.writingUsers();
            } else if (b.getText().equals("Following")) {
                b.setText("Follow");
                Client.controller.unfollow(u);
                WritingFile.writingUsers();
            }
            WritingFile.writingUsers();
        });
        hBox.getChildren().addAll(b);
        hBox.setMargin(b, new Insets(0, 0, 0, 100));
        return hBox;
    }

}
