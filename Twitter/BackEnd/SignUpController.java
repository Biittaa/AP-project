package com.example.demo43;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController {
	@FXML
	private TextField first;
	
	@FXML
	private TextField last; 
	
	@FXML
	private TextField id;
	
	@FXML
	private TextField eOrP;
	
	@FXML
	private TextField pass;
	
	@FXML
	private TextField repass;
	
	@FXML
	private Label emptyFirst;
	
	@FXML
	private Label emptyLast;
	
	@FXML
	private Label wrongID;
	
	@FXML
	private Label wrongEOrP;
	
	@FXML
	private Label wrongEmailFormat;
	
	@FXML
	private Label wrongPass;
	
	@FXML
	private Label wrongPassFormat;
	
	@FXML
	private Label wrongRepass;

	@FXML
	void backButton(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Entery2.fxml"));
		Scene scene = new Scene(root);
		Client.stage.setScene(scene);
		Client.stage.show();
	}
	
	void checkName() {
		if (first.getText().equals(""))
			emptyFirst.setText("*This field can't be empty.");
		if (last.getText().equals(""))
			emptyLast.setText("*This field can't be empty.");
	}
	
	void checkID() {
		if (id.getText().equals(""))
			wrongID.setText("*This field can't be empty.");
		if (!Enter.checkID(id.getText()))
			wrongID.setText("*ID is repetitious.");
	}
	
	void checkEOrP() { 
		String emailOrPhone = eOrP.getText();
		if (emailOrPhone.equals(""))
			wrongEOrP.setText("*This field can't be empty.");
		else {
			int numberCounter = 0;
			for (int i = 0; i < emailOrPhone.length(); i++) 
				if (emailOrPhone.charAt(i) > 47 && emailOrPhone.charAt(i) < 58)
					numberCounter++;
			if (numberCounter == emailOrPhone.length()) {
				if (!Enter.checkExistedPhoneNumber(emailOrPhone))
					wrongEOrP.setText("*Phone number is repetitious.");
			}
			else {
				if (!Enter.checkExistedEmail(emailOrPhone))
					wrongEOrP.setText("*Email is repetitious.");
				if (!Enter.checkEmailPattern(emailOrPhone)) {
					if (wrongEOrP.getText().equals(""))
						wrongEOrP.setText("*Email format is incorrect.");
					else
						wrongEmailFormat.setText("*Email format is incorrect.");
				}
			}
		}
	}
	
	void checkPass() {
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
	
	boolean checkBeforeNext() {
		Enter.readUsers();
		emptyFirst.setText("");
		emptyLast.setText("");
		wrongID.setText("");
		wrongEOrP.setText("");
		wrongEmailFormat.setText("");
		wrongPass.setText("");
		wrongPassFormat.setText("");
		wrongRepass.setText("");
		checkName();
		checkID();
		checkEOrP();
		checkPass();
		if (emptyFirst.getText().equals("") && emptyLast.getText().equals("") && wrongID.getText().equals("") && wrongEOrP.getText().equals("") && wrongEmailFormat.getText().equals("") && wrongPass.getText().equals("") && wrongPassFormat.getText().equals("") && wrongRepass.getText().equals("")) {
			Client.controller.signUp(id.getText(), first.getText(), last.getText(), eOrP.getText(), pass.getText());
			WritingFile.writingUsers();
			return true;
		}
		return false;
	}
	
	@FXML
	void nextButton(ActionEvent e) throws IOException {
		if (checkBeforeNext()) {
			Parent root = FXMLLoader.load(getClass().getResource("SignUp22.fxml"));
			Scene scene = new Scene(root);
			Client.stage.setScene(scene);
			Client.stage.show();
		}
	}
}



