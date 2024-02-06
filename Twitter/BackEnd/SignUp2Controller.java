package com.example.demo43;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignUp2Controller {
	
	@FXML
	private TextField country;
	
	@FXML
	private DatePicker bornDate;
	
	@FXML
	private Label emptyCountry;
	
	@FXML
	private Label emptyBornDate;
	
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
	void backButton(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("SignUp12.fxml"));
		Scene scene = new Scene(root);
		Client.stage.setScene(scene);
		Client.stage.show();
	}
	
	boolean checkBeforeNext() {
		emptyCountry.setText("");
		emptyBornDate.setText("");
		if (country.getText().equals(""))
			emptyCountry.setText("*This field can't be empty.");
		if (bornDate.getValue() == null)
			emptyBornDate.setText("*This field can't be empty.");
		if (bornDate.getValue() != null && !country.getText().equals("")) {
			Client.controller.getUser().setCountry(country.getText());
			Client.controller.getUser().setBornDate(bornDate.getValue().toString());
			return true;
		}
		return false;
	}
	
	@FXML
	void nextButton(ActionEvent e) throws IOException {
		if (checkBeforeNext()) {
			//Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			//Scene scene = new Scene(root);
			HomePage h =new HomePage();
			System.out.println("ok");
			h.TimeLine();
			System.out.println("bye");
			//Client.stage.setScene(HomePage.timeLineScene);
			//Client.stage.show();
		}
	}
}


