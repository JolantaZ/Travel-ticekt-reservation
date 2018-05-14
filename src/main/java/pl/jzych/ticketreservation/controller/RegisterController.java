package pl.jzych.ticketreservation.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import pl.jzych.ticketreservation.Main;
import pl.jzych.ticketreservation.model.User;
import pl.jzych.ticketreservation.service.RegisterService;

public class RegisterController {

    @FXML
    private Button btn_register;

    @FXML
    private TextField tf_phone;

    @FXML
    private TextField tf_mail;

    @FXML
    private TextField tf_surname;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_pass;

    @FXML
    private TextField tf_login;
    
    @FXML
    private Button btn_login;

    @FXML
    void insertInto(MouseEvent event) {

    	if(fieldIsEmpty()) {
    		showAlertFieldEmpty();
    	}else {
    	RegisterService registerService = new RegisterService();
    	User user = newUserData();
    	registerService.save(user);
    	
    	Alert errorSendMail = new Alert(AlertType.CONFIRMATION);
		errorSendMail.setTitle("You are sign in");
		errorSendMail.setHeaderText("Confirmation");
		errorSendMail.setContentText("Please click in the Log in button and login ");
		errorSendMail.show();
    	}
    }

    private User newUserData() {
    	
    	String login = tf_login.getText();
    	String password = tf_pass.getText();
    	String name = tf_name.getText();
    	String surname = tf_surname.getText();
    	String email = tf_mail.getText();
    	String phone = tf_phone.getText();
    	
    	return new User(login, password, name, surname, email, phone);
    }
    
    @FXML
    void newUserLogin(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }
    
	private boolean fieldIsEmpty() {
		return "".equals(tf_login.getText()) || "".equals(tf_pass.getText()) || "".equals(tf_name.getText())
				|| "".equals(tf_surname.getText()) || "".equals(tf_mail.getText())|| "".equals(tf_phone.getText());
	}
	
	private void showAlertFieldEmpty() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Error");
		alert.setContentText("Some of the fields on the Registration Form are not filled");
		alert.setTitle("Please fill all required fields. ");
		alert.show();
	}
	
	
}






