package controller;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import enumerator.PassengerClass;
import model.Airline;
import model.BusinessClass;
import model.Flight;
import model.Passenger;

public class FlightControllerTest {
	FlightController flightController = new FlightController();
	AirlineController airlineController = new AirlineController();
	
	@Test
	public void testAddPassengerToFlight() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
		Flight flight = new Flight ();
		String flightNumber = "ABC123";
		Airline airline = new Airline("Ryanair");
		String departsFrom = "Edinburgh";
		String destination = "London";
		Date departureTime = df.parse("15/06/2017/15:30") ;
		Date landingTime = df.parse("15/06/2017/18:30") ;
		
		flightController.setAirline(flight,airline);
		flightController.setFlightNumber(flight,flightNumber);		
		flightController.setDepartsFrom(flight,departsFrom);
		flightController.setDestination(flight,destination);
		flightController.setDepartureTime(flight,departureTime);
		flightController.setLandingTime(flight,landingTime);
		
		int intialNoOfPassengers =flight.passengerList.size();
	
		Passenger passenger = new BusinessClass("Ben", "Ten", PassengerClass.BUSINESS);
		
		flight.passengerList.add(passenger);
		
		int expected = intialNoOfPassengers +1;
		int actual = flight.passengerList.size();
		assertTrue(expected == actual);
		
	}

	@Test
	public void testValidateFlight() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFlight() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFlightToList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllFlightNumbers() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindFlight() {
		fail("Not yet implemented");
	}

}
