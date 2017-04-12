package controller;

import model.Booking;
import model.Flight;
import model.Passenger;

public class BookingController implements BookingControllerInterface {
	
	private Passenger passenger;
	private Flight flight;
	
			
	private Booking booking = new Booking(passenger, flight );
	
	public int getBookingRef() {
		return booking.bookingRef;
	}
	public void setBookingRef(int bookingRef) {
		booking.bookingRef = bookingRef;
	}

	public String getFlightNumber() {
		return flight.flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		flight.flightNumber = flightNumber;
	}
	
	
}
