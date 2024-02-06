package com.example.demo43;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

class TakePassword extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent entryRoot = FXMLLoader.load(getClass().getResource("TakePassword.fxml"));
        Scene entryScene = new Scene(entryRoot);
        stage.setScene(entryScene);
        stage.setTitle("Take Password");
        stage.show();
    }
}

class ImageError extends Application {
    private String fxmlAddress;
    private String stageTitle;

    public ImageError(String fxmlA, String title) {
        fxmlAddress = fxmlA;
        stageTitle = title;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent entryRoot = FXMLLoader.load(getClass().getResource(fxmlAddress));
        Scene entryScene = new Scene(entryRoot);
        stage.setScene(entryScene);
        stage.setTitle(stageTitle);
        stage.show();
    }
}

public class OwnerProfileController {
    @FXML
    private TextArea bio;

    @FXML
    private DatePicker bornDate;

    @FXML
    private TextField country;

    @FXML
    private TextField email;

    @FXML
    private TextField first;

    @FXML
    private TextField id;

    @FXML
    private TextField last;

    // @FXML
    // private TextField location;

    @FXML
    private Label name;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField profileName;

    @FXML
    private TextField website;

    @FXML
    private Text changeID;

    @FXML
    private Text changeProfileName;

    @FXML
    private Text changeBio;

    @FXML
    private Text changeLocation;

    @FXML
    private Text changeWebsite;

    @FXML
    private Text changeFirst;

    @FXML
    private Text changeLast;

    @FXML
    private Text changeEmail;

    @FXML
    private Text changePhone;

    @FXML
    private Text changeCountry;

    @FXML
    private Text changeBornDate;

    @FXML
    private Text wrongChange;

    @FXML
    private ImageView header;

    @FXML
    private ImageView avatar;

    @FXML
    private ImageView followerAvatar;

    @FXML
    private Button followerName;

    @FXML
    private Label followerID;

    @FXML
    private Label followerBio;

    @FXML
    private VBox followingVBox;

    @FXML
    private VBox followerVBox;

    @FXML
    private VBox tweetVBox;

    @FXML
    private VBox ReTweetVBox;
    @FXML
    private VBox blackVBox;

    private static Scene lastScene;

    public static void setLastScene(Scene lastScene) {
        OwnerProfileController.lastScene = lastScene;
    }

    void setContactInfo() {
        Enter.readUsers();
        if (Client.controller.getUser().getProfile().getHeader() != null)
            header.setImage(new Image(Client.controller.getUser().getProfile().getHeader()));
        else
            header.setImage(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\java\\com\\example\\demo14\\sampleHeader.png"));
        if (Client.controller.getUser().getProfile().getAvatar() != null)
            avatar.setImage(new Image(Client.controller.getUser().getProfile().getAvatar()));
        else
            avatar.setImage(new Image("C:\\Users\\Lenovo\\IdeaProjects\\demo14\\src\\main\\java\\com\\example\\demo14\\sampleHeader.png"));
        if (Client.controller.getUser().getProfileName() != null) {
            name.setText(Client.controller.getUser().getProfileName());
            profileName.setText(Client.controller.getUser().getProfileName());
        } else {
            name.setText("User");
            profileName.setText("User");
        }
        id.setText(Client.controller.getUser().getID());
        if (Client.controller.getUser().getProfile().getBio() != null)
            bio.setText(Client.controller.getUser().getProfile().getBio());
        //if (u.getProfile().getLocation() != null)
        //location.setText(u.getProfile().getLocation());
        if (Client.controller.getUser().getProfile().getWebsite() != null)
            website.setText(Client.controller.getUser().getProfile().getWebsite());
        WritingFile.writingUsers();
    }

    void setPersonalInfo() {
        Enter.readUsers();
        first.setText(Client.controller.getUser().getFirstName());
        last.setText(Client.controller.getUser().getLastName());
        if (Client.controller.getUser().getEmail() != null)
            email.setText(Client.controller.getUser().getEmail());
        if (Client.controller.getUser().getPhoneNumber() != null)
            phoneNumber.setText(Client.controller.getUser().getPhoneNumber());
        country.setText(Client.controller.getUser().getCountry());
        bornDate.setValue(LocalDate.parse(Client.controller.getUser().getBornDate()));
        WritingFile.writingUsers();
    }

    void setChangeContactFields() {
        Enter.readUsers();
        changeProfileName.setText("");
        changeID.setText("");
        changeBio.setFill(Color.GREEN);
        changeBio.setText("");
        changeLocation.setText("");
        changeWebsite.setText("");
        WritingFile.writingUsers();

    }

    void setChangePersonalFields() {
        Enter.readUsers();
        changeFirst.setText("");
        changeLast.setText("");
        changeEmail.setFill(Color.GREEN);
        changeEmail.setText("");
        changePhone.setFill(Color.GREEN);
        changePhone.setText("");
        changeCountry.setText("");
        changeBornDate.setText("");
        wrongChange.setFill(Color.GREEN);
        wrongChange.setText("");
        WritingFile.writingUsers();

    }

    @FXML
    void setInfo(ActionEvent e) {
        setChangeContactFields();
        setChangePersonalFields();
        setContactInfo();
        setPersonalInfo();
        WritingFile.writingUsers();
    }

    @FXML
    void setHeader(ActionEvent e) throws IOException {
        Enter.readUsers();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose Header");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fc.showOpenDialog(Client.stage);
        if (selectedFile != null) {
            Client.controller.getUser().getProfile().setHeader(selectedFile.toURI().toString());
            //if (checkHeader == 0) {
            Image selectedImage = new Image(selectedFile.toURI().toString());
            header.setImage(selectedImage);
            //}
            //else {
            //	ImageError ir = new ImageError("HeaderError.fxml", "Invalid Header File");
            //	ir.start(new Stage());
            //}
        }
        WritingFile.writingUsers();
    }

    @FXML
    void setAvatar(ActionEvent e) throws IOException {
        Enter.readUsers();
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose Avatar");
        fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fc.showOpenDialog(Client.stage);
        if (selectedFile != null) {
            Client.controller.getUser().getProfile().setAvatar(selectedFile.toURI().toString());
            //if (checkAvatar == 0) {
            Image selectedImage = new Image(selectedFile.toURI().toString());
            avatar.setImage(selectedImage);
            //}
            //else {
            //ImageError ir = new ImageError("AvatarError.fxml", "Invalid Avatar File");
            //ir.start(new Stage());
            //}
        }
        WritingFile.writingUsers();
    }

    @FXML
    void resetButtonContact(ActionEvent e) {
        setContactInfo();
    }


    @FXML
    void resetButtonPersonal(ActionEvent e) {
        setPersonalInfo();
    }

    @FXML
    void saveButtonContact(ActionEvent e) {
        setChangeContactFields();
        if (!profileName.getText().equals(Client.controller.getUser().getProfileName()) && !profileName.getText().equals("")) {
            Client.controller.getUser().setProfileName(profileName.getText());
            changeProfileName.setText("Changes Saved!");
        }
        if (!id.getText().equals(Client.controller.getUser().getID())) {
            if (Enter.checkID(id.getText()) && !id.getText().equals("")) {
                Client.controller.getUser().setID(id.getText());
                changeID.setText("Changes Saved");
            } else {
                changeID.setFill(Color.RED);
                changeID.setText("*Invalid ID");
            }
        }
        if (!bio.getText().equals(Client.controller.getUser().getProfile().getBio())) {
            if (Client.controller.getUser().getProfile().setBio(bio.getText()) == 0)
                changeBio.setText("Changes Saved!");
            else {
                changeBio.setFill(Color.RED);
                changeBio.setText("*invalid length!");
            }
        }
    	/*if (!location.getText().equals(u.getProfile().getLocation())) {
    		u.getProfile().setLocation(location.getText());
    		changeLocation.setText("Changes Saved!");
    	}*/
        if (!website.getText().equals(Client.controller.getUser().getProfile().getWebsite())) {
            Client.controller.getUser().getProfile().setWebsite(website.getText());
            changeWebsite.setText("Changes Saved!");
        }
        WritingFile.writingUsers();
    }

    @FXML
    void saveButtonPersonal(ActionEvent e) {
        setChangePersonalFields();
        if (!first.getText().equals(Client.controller.getUser().getFirstName()) && !first.getText().equals("")) {
            Client.controller.getUser().setFirstName(first.getText());
            changeFirst.setText("Changes Saved!");
        } else {
            changeFirst.setFill(Color.RED);
            changeFirst.setText("*empty!");
        }
        if (!last.getText().equals(Client.controller.getUser().getLastName()) && !last.getText().equals("")) {
            Client.controller.getUser().setLastName(last.getText());
            changeLast.setText("Changes Saved!");
        } else {
            changeLast.setFill(Color.RED);
            changeLast.setText("*empty!");
        }
        if (!email.getText().equals(Client.controller.getUser().getEmail()) && !email.getText().equals("")) {
            if (Enter.checkEmailPattern(email.getText()) && Enter.checkExistedEmail(email.getText())) {
                if (phoneNumber.getText().equals("")) {
                    if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber())) {
                        Client.controller.getUser().setEmail(email.getText());
                        changeEmail.setText("Changes Saved!");
                        Client.controller.getUser().setPhoneNumber(phoneNumber.getText());
                        changePhone.setText("Changes Saved!");
                    } else {
                        Client.controller.getUser().setEmail(email.getText());
                        changeEmail.setText("Changes Saved!");
                    }
                } else {
                    if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber())) {
                        if (Enter.checkExistedPhoneNumber(phoneNumber.getText()) && Enter.checkPhoneNumberPattern(phoneNumber.getText())) {
                            Client.controller.getUser().setEmail(email.getText());
                            changeEmail.setText("Changes Saved!");
                            Client.controller.getUser().setPhoneNumber(phoneNumber.getText());
                            changePhone.setText("Changes Saved!");
                        } else {
                            Client.controller.getUser().setEmail(email.getText());
                            changeEmail.setText("Changes Saved!");
                            changePhone.setFill(Color.RED);
                            changePhone.setText("*invalid!");
                        }
                    } else {
                        Client.controller.getUser().setEmail(email.getText());
                        changeEmail.setText("Changes Saved!");
                    }
                }
            } else {
                changeEmail.setFill(Color.RED);
                changeEmail.setText("*invalid!");
                if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber()) && !phoneNumber.getText().equals("")) {
                    if (Enter.checkExistedPhoneNumber(phoneNumber.getText()) && Enter.checkPhoneNumberPattern(phoneNumber.getText())) {
                        Client.controller.getUser().setPhoneNumber(phoneNumber.getText());
                        changePhone.setText("Changes Saved!");
                    } else {
                        wrongChange.setFill(Color.RED);
                        wrongChange.setText("*One of E-mail or Phone number must be valid.");
                        changePhone.setFill(Color.RED);
                        changePhone.setText("*invalid!");
                    }
                } else if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber()) && phoneNumber.getText().equals("")) {
                    wrongChange.setFill(Color.RED);
                    wrongChange.setText("*One of E-mail or Phone number must be full and valid.");
                    changePhone.setFill(Color.RED);
                    changePhone.setText("*empty!");
                }
            }
        } else if (email.getText().equals("")) {
            if (!email.getText().equals(Client.controller.getUser().getEmail())) {
                if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber()) && !phoneNumber.getText().equals("")) {
                    if (Enter.checkExistedPhoneNumber(phoneNumber.getText()) && Enter.checkPhoneNumberPattern(phoneNumber.getText())) {
                        Client.controller.getUser().setEmail(null);
                        changeEmail.setText("Changes Saved!");
                        Client.controller.getUser().setPhoneNumber(phoneNumber.getText());
                        changePhone.setText("Changes Saved!");
                    } else {
                        wrongChange.setFill(Color.RED);
                        wrongChange.setText("*One of E-mail or Phone number must be full and valid.");
                        changeEmail.setFill(Color.RED);
                        changeEmail.setText("*empty!");
                        changePhone.setFill(Color.RED);
                        changePhone.setText("*invalid!");
                    }
                } else if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber()) && phoneNumber.getText().equals("")) {
                    wrongChange.setFill(Color.RED);
                    wrongChange.setText("*E-mail and Phone number can't be empty simultaneously.");
                    changeEmail.setFill(Color.RED);
                    changeEmail.setText("*empty!");
                    changePhone.setFill(Color.RED);
                    changePhone.setText("*empty!");
                } else {
                    Client.controller.getUser().setEmail(email.getText());
                    changeEmail.setText("Changes Saved!");
                }
            } else {
                if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber()) && !phoneNumber.getText().equals("")) {
                    if (Enter.checkExistedPhoneNumber(phoneNumber.getText()) && Enter.checkPhoneNumberPattern(phoneNumber.getText())) {
                        Client.controller.getUser().setPhoneNumber(phoneNumber.getText());
                        changePhone.setText("Changed Saved!");
                    } else {
                        wrongChange.setFill(Color.RED);
                        wrongChange.setText("*One of E-mail or Phone number must be full and valid.");
                        changeEmail.setFill(Color.RED);
                        changeEmail.setText("*empty!");
                        changePhone.setFill(Color.RED);
                        changePhone.setText("*invalid!");
                    }
                } else if (!phoneNumber.getText().equals(Client.controller.getUser().getPhoneNumber()) && phoneNumber.getText().equals("")) {
                    wrongChange.setFill(Color.RED);
                    wrongChange.setText("*E-mail and Phone number can't be empty simultaneously.");
                    changeEmail.setFill(Color.RED);
                    changeEmail.setText("*empty!");
                    changePhone.setFill(Color.RED);
                    changePhone.setText("*empty!");
                }
            }
        }
        if (!country.getText().equals(Client.controller.getUser().getCountry()) && !country.getText().equals("")) {
            Client.controller.getUser().setCountry(country.getText());
            changeCountry.setText("Changed Saved!");
        } else {
            changeCountry.setFill(Color.RED);
            changeCountry.setText("*empty!");
        }
        if (!bornDate.getValue().toString().equals(Client.controller.getUser().getBornDate()) && bornDate.getValue() != null) {
            Client.controller.getUser().setBornDate(bornDate.getValue().toString());
            changeBornDate.setText("Changed Saved!");
        } else {
            changeBornDate.setFill(Color.RED);
            changeBornDate.setText("*empty!");
        }
    }

    @FXML
    void changePassword(ActionEvent e) throws IOException {
        TakePassword tp = new TakePassword();
        WritingFile.writingUsers();
        Stage s = new Stage();
        tp.start(s);
    }

    @FXML
    void backButton() throws IOException {
        Client.stage.setScene(lastScene);
        //HomePage h = new HomePage();
       // h.TimeLine();
        WritingFile.writingUsers();
    }


    @FXML
    void onTweetsPressed() {
        deleteExtra();
        HashSet<Tweet> tweets = Client.controller.getAllTweetsOfCurrentUser();
        HomePage h = new HomePage();
        for (Tweet t : tweets) {
            if (!(t instanceof ReTweet)) {
                BorderPane r = h.displayTweet(Client.stage, t, Client.controller);
                tweetVBox.getChildren().add(r);
            }
        }
        Scene s = tweetVBox.getScene();
        Client.stage.setScene(s);
        WritingFile.writingUsers();
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
        HashSet<Tweet> tweets = Client.controller.getAllTweetsOfCurrentUser();
        HomePage h = new HomePage();
        for (Tweet t : tweets) {
            if ((t instanceof ReTweet)) {
                BorderPane r = h.displayTweet(Client.stage, t, Client.controller);
                ReTweetVBox.getChildren().add(r);
            }
        }
        Scene s = ReTweetVBox.getScene();
        Client.stage.setScene(s);
        WritingFile.writingUsers();

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
    void timeLineDisplay() {
        HomePage h = new HomePage();
        h.TimeLine();
    }

    @FXML
    void newTweet() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("OwnerProfile.fxml"));
            Parent s = fxmlLoader.load();
            Scene scene1 = new Scene(s);
            NewTweetView.setLastScene(scene1, Client.controller);
            Parent root = FXMLLoader.load(HelloApplication.class.getResource("newTweetView.fxml"));
            Scene scene = new Scene(root);
            Client.stage.setScene(scene);
        } catch (IOException o) {
            o.printStackTrace();
        }
    }

    @FXML
    void searchDisplay() {
        SearchDisplay d = new SearchDisplay();
        d.show();
    }

    @FXML
    void followingList() {
//		Enter.readUsers();
        ObservableList<Node> children = followingVBox.getChildren();
        deleteUsers(children);
        HashSet<User> following = Client.controller.getCurrentUserFollowings();
        SearchView s = new SearchView();
        for (User u : following) {
            HBox h = s.userShow(u);
            followingVBox.getChildren().addAll(h);
        }
        Scene s1 = followingVBox.getScene();
        Client.stage.setScene(s1);
        WritingFile.writingUsers();
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
//		Enter.readUsers();
        ObservableList<Node> children = followerVBox.getChildren();
        deleteUsers(children);
        HashSet<User> follower = Client.controller.getCurrentUserFollowers();
        SearchView s = new SearchView();
        for (User u : follower) {
            HBox h = s.userShow(u);
            followerVBox.getChildren().addAll(h);
            WritingFile.writingUsers();
        }
        Scene s1 = followerVBox.getScene();
        WritingFile.writingUsers();
        Client.stage.setScene(s1);
    }

    @FXML
    void chooseIran(ActionEvent e) {
        country.setText("Iran");
    }

    @FXML
    void chooseChina(ActionEvent e) {
        country.setText("China");
    }

    @FXML
    void chooseBrazil(ActionEvent e) {
        country.setText("Brazil");
    }

    @FXML
    void chooseGermany(ActionEvent e) {
        country.setText("Germany");
    }

    @FXML
    void chooseItaly(ActionEvent e) {
        country.setText("Italy");
    }

    @FXML
    void chooseEngland(ActionEvent e) {
        country.setText("England");
    }

    @FXML
    void chooseUS(ActionEvent e) {
        country.setText("US");
    }

    @FXML
    void chooseNone(ActionEvent e) {
        country.setText("");
    }

    @FXML
    void blackList() {
        ObservableList<Node> children = blackVBox.getChildren();
        deleteUsers(children);
        HashSet<User> blackList = Client.controller.getCurrentUserBlackList();
        SearchView s = new SearchView();
        for(User u: blackList) {
            HBox h = s.userShow(u);
            blackVBox.getChildren().addAll(h);
            WritingFile.writingUsers();
        }
        Scene s1 = blackVBox.getScene();
        WritingFile.writingUsers();
        Client.stage.setScene(s1);
    }

}

