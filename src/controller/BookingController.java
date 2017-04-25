package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enumerator.PassengerClass;
import model.Airline;
import model.Booking;
import model.Flight;
import model.Passenger;
import view.UserGUI;

public final class BookingController extends AbstractController {

	private static BookingController bookingController = null;
	public  ArrayList<Booking> allBookings = new ArrayList<>();
	public String fileName = "allBookings.data";
	
	private BookingController() {
	}

	public static BookingController getInstance() {
		if (bookingController == null) {
			bookingController = new BookingController();
		}
		return bookingController;
	}

	public ArrayList<Booking> getBookingList() {
		return this.allBookings;
	}
	
	public int validateBooking(String departsFrom, String destination, String flightTime, String firstName,
			String lastName, String passengerClass) {

		System.out.println(FlightController.getInstance().getFlightList().size() + "  in validateBooking");
		// convert string to date
		Date parsedDepartureTime;
		try {
			parsedDepartureTime = FlightController.getInstance().getDateFormatForBooking().parse(flightTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return 1;
		}

		// get enum value for passenger class
		PassengerClass pClass = PassengerClass.valueOf(passengerClass);
		// find flight object that matches the parameters
		Flight bookFlight = FlightController.getInstance().findFlight(departsFrom, destination, parsedDepartureTime);

		// find passenger and check if eligible
		Passenger passenger = PassengerController.getInstance().findPassenger(firstName, lastName);

		if (passenger != null) {
			System.out.println(passenger.getFirstname() + passenger.getLastname() + "  found passenger");
			if (PassengerController.getInstance().isPassengerEligibleToBook(passenger, bookFlight)) {
				addBooking(passenger, bookFlight);
				return 3;
			} else
				return 2;
		} else {

			Passenger newPassenger = PassengerController.getInstance().addPassenger(firstName, lastName, pClass);
			addBooking(newPassenger, bookFlight);
			return 3;
		}

	}

	public void addBooking(Passenger passenger, Flight flight) {
		Booking newBooking = new Booking(passenger, flight);
		passenger.getBookings().add(newBooking);
		System.out.println(passenger.getBookings().size() + "  passenger bookings size");
		flight.getBookingListForFlight().add(newBooking);
		bookingController.allBookings.add(newBooking);
		serialize(this.allBookings, this.fileName);
		serialize(FlightController.getInstance().getFlightList(), this.fileName);
		setChanged();
		notifyObservers();

	}

	public boolean validateBookingRef(String bookingRefString){
		if (!bookingRefString.isEmpty()) {
			System.out.println("not empty");
			int bookingRef = Integer.parseInt(bookingRefString);
			System.out.println(this.getBookingList().size());
			for (Booking tempBooking : getBookingList()) {
				System.out.println(tempBooking.getBookingRef() + "  inside foreach");
				if(tempBooking.getBookingRef() == bookingRef) {
					cancelBooking(tempBooking);
					return true;
				}
			}
		}
		return false;
	}
	
	private void cancelBooking(Booking booking) {
		//remove booking from booking list
		getBookingList().remove(booking);
		
		//re-add passenger to list of all passengers to reflect updated booking list
		PassengerController.getInstance().getPassengerList().remove(booking.getPassenger());
		booking.getPassenger().getBookings().remove(booking);
		PassengerController.getInstance().getPassengerList().add(booking.getPassenger());
		
		//re-add flight to list of all flights to reflect updated list of passengers on the flights
		FlightController.getInstance().getFlightList().remove(booking.getFlight());
		booking.getFlight().getBookingListForFlight().remove(booking.getPassenger());
		FlightController.getInstance().getFlightList().add(booking.getFlight());
		
		//save changes
		serialize(getBookingList(), this.fileName);
		serialize(FlightController.getInstance().getFlightList(), FlightController.getInstance().fileName);
		serialize(PassengerController.getInstance().getPassengerList(), PassengerController.getInstance().fileName);
	}
	
	
}
