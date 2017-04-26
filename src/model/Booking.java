package model;

import java.io.Serializable;

import controller.BookingController;
import interfaces.Model;

public class Booking implements Model, Serializable {
	private int bookingRef;
	private Passenger passenger;
	private Flight flight;


	public Booking(Passenger passenger, Flight flight) {
		bookingRef=incrementBookingRef();
		this.passenger = passenger;
		this.flight = flight;
	}
	

	public int getBookingRef() {
		return bookingRef;
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
	
	public int incrementBookingRef(){
		int tempRef=1;
		for(Booking tempBooking: BookingController.getInstance().getBookingList()){
			if(tempBooking.bookingRef<=tempRef){
			tempRef++;
			}		
		}
		return tempRef;
	}
	
}
