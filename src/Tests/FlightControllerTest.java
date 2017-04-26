package Tests;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.AirlineController;
import controller.FlightController;
import enumerator.PassengerClass;
import model.Airline;
import model.Booking;
import model.BusinessClass;
import model.Flight;
import model.Passenger;

public class FlightControllerTest {
	private FlightController flightController;
	private Flight flight;
	private Airline airline;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
	private List<Flight> allFlights;
	private String flightNumber;
	private Airline flightAirline;
	private String departsFrom;
	private String destination;
	private Date departureTime;
	private Date landingTime;
	private String takeOffTime;
	private String landTime;
	private String airlineName;
	private String date;
	
	@Before
	public void setUp() throws Exception {
		flightController = FlightController.getInstance();
		allFlights= new ArrayList<>();
		
		df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
		
		airline = new Airline("Ryanair");
		
		flight = new Flight();
		flightNumber = "ABC123";
		flightAirline = airline;
		departsFrom = "Edinburgh";
		destination = "London";
		departureTime = df.parse("15/06/2017/15:30");
		landingTime = df.parse("15/06/2017/18:30");
		
		flight.setAirline(flightAirline);
		flight.setFlightNumber(flightNumber);
		flight.setDepartsFrom(departsFrom);
		flight.setDestination(destination);
		flight.setTakeOffTime(departureTime);
		flight.setLandingTime(landingTime);	
	}

	@Test
	public void testValidateFlight() {
		airlineName= airline.getAirlineName();
		flightNumber="123";
		takeOffTime="15:30";
		landTime="15:30";
		date = "12/06/2017";
		 
		assertFalse(flightNumber.isEmpty() || takeOffTime.isEmpty() || landTime.isEmpty() || airlineName.isEmpty() ||date.isEmpty());
		
		String dateAndDepartureTime = date + "/" + takeOffTime;
		String dateAndLandingTime = date + "/" + landTime;
		Date parsedDepartureTime;
		Date parsedLandingTime;
		
		boolean actual=false;
		try {
			parsedDepartureTime = df.parse(dateAndDepartureTime);
			parsedLandingTime = df.parse(dateAndLandingTime);
			actual=true;
		}
		catch (Exception e) {
			actual=false;
		}
		
		boolean expected= true;
		assertTrue(actual==expected);
	}

	@Test
	public void testCreateFlight() throws ParseException {
		Flight flight2=new Flight("ABC123", "HongKong", "NewYork", airline,departureTime,landingTime);
		int initialCountOfFlights = allFlights.size();
		allFlights.add(flight2);
		int countAfterAddingFlight= initialCountOfFlights+1;
		
		int expected=countAfterAddingFlight;
		int actual = allFlights.size();
		
		assertTrue(expected==actual);
	}

	@Test
	public void testFindFlight() {
		allFlights.add(flight);
		String expected="";
		for(Flight tempFlight: allFlights){
			if (flight.getFlightNumber().equals(tempFlight.getFlightNumber())){
				expected=tempFlight.getFlightNumber();
			}
		}
		String actual = flight.getFlightNumber();
		
		assertTrue(expected==actual);
	}

}
