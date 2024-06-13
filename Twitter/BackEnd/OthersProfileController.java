package com.example.demo43;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashSet;

public class OthersProfileController {
    private static User u;
    private static Scene lastScene;
    @FXML
    private ImageView avatar;

    @FXML
    private TextArea bio;


    @FXML
    private Tab contactInfoTab;

    @FXML
    private ImageView header;

    @FXML
    private TextField id;

    @FXML
    private Label name;

    @FXML
    private TextField website;
    @FXML
    private VBox followingVBox;
    @FXML
    private VBox followerVBox;
    @FXML
    private VBox ReTweetVBox;
    @FXML
    private VBox tweetVBox;
    @FXML
    private Button block;

    @FXML
    private Button follow;

    void setContactInfo() {
        if (u.getProfile().getHeader() != null)
            header.setImage(new Image(u.getProfile().getHeader()));
        else
            header.setImage(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo43\\src\\main\\java\\com\\example\\demo43\\sampleHeader.png"));
        if (u.getProfile().getAvatar() != null)
            avatar.setImage(new Image(u.getProfile().getAvatar()));
        else
            avatar.setImage(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo43\\src\\main\\java\\com\\example\\demo43\\sampleAvatar.png"));
        if (u.getProfileName() != null) {
            name.setText(u.getProfileName());
        } else {
            name.setText("User");
        }
        id.setText(u.getID());
        if (u.getProfile().getBio() != null)
            bio.setText(u.getProfile().getBio());
        
        if (u.getProfile().getWebsite() != null)
            website.setText(u.getProfile().getWebsite());
        if (Client.controller.getUser().getAbility().checkIfBlocked(u)) {
            block.setText("Unblock");
            follow.setDisable(true);
        } else {
            if (Client.controller.getUser().getAbility().checkIfFollowing(u)) {
                follow.setText("Unfollow");
                block.setDisable(true);
            } else {
                follow.setText("Follow");
                follow.setDisable(false);
                block.setText("Block");
                block.setDisable(false);
            }
        }
    }

    public void show() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("OthersProfile.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Client.stage.setScene(scene);
        } catch (IOException p) {
            p.printStackTrace();
        }

    }

    public static void setU(User u, Scene lastScene) {
        OthersProfileController.u = u;
        OthersProfileController.lastScene = lastScene;
    }

    @FXML
    void recieveData(ActionEvent e) {
        Node preNode = (Node) e.getSource();
        Stage preStage = (Stage) preNode.getScene().getWindow();
        String id = (String) preStage.getUserData();
        setContactInfo();
    }

    @FXML
    void onBackButton() {
        Client.stage.setScene(lastScene);
        WritingFile.writingUsers();
    }

    @FXML
    void followingList() {
        ObservableList<Node> children = followingVBox.getChildren();
        deleteUsers(children);
        HashSet<User> following = Client.controller.getFollowings(u);
        SearchView s = new SearchView();
        for (User u : following) {
            HBox h = s.userShow(u);
            followingVBox.getChildren().addAll(h);
        }
        Scene s1 = followingVBox.getScene();
        Client.stage.setScene(s1);
    }

    void deleteUsers(ObservableList<Node> children) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof HBox) {
                children.remove(children.get(i));
                i--;
            }
        }
    }

    @FXML
    void followerList() {
        ObservableList<Node> children = followerVBox.getChildren();
        deleteUsers(children);
        HashSet<User> follower = Client.controller.getFollowers(u);
        SearchView s = new SearchView();
        for (User u : follower) {
            HBox h = s.userShow(u);
            followerVBox.getChildren().addAll(h);
        }
        Scene s1 = followerVBox.getScene();
        Client.stage.setScene(s1);
    }

    @FXML
    void onTweetsPressed() {
        deleteExtra();
        HashSet<Tweet> tweets = Client.controller.getTweets(u);
        HomePage h = new HomePage();
        for (Tweet t : tweets) {
            if (!(t instanceof ReTweet)) {
                BorderPane r = h.displayTweet(Client.stage, t, Client.controller);
                tweetVBox.getChildren().add(r);
            }
        }
        Scene s = tweetVBox.getScene();
        Client.stage.setScene(s);
    }

    @FXML
    void onReTweetsPressed() {
        ObservableList<Node> children = ReTweetVBox.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof BorderPane) {
                children.remove(children.get(i));
                i--;
            }
        }
        HashSet<Tweet> tweets = Client.controller.getTweets(u);
        HomePage h = new HomePage();
        for (Tweet t : tweets) {
            if ((t instanceof ReTweet)) {
                BorderPane r = h.displayTweet(Client.stage, t, Client.controller);
                ReTweetVBox.getChildren().add(r);
            }
        }
        Scene s = ReTweetVBox.getScene();
        Client.stage.setScene(s);

    }

    void deleteExtra() {
        ObservableList<Node> children = tweetVBox.getChildren();
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i) instanceof BorderPane) {
                children.remove(children.get(i));
                i--;
            }
        }
    }

    @FXML
    void block() {
        if (block.getText().equals("Block")) {
            block.setText("Unblock");
            Client.controller.block(u);
        } else {
            block.setText("Block");
            Client.controller.unblock(u);
        }
        WritingFile.writingUsers();
    }

    @FXML
    void follow() {
        if (follow.getText().equals("Follow")) {
            follow.setText("Unfollow");
            Client.controller.follow(u);
        } else {
            follow.setText("Follow");
            Client.controller.unfollow(u);
        }
        WritingFile.writingUsers();
    }


}


