package pl.jzych.ticketreservation.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Rides {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ride;
	
	public String fromcity;
	
	public String tocity;
	
	private String departure_t;
	
	private Double price;
	
	private String arrival_t;
	
	public String date_r;

	public Rides() {
		super();
	}

	

	public Rides(String fromcity, String tocity, String departure_t, Double price, String arrival_t, String date_r) {
		super();
		this.fromcity = fromcity;
		this.tocity = tocity;
		this.departure_t = departure_t;
		this.price = price;
		this.arrival_t = arrival_t;
		this.date_r = date_r;
	}



	public Rides(Long id_ride, String fromcity, String tocity, String departure_t, Double price, String arrival_t,
			String date_r) {
		super();
		this.id_ride = id_ride;
		this.fromcity = fromcity;
		this.tocity = tocity;
		this.departure_t = departure_t;
		this.price = price;
		this.arrival_t = arrival_t;
		this.date_r = date_r;
	}

	


	public Long getId_ride() {
		return id_ride;
	}



	public void setId_ride(Long id_ride) {
		this.id_ride = id_ride;
	}



	public String getFromcity() {
		return fromcity;
	}



	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}



	public String getTocity() {
		return tocity;
	}



	public void setTocity(String tocity) {
		this.tocity = tocity;
	}



	public String getDeparture_t() {
		return departure_t;
	}



	public void setDeparture_t(String departure_t) {
		this.departure_t = departure_t;
	}



	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getArrival_t() {
		return arrival_t;
	}



	public void setArrival_t(String arrival_t) {
		this.arrival_t = arrival_t;
	}



	public String getDate_r() {
		return date_r;
	}



	public void setDate_r(String date_r) {
		this.date_r = date_r;
	}



	@Override
	public String toString() {
		return "Rides [id_ride=" + id_ride + ", fromcity=" + fromcity + ", tocity=" + tocity + ", departure_t="
				+ departure_t + ", price=" + price + ", arrival_t=" + arrival_t + ", date_r=" + date_r + "]";
	}



}
