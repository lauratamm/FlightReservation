package Tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import controller.BookingController;
import controller.FlightController;
import enumerator.PassengerClass;
import model.Airline;
import model.Booking;
import model.Flight;
import model.Passenger;
import model.StandardClass;

public class BookingControllerTest {
	private Booking booking;
	private Passenger passenger;
	private BookingController bookingController;
	private ArrayList<Booking> allBookings;
	private Airline airline;
	private Flight flight;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
	private DateFormat dateFormatForBooking = new SimpleDateFormat("E dd/MM/yyyy  HH:mm");
	private String bookingRefString="1";
	private String firstName="Joe";
	private String lastName="Smith";
	private String flightTime="Wed 26/04/2017  20:15";
	
	
	@Before
	public void setUp() throws Exception {
		passenger = new StandardClass(firstName, lastName, PassengerClass.STANDARD); 
		booking = new Booking(passenger, flight);
		bookingController = BookingController.getInstance();
		allBookings= new ArrayList<>();			
		airline = new Airline("Ryanair");		
		flight = new Flight();
		
		String flightNumber = "ABC123";
		Airline flightAirline = airline;
		String departsFrom = "Edinburgh";
		String destination = "London";
		Date departureTime = df.parse("15/06/2017/15:30");
		Date landingTime = df.parse("15/06/2017/18:30");
		
		flight.setAirline(flightAirline);
		flight.setFlightNumber(flightNumber);
		flight.setDepartsFrom(departsFrom);
		flight.setDestination(destination);
		flight.setTakeOffTime(departureTime);
		flight.setLandingTime(landingTime);		
	}

	@Test
	public void testValidateBooking() throws ParseException {
		Date parsedDepartureTime = FlightController.getInstance().getDateFormatForBooking().parse(flightTime);
	}

	@Test
	public void testAddBooking() {
		Booking booking2 = new Booking(passenger, flight);
		int initialNumberOfBookings = allBookings.size();	
		allBookings.add(booking2);
		int NumberOfBookingsAfterAdding=initialNumberOfBookings+1;
		
		int actual = allBookings.size();
		int expected = NumberOfBookingsAfterAdding;	
	}

	@Test
	public void testValidateBookingRef() {
		allBookings.add(booking);		
		assertFalse(bookingRefString.isEmpty());
		int bookingRef = Integer.parseInt(bookingRefString);
		
		int actual = -99;
		for (Booking tempBooking: allBookings){
			if (tempBooking.getBookingRef()==bookingRef){
				actual=tempBooking.getBookingRef();
			}
		}
		int expected = bookingRef;
		
		assertTrue(actual==expected);
		
	}

	@Test
	public void cancelBooking() {	
		allBookings.add(booking);	
		int initialNumberOfBookings = allBookings.size();	
		allBookings.remove(booking);
		int NumberOfBookingsAfterRemoving=initialNumberOfBookings-1;
		
		int actual = allBookings.size();
		int expected = NumberOfBookingsAfterRemoving;		
	}
}
