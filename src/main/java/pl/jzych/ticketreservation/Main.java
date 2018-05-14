package pl.jzych.ticketreservation;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Main extends Application {
	private static Stage primaryStage;
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	private static void setPrimaryStage(Stage primaryStage) {
		Main.primaryStage = primaryStage;
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			setPrimaryStage(primaryStage);
			
			Parent parent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bus-ticket Reservation Panel");
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
			Alert errorSendMail = new Alert(AlertType.ERROR);
			errorSendMail.setTitle("ERROR");
			errorSendMail.setHeaderText("B³¹d");
			errorSendMail.setContentText(e.getMessage());
			errorSendMail.show();
		}
		
	
	}

	
	public static void main(String[] args) {
		launch(args);
		
		
	}
}
