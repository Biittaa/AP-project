package com.example.demo43;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HomePage {
    static Scene timeLineScene;

    public void TimeLine() {
        Enter.readUsers();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: white");
        borderPane.setMaxHeight(500);
        borderPane.setMaxWidth(500);
        ToolBar toolBar = new ToolBar();
        Button button1 = new Button("TimeLine");
        button1.setPrefWidth(80);
        button1.setPrefHeight(30);
        button1.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
        Button button2 = new Button("Profile");
        button2.setPrefWidth(80);
        button2.setPrefHeight(30);
        button2.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
        Button button3 = new Button("Search");
        button3.setPrefWidth(80);
        button3.setPrefHeight(30);
        button3.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
        Button button4 = new Button("Direct");
        button4.setPrefWidth(80);
        button4.setPrefHeight(30);
        button4.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
        Button button5 = new Button(" + ");
        button5.setPrefWidth(80);
        button5.setPrefHeight(30);
        button5.setStyle("-fx-background-color: rgba(246,226,6,0.96)");
        toolBar.getItems().addAll(button1, button2, button5, button3, button4);
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: white");
        vbox.setPrefHeight(500);
        vbox.setPrefWidth(500);
       tweetsList.add("hjjjvfjvkfcnbnfkbkfnbnfkntbktngfuuui\nhuuhhuhu");
       tweetsList.add("hellouiuiuihiuouirjfijgifjigjtij\nuuuhuuihuhgygyhgyg\nuyyyt6ytyu\n");
       tweetsList.add("hello\nhuuhugiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii\nguhugugyteufudgfjgjfgnf");
       tweetsList.add("hello\nyhjijjjij\njijiiijijiijrigirjtrt\nhuhuyyrdrfyguhujhyhuftfgvgvgf\n");
       commentList.add("hjhj");
       commentList.add("hjhjllllllllllllllllllllll");
       commentList.add("hjhj\nij\njjjjjjjjjjjjjjjjjjj\nh\nj");
       commentList.add("hjhjpllllllllllllllllllllllllllllllll");
       commentList.add("hjhjhjhhhjhuhuhjsdjnsjfnejdbefjbdbdbvjur");*/
        ArrayList<Tweet> timeLineTweets;
        timeLineTweets = (Client.controller.showTimeLine());
        toolBar.setCenterShape(true);
        borderPane.setBottom(toolBar);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(600);
        scrollPane.setContent(vbox);
        Label timeLine = new Label("TimeLine");
        timeLine.setPrefWidth(borderPane.getPrefWidth());
        timeLine.setAlignment(Pos.CENTER);
        timeLine.setFont(new Font(30));
        timeLine.setMaxWidth(Double.MAX_VALUE);
        timeLine.setPrefHeight(20);
        BorderPane.setAlignment(timeLine, Pos.CENTER);
        borderPane.setTop(timeLine);
        borderPane.getTop().setStyle("-fx-background-color: pink");
        scrollPane.setPrefWidth(600);
        borderPane.setCenter(scrollPane);
        borderPane.getCenter().setStyle("-fx-background-color: white");
        borderPane.setRight(new VBox());
        borderPane.getRight().setStyle("-fx-background-color: white");
        timeLineScene = new Scene(borderPane, 600, 600);
        WritingFile.writingUsers();
        Client.stage.setScene(timeLineScene);
        for (int i = 0; i < timeLineTweets.size(); i++) {
            BorderPane rowPane = new BorderPane();
            rowPane = displayTweet(Client.stage, timeLineTweets.get(i), Client.controller);
            vbox.getChildren().add(rowPane);
        }
        button2.setOnAction(e -> {
            Enter.readUsers();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("OwnerProfile.fxml"));
                OwnerProfileController.setLastScene(button2.getScene());
                Scene scene = new Scene(root);
                WritingFile.writingUsers();
                Client.stage.setScene(scene);
                Client.stage.show();
            } catch (IOException l) {
                l.printStackTrace();
            }
        });
        button3.setOnAction(e -> {
            Enter.readUsers();
            WritingFile.writingUsers();
            SearchDisplay d = new SearchDisplay();
            d.show();
        });
        button4.setOnAction(e -> {
            WritingFile.writingUsers();
            directContactView d = new directContactView();
            d.setLastScene(button4.getScene());
            d.show();
        });
        button5.setOnAction(e -> {
            Enter.readUsers();
            try {
                NewTweetView.setLastScene(timeLineScene, Client.controller);
                WritingFile.writingUsers();
                Parent root = FXMLLoader.load(HelloApplication.class.getResource("newTweetView.fxml"));
                Scene scene = new Scene(root);
                WritingFile.writingUsers();
                Client.stage.setScene(scene);
            } catch (IOException o) {
                o.printStackTrace();
            }
        });

    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        private Button button;
        private Tweet t;
        private Label label;

        public ButtonHandler(Button button, Tweet t, Label label) {
            this.button = button;
            this.t = t;
            this.label = label;
        }

        @Override
        public void handle(ActionEvent actionEvent) {
            String colorString = button.getStyle();
            Enter.readUsers();
            if (!Client.controller.checkIfLiked(t)) {
                button.setStyle("-fx-background-color: red;");
                t.like(Client.controller.getUser());
                resetTweets(t);
                WritingFile.writingUsers();
                label.setText("" + t.getLikes());
            } else {
                t.dislike(Client.controller.getUser());
                button.setStyle("-fx-background-color: white;");
                resetTweets(t);
                WritingFile.writingUsers();
                label.setText("" + t.getLikes());
            }
        }
    }

    public BorderPane displayTweet(Stage stage, Tweet t, Controller c) {
        Enter.readUsers();
        Label first;
        VBox tVBox = new VBox();
        Button b2 = new Button();
        if (t instanceof ReTweet) {
            if (((ReTweet) t).getRetweetedPerson().getID().equals(c.getUser().getID())) {
                first = new Label(" " + "you" + "  Retweeted");
            } else {
                first = new Label(" " + ((ReTweet) t).getRetweetedPerson().getID() + "  Retweeted");
            }
            first.setTextFill(Color.ORANGE);
            first.setFont(new Font(14));
            tVBox.getChildren().addAll(first);
        }
        Region leftRegion = new Region();
        leftRegion.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        BorderPane rowPane = new BorderPane();
        Label labelName = new Label(t.getUser().getFirstName() + " " + t.getUser().getLastName() + "      @" + t.getUser().getID() + " " + t.PassedTime());
        labelName.setFont(new Font(20));
        String avatar = t.getUser().getProfile().getAvatar();
        ImageView imageViewID;
        if (avatar != null) {
           imageViewID = new ImageView(new Image(avatar));
        }
       else {
            imageViewID = new ImageView(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\f7b400.png"));
        }
        imageViewID.setFitWidth(50);
        imageViewID.setFitHeight(50);
        HBox tHBox = new HBox();
        tHBox.getChildren().addAll(imageViewID, labelName);
        tVBox.getChildren().addAll(tHBox);
        ArrayList<String> images = Client.controller.getImages(t);
        if (images.size() != 0) {
            HBox hBox = new HBox();
            hBox.getChildren().addAll(new Label("               "));
            for (String s : images) {
                Image image = new Image(s);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(70);
                imageView.setFitWidth(70);
                hBox.getChildren().addAll(imageView);
            }
            tVBox.getChildren().addAll(hBox);
        }
        TextArea tweetMessage = new TextArea(t.getTweetMessage());
        tweetMessage.setFont(new Font(15));
        tweetMessage.setMaxHeight(43);
        tweetMessage.setEditable(false);
        tweetMessage.setPadding(new Insets(0, 0, 0, 50));
        tweetMessage.setStyle("-fx-background-color: white");
        tVBox.getChildren().addAll(tweetMessage);
        if (t instanceof Quote) {
            VBox v1Box = new VBox();
            HBox h1Box = new HBox();
            Label l = new Label("                ");
            ImageView imageID = new ImageView(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\f7b400.png"));
            imageID.setFitWidth(40);
            imageID.setFitHeight(40);
            Tweet q = ((Quote) t).getTweetOn();
            ArrayList<String> image = Client.controller.getImages(q);
            VBox tV1Box = new VBox();
            if (image.size() != 0) {
                HBox hBox = new HBox();
                hBox.getChildren().addAll(new Label("                 "));
                for (String s : image) {
                    Image image1 = new Image(s);
                    ImageView imageView = new ImageView(image1);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    hBox.getChildren().addAll(imageView);
                }
                tV1Box.getChildren().addAll(hBox);
            }
            Label label = new Label(q.getUser().getFirstName() + " " + q.getUser().getLastName() + "      @" + q.getUser().getID() + " " + q.PassedTime());
            label.setFont(new Font(15));
            TextArea r = new TextArea(q.getTweetMessage());
            r.setMaxHeight(40);
            r.setEditable(false);
            r.setPadding(new Insets(0, 0, 0, 80));
            r.setStyle("-fx-background-color: white");
            h1Box.getChildren().addAll(l, imageID, label);
            v1Box.getChildren().addAll(h1Box, tV1Box, r);
            tVBox.getChildren().addAll(v1Box);
        }
        //BorderPane.setAlignment(tVBox, Pos.CENTER_LEFT);
        rowPane.setCenter(tVBox);
        rowPane.setStyle("-fx-background-color: white");
        ToolBar toolBar2 = new ToolBar();
        toolBar2.setStyle("-fx-background-color: white");
        Image image = new Image("C:\\Users\\Lenovo\\Desktop\\pic\\CommentBubble1.jpg");
        Button b1 = new Button();
        b1.setPrefWidth(50);
        b1.setPrefHeight(40);
        b1.setStyle("-fx-background-color: white");
        ImageView imageView = new ImageView(image);
        b1.setGraphic(imageView);
        imageView.setFitHeight(40);
        imageView.setFitWidth(50);
        b2.setPrefWidth(40);
        b2.setPrefHeight(30);
        if (c.checkIfReTweeted(t)) {
            b2.setStyle("-fx-background-color: rgba(0,196,255,0.99)");
            b2.setDisable(true);
        } else {
            b2.setStyle("-fx-background-color: white");
        }
        Label RetweetCount = new Label("" + t.getRetweets() + " ");
        Image image2 = new Image("C:\\Users\\Lenovo\\Desktop\\pic\\arrow_retweet_icon_143838.png");
        ImageView imageView2 = new ImageView(image2);
        imageView2.setFitHeight(30);
        imageView2.setFitWidth(40);
        b2.setGraphic(imageView2);
        Button b3 = new Button();
        Label likeLabel = new Label("" + t.getLikes() + " ");
        b3.setPrefWidth(50);
        b3.setPrefHeight(43);
        if (!c.checkIfLiked(t)) {
            b3.setStyle("-fx-background-color: white");
        } else {
            b3.setStyle("-fx-background-color: red");

        }
        b3.setText("");
        ImageView imageView1 = new ImageView(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\409001-200.png"));
        b3.setGraphic(imageView1);
        imageView1.setFitHeight(30);
        imageView1.setFitWidth(40);
        Button b4 = new Button();
        b4.setStyle("-fx-background-color: white;");
        ImageView imageView4 = new ImageView(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\y.png"));
        b4.setGraphic(imageView4);
        imageView4.setFitHeight(20);
        imageView4.setFitWidth(20);
        Button b5 = new Button();
        b5.setStyle("-fx-background-color: white;");
        ImageView imageView5 = new ImageView(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\direct.png"));
        b5.setGraphic(imageView5);
        imageView5.setFitHeight(30);
        imageView5.setFitWidth(30);
        b1.setOnAction(e -> {
            ArrayList<Tweet> comments = t.getReplies();
            Button backButton = new Button();
            backButton.setPrefWidth(70);
            backButton.setPrefHeight(40);
            Image image4 = new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\resources\\com\\example\\demo14\\arrow-ios-back.png");
            ImageView imageView8 = new ImageView(image4);
            imageView8.setFitHeight(40);
            imageView8.setFitWidth(70);
            backButton.setGraphic(imageView8);
            ScrollPane scrollPane1 = new ScrollPane();
            VBox vbox2 = new VBox();
            Label commentLabel = new Label();
            commentLabel.setText("Comments");
            commentLabel.setStyle("-fx-background-color: pink");
            commentLabel.setMaxWidth(Double.MAX_VALUE);
            commentLabel.setFont(new Font(30));
            commentLabel.setAlignment(Pos.CENTER);
            vbox2.getChildren().addAll(commentLabel);
            vbox2.getChildren().addAll(backButton);
            for (Tweet comment : comments) {
                BorderPane rowPaneComment = displayTweet(stage, comment, c);
                vbox2.getChildren().addAll(rowPaneComment);
                WritingFile.writingUsers();
            }
            TextField textField = new TextField();
            vbox2.getChildren().addAll(textField);
            textField.setOnAction(l -> {
                String text = textField.getText();
                Label label2 = new Label("comment saved!");
                Tweet t0 = Client.controller.addComment(t, text);
                //resetUserTweet(t);
                BorderPane h = displayTweet(stage, t0, c);
                vbox2.getChildren().add(comments.size() + 1, h);
                vbox2.getChildren().addAll(label2);
                textField.clear();
                WritingFile.writingUsers();
            });
            backButton.setOnAction(l -> {
                HomePage p = new HomePage();
                p.TimeLine();
            });
            WritingFile.writingUsers();
            vbox2.setPrefHeight(500);
            vbox2.setPrefWidth(500);
            scrollPane1.setContent(vbox2);
            Scene scene2 = new Scene(scrollPane1, 500, 500);
            stage.setScene(scene2);
        });
        b2.setOnAction(e -> {
            if (c.checkIfReTweeted(t)) {
                b2.setStyle("-fx-background-color: rgb(255,255,255)");

            } else {
                b2.setStyle("-fx-background-color: rgba(0,196,255,0.99)");
                c.addNewReTweet(t);
                tVBox.getChildren().add(0, new Label("you Retweeted!"));
                RetweetCount.setText("" + t.getRetweets() + " ");
                b2.setDisable(true);
                resetTweets(t);
            }
            WritingFile.writingUsers();

        });
        b4.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("RetweetView.fxml"));
                Scene scene = new Scene(root);
                WritingFile.writingUsers();
                stage.setScene(scene);
                RetweetView.setTweet(t);
                WritingFile.writingUsers();
            } catch (IOException i) {
                i.printStackTrace();
            }
        });
        b5.setOnAction(k -> {
            directView d = new directView();
            Enter.readUsers();
            for(User u : Enter.users){
                if(u.getID().equals(t.getUser().getID())){
                    t.setUser(u);
                    break;
                }
            }
            WritingFile.writingUsers();
            d.setLastScene(b5.getScene());
            d.displayMessagePage(t.getUser());
        });
        b3.setOnAction(new ButtonHandler(b3, t, likeLabel));
        Label commentCount = new Label("" + t.getReplyCount() + " ");
        toolBar2.getItems().addAll(b1, commentCount, b2, RetweetCount, b3, likeLabel, b4, b5);
        rowPane.setBottom(toolBar2);
        return rowPane;
    }


    public static void resetTweets(Tweet t){
        Enter.readUsers();
        for(User u : Enter.users){
            if(u.getID().equals(t.getUser().getID())){
                if(u.getID().equals(Client.controller.getUser().getID())){
                    Controller.setCurrentUser(t.getUser());
                }
                Enter.users.remove(u);
                Enter.users.add(t.getUser());
                break;
            }
        }
    }
}

