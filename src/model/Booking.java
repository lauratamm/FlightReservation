package model;

import java.io.Serializable;

public class Booking implements Model, Serializable {
	private int bookingRef;
	private Passenger passenger;
	private Flight flight;


	public Booking(Passenger passenger, Flight flight) {
		this.bookingRef = 1;
		this.passenger = passenger;
		this.flight = flight;
	}
	

	public int getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(int bookingRef) {
		this.bookingRef = bookingRef;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

}
