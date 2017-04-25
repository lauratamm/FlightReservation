package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Passenger implements Model, Serializable {
	private String firstname;
	private String lastname;
	private List<Booking> bookings = new ArrayList<Booking>();
	
	public Passenger (String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	
}
