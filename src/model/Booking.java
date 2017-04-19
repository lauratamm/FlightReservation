package model;

import java.io.Serializable;

public class Booking implements Model, Serializable {
	public int bookingRef;
	public Passenger passenger;
	public Flight flight;
	
	public Booking(Passenger passenger, Flight flight) {
		this.bookingRef= 1;
		this.passenger = passenger;
		this.flight = flight;
		// using passenger and flight objects instead of setting and getting specific variables within constructor  
	};
	
	
}
