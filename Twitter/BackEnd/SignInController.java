package com.example.demo43;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignInController {
	@FXML
	private TextField id;
	
	@FXML
	private TextField password;
	
	@FXML
	private Label wrongID;
	
	@FXML
	private Label wrongPass;
	
	@FXML
	void backButton(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Entery2.fxml"));
		Scene scene = new Scene(root);
		Client.stage.setScene(scene);
		Client.stage.show();
	}
	
	@FXML
	void nextButton(ActionEvent e) throws IOException {
		wrongID.setText("");
		wrongPass.setText("");
		if (Enter.signIn(id.getText(), password.getText()) == -1) {
			wrongPass.setText("*Password is incorrect.");
		}
		else if (Enter.signIn(id.getText(), password.getText()) == -2) {
			wrongID.setText("*ID doesn't exist.");
		}
		else {
			Controller.setCurrentUser(Enter.signInResult(id.getText(), password.getText()));
			HomePage h = new HomePage();
			h.TimeLine();
		}
	}
}


