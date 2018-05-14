package pl.jzych.ticketreservation.model;


public class RidesFilter {

	private String fromcity;
	private String tocity;
	private String date_r;
	
	
	public RidesFilter(String fromcity, String tocity, String date_r) {
		super();
		this.fromcity = fromcity;
		this.tocity = tocity;
		this.date_r = date_r;
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


	public String getDate_r() {
		return date_r;
	}


	public void setDate_r(String date_r) {
		this.date_r = date_r;
	}
	
	
	

	
	
}
