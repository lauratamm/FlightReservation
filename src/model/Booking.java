package model;

import java.util.Random;

public class Booking {
	public int bookingRef;
	public Passenger passenger;
	public Flight flight;
	
	public Booking(Passenger passenger, Flight flight) {
		this.bookingRef= new Random().nextInt(1000);
		this.passenger = passenger;
		this.flight = flight;
		// using passenger and flight objects instead of setting and getting specific variables within constructor  
	};
	
	
}
