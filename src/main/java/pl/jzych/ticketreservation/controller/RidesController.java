package pl.jzych.ticketreservation.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import pl.jzych.ticketreservation.Main;
import pl.jzych.ticketreservation.mail.MailService;
import pl.jzych.ticketreservation.model.Rides;
import pl.jzych.ticketreservation.model.RidesFilter;
import pl.jzych.ticketreservation.service.RideService;


public class RidesController {

	@FXML
	private MenuItem m_close;

	@FXML
	private MenuItem m_about;
	
    @FXML
    private MenuItem m_loginpanel;

	@FXML
	private ComboBox<String> cmb_from;

	@FXML
	private ComboBox<String> cmb_to;

	@FXML
	private DatePicker dp_date;

	@FXML
	private Spinner<Integer> spin_nmb_seats;
	

	@FXML
	private Button btn_search;

	@FXML
	private Button btn_clear;



	@FXML
	private TableView<Rides> table_departures;

	@FXML
	private TableColumn<Rides, LocalDate> col_date;

	@FXML
	private TableColumn<Rides, String> col_from;

	@FXML
	private TableColumn<Rides, String> col_to;

	@FXML
	private TableColumn<Rides, String> col_deptime;

	@FXML
	private TableColumn<Rides, String> col_arrtime;

	@FXML
	private TableColumn<Rides, Double> col_price;

	@FXML
	private TextField tf_name;

	@FXML
	private TextField tf_mail;

	@FXML
	private TextField tf_surname;

	@FXML
	private TextField tf_phone;

	@FXML
	private Button btn_reserve;

	@FXML
	private Button btn_filldata;



	public void initialize() {

		fillFromRides();
		fillToRides();
		setCellValue();

		SpinnerValueFactory<Integer> seatsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 6, 1);
		this.spin_nmb_seats.setValueFactory(seatsValueFactory);

	    
	}


	private void fillFromRides() {
		rideService = new RideService();
		List<String> fromList = rideService.fromCity();

		ObservableList<String> from_cmb = FXCollections.observableArrayList(fromList);
		cmb_from.setItems(from_cmb);
	}

	private void fillToRides() {
		rideService = new RideService();
		List<String> toList = rideService.toCity();

		ObservableList<String> to_cmb = FXCollections.observableArrayList(toList);
		cmb_to.setItems(to_cmb);
	}

	@FXML
	void aboutAction(ActionEvent event) {

		Alert about = new Alert(AlertType.INFORMATION);
		about.setTitle("How to use...");
		about.setHeaderText("Instruction");
		about.setContentText("Instruction for ticket reservation: \nSelect available options from \ndrop down boxes "
				+ "and press Search button. \n\nThe available bus connections \nwill appear in the table.\nIn order to make"
				+ " a reservation, \nfill your personal data and confirm \nthe reservation with the Reserve button.  ");
		about.show();
	}

	@FXML
	void clear(MouseEvent event) {

		tf_mail.clear();
		tf_name.clear();
		tf_phone.clear();
		tf_surname.clear();
		cmb_from.setValue(null);
		cmb_to.setValue(null);
		dp_date.setValue(null);
		SpinnerValueFactory<Integer> nmseats = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 6, 1);
		spin_nmb_seats.setValueFactory(nmseats);
		table_departures.setItems(null);
	}

	@FXML
	void closeAction(ActionEvent event) {

		System.exit(0);
	}
	
    @FXML
    void loginPanel(ActionEvent event) throws IOException {

    	Parent parent = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    }

	
	@FXML
	void fullfillData(MouseEvent event) {

		System.out.println(LoginController.u1);
			
		tf_name.setText(LoginController.u1.getName());
		tf_surname.setText(LoginController.u1.getSurname());
		tf_mail.setText(LoginController.u1.getEmail());
		tf_phone.setText(LoginController.u1.getPhone());
		
	}
	

	@FXML
	void reserve(MouseEvent event) {

		if (fieldIsEmpty()) {
			showAlertFieldEmpty();
		} else {
			boolean isValidEmail = validateMail();
			if (!isValidEmail) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Wrong e-mail address");
				error.setHeaderText("Error");
				error.setContentText(
						"Wrong e-mail address: " + tf_mail.getText() + "\n" + "Please enter proper e-mail address.");
				error.show();
				return;
			}

			MailService mailService = new MailService();
			try {
				mailService.sendMail(tf_mail.getText(), "Reservation confirmation", getFormData());
				Alert errorSendMail = new Alert(AlertType.CONFIRMATION);
				errorSendMail.setTitle("E-mail has been sent");
				errorSendMail.setHeaderText("Confirmation");
				errorSendMail.setContentText("On your e-mail address has been sent Reservation confirmation. \nPlease check your e-mail box.");
				errorSendMail.show();

			} catch (MessagingException e) {
				Alert errorSendMail = new Alert(AlertType.ERROR);
				errorSendMail.setTitle("E-mail has not been sent");
				errorSendMail.setHeaderText("Error");
				errorSendMail.setContentText(e.getMessage());
				errorSendMail.show();
			}
		}

	}

	private boolean fieldIsEmpty() {
		return "".equals(tf_name.getText()) || "".equals(tf_surname.getText()) || "".equals(tf_mail.getText())
				|| "".equals(tf_phone.getText()) || Objects.isNull(cmb_from.getValue())
				|| Objects.isNull(cmb_to.getValue()) || Objects.isNull(dp_date.getValue()); 
	}
	
	
	private boolean searchingEmpty() {
		return  Objects.isNull(cmb_from.getValue())
				|| Objects.isNull(cmb_to.getValue()) || Objects.isNull(dp_date.getValue());
	}

	
	private void showAlertFieldEmpty() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Error");
		alert.setContentText("Some of the fields on the reservation panel are not filled or selected");
		alert.setTitle("Please fill all required fields on the reservation form ");
		alert.show();
	}

	RideService rideService;

	private void setCellValue() {
		col_date.setCellValueFactory(new PropertyValueFactory<>("date_r"));
		col_from.setCellValueFactory(new PropertyValueFactory<>("fromcity"));
		col_to.setCellValueFactory(new PropertyValueFactory<>("tocity"));
		col_deptime.setCellValueFactory(new PropertyValueFactory<>("departure_t"));
		col_arrtime.setCellValueFactory(new PropertyValueFactory<>("arrival_t"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

	}

	@FXML
	public void search(MouseEvent event) throws IOException {

		if(searchingEmpty()) {
			showAlertFieldEmpty();
		}else {
		String fromvalue = cmb_from.getValue();
		String tovalue = cmb_to.getValue();
		String datevalue = dp_date.getValue().toString();

		RidesFilter filter = new RidesFilter(fromvalue, tovalue, datevalue);
		List<Rides> list = rideService.filter(filter);

		ObservableList<Rides> founded = FXCollections.observableArrayList(list);
		table_departures.setItems(null);
		table_departures.setItems(founded);
		}

	}

	private String getFormData() {

		String name = tf_name.getText();
		String surname = tf_surname.getText();
		String email = tf_mail.getText();
		String phone = tf_phone.getText();

		Random random = new Random();
		int nr_res = random.nextInt()+123456789;

		
		String datedep = table_departures.getSelectionModel().getSelectedItem().getDate_r();
		String from = table_departures.getSelectionModel().getSelectedItem().getFromcity();
		String tocity = table_departures.getSelectionModel().getSelectedItem().getTocity();
		String deptime = table_departures.getSelectionModel().getSelectedItem().getDeparture_t();
		String arrivtime = table_departures.getSelectionModel().getSelectedItem().getArrival_t();
		Double price = table_departures.getSelectionModel().getSelectedItem().getPrice();
		Integer seats = (Integer) spin_nmb_seats.getValue();

		return "Ticket reservation summary: \n" + "\nNr rezerwacji: " + nr_res +"\nName: " + name + " \nSurname: " + surname + "\nE-mail: " + email
				+ "\nPhone: " + phone + "\nDate of departure: " + datedep + "\nFrom city: " + from + "\nTo city: "
				+ tocity + "\nDeparture time: " + deptime + "\nArrival time: " + arrivtime + "\nPrice: " + price * seats + " z³"
				+ "\nNumber of seats: " + seats
				+ "\nBank account number to transfer funds for tickets: \n93 2490 0005 8077 2308 5460 5439 ";

	}

	private boolean validateMail() {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tf_mail.getText());
		boolean isValidEmail = matcher.matches();
		return isValidEmail;
	}
}
