package com.example.demo43;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TakePasswordController {
	private User u = Client.controller.getUser();
	@FXML
	private TextField pass;
	
	@FXML
	private Label wrongPass;
	
	@FXML
	void nextButton() throws IOException {
		if (pass.getText().equals(u.getPass())) {
			wrongPass.setText("");
			Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
			Scene scene = new Scene(root);
			Client.stage.setScene(scene);
			Client.stage.show();
		}
		else {
			wrongPass.setText("*Password is not correct.");
		}
	}
}
