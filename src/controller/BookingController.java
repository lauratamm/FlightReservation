package controller;

import java.util.Date;

import model.Booking;
import model.Flight;
import model.Passenger;

public class BookingController extends AbstractController{
	
	private Passenger passenger;
	private Flight flight;
	FlightController flightController = new FlightController();
	PassengerController passengerController = new PassengerController();
	
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
	
	public String validateBooking (String flightDept, String flightDest, Date flightDate, String firstName, String lastName, String passengerClass){
		Flight flight = flightController.findFlight(flightDept, flightDest, flightDate);
		Passenger passenger = passengerController.findPassenger(firstName, lastName);
		if (passenger !=null) {
			if (passengerController.isPassengerEligibleToBook(passenger, flight)) {
				addBooking (passenger, flight);
				return "Booking completed";
			}
			else return "Number of bookings exceeded";
			}
		else {
			System.out.println("new passenger");
			Passenger newPassenger = passengerController.addPassenger(firstName, lastName, passengerClass);
			addBooking(newPassenger, flight);
			return "Booking completed";
		}
		
	}
	

	public void addBooking(Passenger passenger, Flight flight ){
		Booking newBooking = new Booking(passenger, flight);
		passenger.bookings.add(newBooking);
		System.out.println(newBooking.bookingRef);
		System.out.println(passenger.firstname);
		System.out.println(flight.departsFrom);
		flight.passengerList.add(passenger);
		System.out.println("booking added");
		//notifyObservers();
		
	}

}
