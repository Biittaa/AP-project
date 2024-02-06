package com.example.demo43;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class EnteryController {
	
	@FXML
	void signIn(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignIn2.fxml"));
		Scene scene = new Scene(root);
		Client.stage.setScene(scene);
		Client.stage.show();
	}
	
	@FXML
	void signUp(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUp12.fxml"));
		Scene scene = new Scene(root);
		Client.stage.setScene(scene);
		Client.stage.show();
	}
}



