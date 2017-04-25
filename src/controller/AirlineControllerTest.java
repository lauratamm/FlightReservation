package controller;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Airline;
import model.Flight;

public class AirlineControllerTest {

	private FlightController flightController;
	private AirlineController airlineController;
	private Flight flight;
	private Airline airline;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
	
	@Before
	public void setUp() throws Exception {
		airlineController = AirlineController.getInstance();
		flightController = FlightController.getInstance();
		
		df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
		
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
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFlightsToAirline() throws ParseException {
		
		int initialNoOfFlights = airline.getAllFlightsForAirline().size();

		airlineController.addFlightsToAirline(airline, flight);

		int expected = initialNoOfFlights + 1;
		int actual = airline.getAllFlightsForAirline().size();
		assertTrue(expected == actual);

	}

	@Test
	public void testGetAllAirlines() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllAirlineNames() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateNotEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAirline() {
		fail("Not yet implemented");
	}

}
