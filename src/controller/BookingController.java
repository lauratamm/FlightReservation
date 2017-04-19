package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import enumerator.PassengerClass;
import model.Booking;
import model.Flight;
import model.Passenger;

public class BookingController extends AbstractController{
	
	
	
	private Passenger passenger;
	private Flight flight;
	FlightController flightController;
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
	
	public boolean validateBooking (String departsFrom, String destination, String flightTime, String firstName, String lastName, String passengerClass, 
			FlightController flightController){
		this.flightController=flightController;
		System.out.println(flightController.allFlights.size() + "  in validateBooking");
		//convert string to date
		Date parsedDepartureTime;
		try {
			parsedDepartureTime = flightController.bookingDateFormat.parse(flightTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		
		//get enum value for passenger class
		PassengerClass pClass = PassengerClass.valueOf(passengerClass);
		//find flight object that matches the parameters
		Flight bookFlight = flightController.findFlight(departsFrom, destination, parsedDepartureTime);
		System.out.println(bookFlight.departsFrom + "find fliight");
		//find passenger and check if eligible
		Passenger passenger = passengerController.findPassenger(firstName, lastName);
		if (passenger !=null) {
			if (passengerController.isPassengerEligibleToBook(passenger, bookFlight)) {
				addBooking (passenger, bookFlight);
				return true;
			}
			else return false;
			}
		else {
			Passenger newPassenger = passengerController.addPassenger(firstName, lastName, pClass);
			addBooking(newPassenger, bookFlight);
			return true;
		}
		
	}
	

	public void addBooking(Passenger passenger, Flight flight ){
		Booking newBooking = new Booking(passenger, flight);
		passenger.bookings.add(newBooking);
		flight.passengerList.add(passenger);
		serialize(flightController.allFlights, "allFlights.data");
		setChanged();
		notifyObservers();
		
	}

}
