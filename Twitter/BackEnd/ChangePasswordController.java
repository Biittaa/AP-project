package com.example.demo43;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChangePasswordController {
	@FXML
	private TextField pass;
	
	@FXML
	private TextField repass;
	
	@FXML
	private Label wrongPass;
	
	@FXML
	private Label wrongRepass;
	
	@FXML
	private Label wrongPassFormat;
	
	@FXML
	void discard(ActionEvent e) throws IOException {
		Parent entryRoot = FXMLLoader.load(getClass().getResource("OwnerProfile.fxml"));
        Scene entryScene = new Scene(entryRoot);
        Client.stage.setScene(entryScene);
        Client.stage.show();
	}
	
	void checkBeforeSave() {
		String password = pass.getText();
		String repeatPass = repass.getText();
		if (repeatPass.equals(""))
			wrongRepass.setText("*This field can't be empty.");
		if (password.equals(""))
			wrongPass.setText("*This field can't be empty.");
		else {
			if (!Enter.checkPasswordLength(password))
				wrongPass.setText("*Password has invalid length.");
			if (!Enter.checkPasswordAlphabet(password)) {
				if (wrongPass.getText().equals(""))
					wrongPass.setText("*Password has invalid format.");
				else
					wrongPassFormat.setText("*Password has invalid format.");
			}
			if (!password.equals(repass.getText()))
				wrongRepass.setText("*It's not equal to your password.");
		}
	}
	
	@FXML
	void save(ActionEvent e) throws IOException {
		wrongPass.setText("");
		wrongPassFormat.setText("");
		wrongRepass.setText("");
		checkBeforeSave();
		if (wrongPass.getText().equals("") && wrongPassFormat.getText().equals("") && wrongRepass.equals("")) {
			Client.controller.getUser().setPassword(pass.getText());
			Parent entryRoot = FXMLLoader.load(getClass().getResource("OwnerProfile.fxml"));
			Scene entryScene = new Scene(entryRoot);
			Client.stage.setScene(entryScene);
        	Client.stage.show();
		}
	}
}
