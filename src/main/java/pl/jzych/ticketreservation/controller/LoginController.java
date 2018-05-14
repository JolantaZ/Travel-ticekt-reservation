package pl.jzych.ticketreservation.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import pl.jzych.ticketreservation.Main;
import pl.jzych.ticketreservation.model.User;
import pl.jzych.ticketreservation.service.UserService;


public class LoginController {

	@FXML
	private TextField tf_login;

	@FXML
	private PasswordField pf_password;

	@FXML
	private Button btn_login;

    @FXML
    private Button btn_guest;

    @FXML
    private Button btn_signin;
	


	@FXML
	void enterGuest(MouseEvent event) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("/views/RidesView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
	}
	
	
	static User u1;
	@FXML
	void login(MouseEvent event) throws IOException {

		String login = tf_login.getText();
		String password = pf_password.getText();
		boolean isLogin = userService.login(login, password);
		if (isLogin) {
			u1 = userService.takeDataBylogin(login, password);
			Parent parent = FXMLLoader.load(getClass().getResource("/views/RidesView.fxml"));
			Scene scene = new Scene(parent);
			Main.getPrimaryStage().setScene(scene);

		} else {
			Alert error = new Alert(AlertType.ERROR);
			error.setHeaderText("Error");
			error.setContentText("Enter valid login/password");
			error.setTitle("Wrong login or password");
			error.show();
		}
	}
	
    @FXML
    void sign(MouseEvent event) throws IOException {
    	Parent parent = FXMLLoader.load(getClass().getResource("/views/RegistrationView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }
    
    
    
	private UserService userService;
	
	public void initialize() {
		userService = new UserService();
	}
	

}
