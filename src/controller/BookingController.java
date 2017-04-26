package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import enumerator.PassengerClass;
import model.Booking;
import model.Flight;
import model.Passenger;

public final class BookingController extends AbstractController {

	private static BookingController bookingController = null;
	private  ArrayList<Booking> allBookings = new ArrayList<>();
	public String fileName = "allBookings.data";

	private BookingController() {}
	
	//singleton
	public static BookingController getInstance() {
		if (bookingController == null) {
			bookingController = new BookingController();
		}
		return bookingController;
	}

	public ArrayList<Booking> getBookingList() {

		return this.allBookings;
	}
	
	//validate inputs
	public int validateBooking(String departsFrom, String destination, String flightTime, String firstName,
		String lastName, String passengerClass) {
		// convert string to date
		Date parsedDepartureTime;
		PassengerClass pClass;
		Flight bookFlight;
		
		try {
			parsedDepartureTime = FlightController.getInstance().getDateFormatForBooking().parse(flightTime);
		} catch (ParseException e) {
			return 1;
		}
		
		try {
		// get enum value for passsdfdsenger class
		pClass = PassengerClass.valueOf(passengerClass);
		// find flight object that matches the parameters
		bookFlight = FlightController.getInstance().findFlight(departsFrom, destination, parsedDepartureTime);
		}
		catch (Exception e){
			return 5;
		}
		// find passenger and check if eligible
		Passenger passenger = PassengerController.getInstance().findPassenger(firstName, lastName, pClass);

		if (passenger != null) {
			if (PassengerController.getInstance().isPassengerEligibleToBook(passenger, bookFlight)) {
				addBooking(passenger, bookFlight);
				return 3;
			} else
				return 2;
		} 
		else {

			Passenger newPassenger = PassengerController.getInstance().addPassenger(firstName, lastName, pClass);
			addBooking(newPassenger, bookFlight);
			return 3;
		}
	}
	
	//add booking
	public void addBooking(Passenger passenger, Flight flight) {
		Booking newBooking = new Booking(passenger, flight);
		this.allBookings.add(newBooking);
		serialize(this.allBookings, this.fileName);
		setChanged();
		notifyObservers();
	}

	//validate booking by reference 
	public boolean validateBookingRef(String bookingRefString){
		if (!bookingRefString.isEmpty()) {
			int bookingRef = Integer.parseInt(bookingRefString);
			for (Booking tempBooking : getBookingList()) {
				if(tempBooking.getBookingRef() == bookingRef) {
					cancelBooking(tempBooking);
					return true;
				}
			}
		}
		return false;
	}
	
	//cancel booking
	private void cancelBooking(Booking booking) {
		this.allBookings.remove(booking);
		serialize(getBookingList(), this.fileName);
	}
}
