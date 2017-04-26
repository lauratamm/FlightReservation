package Tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import controller.BookingController;
import enumerator.PassengerClass;
import model.Airline;
import model.Booking;
import model.BusinessClass;
import model.Flight;
import model.Passenger;
import model.StandardClass;

public class PassengerControllerTest {
	private Booking booking;
	private Passenger passenger;
	private BookingController bookingController;
	private ArrayList<Passenger> allPassengers;
	private ArrayList<Booking> allBookings;
	private Airline airline;
	private Flight flight;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
	private String bookingRefString="1";
	private String firstName="Joe";
	private String lastName="Smith";
	private Passenger passenger2;
	@Before
	public void setUp() throws Exception {
		passenger = new StandardClass(firstName, lastName, PassengerClass.STANDARD); 
		passenger2 = new StandardClass(firstName, lastName, PassengerClass.STANDARD); 
		bookingController = BookingController.getInstance();
		allPassengers= new ArrayList<>();	
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
		
		booking = new Booking(passenger, flight);
	}

	@Test
	public void testFindPassenger() {
		allPassengers.add(passenger);
		allPassengers.add(passenger2);
		PassengerClass pClass = PassengerClass.STANDARD;

		String expected="";
		for(Passenger tempPassenger: allPassengers){
			if ( passenger.getFirstname().equals(tempPassenger.getFirstname()) && passenger.getLastname().equals(tempPassenger.getLastname()) && 
					pClass.equals(tempPassenger.getPassengerClass())){
				expected=tempPassenger.getFirstname();
			}
		}
		String actual = passenger.getFirstname();
		
		assertTrue(expected==actual);
	}

	@Test
	public void testIsPassengerEligibleToBook() {
		allBookings.add(booking);
		int bookingsPerAirline=0;
		for (Booking tempBooking: allBookings) {			
			if (tempBooking.getPassenger().getFirstname().equals(passenger.getFirstname()) &&
				tempBooking.getPassenger().getLastname().equals(passenger.getLastname()) &&
				tempBooking.getFlight().getAirline().getAirlineName().equals(airline.getAirlineName())) {
				bookingsPerAirline++;
				}
			}
		
		boolean eligible = false;
		if (passenger instanceof BusinessClass ){
			if ( bookingsPerAirline<4 )	{
			eligible=true;
				}
		}
		else{ //if passenger is an instance of StandardClass
			if ( bookingsPerAirline<2 ){
			eligible=true;
			}
		}
		
		boolean expected = true;
		boolean actual = eligible;
		assertTrue(actual==expected);
	}

	@Test
	public void testAddPassenger() {
		passenger2 = new BusinessClass("Jill","Ball", PassengerClass.BUSINESS);
		int initialNumberOfBookings = allBookings.size();	
		allPassengers.add(passenger2);
		int NumberOfBookingsAfterAdding=initialNumberOfBookings+1;
		
		int actual = allPassengers.size();
		int expected = NumberOfBookingsAfterAdding;	
	}

}
