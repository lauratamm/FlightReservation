package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Passenger implements Model, Serializable {
	public String firstname;
	public String lastname;
	public List<Booking> bookings = new ArrayList<Booking>();
	
	public Passenger (String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}
}
