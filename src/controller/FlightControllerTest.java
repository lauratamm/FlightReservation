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
import model.Booking;
import model.BusinessClass;
import model.Flight;
import model.Passenger;

public class FlightControllerTest {
	FlightController flightController = FlightController.getInstance();
	AirlineController airlineController = AirlineController.getInstance();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");

	@Test
	public void testAddPassengerToFlight() throws ParseException {

		Flight flight = new Flight();
		String flightNumber = "ABC123";
		Airline airline = new Airline("Ryanair");
		String departsFrom = "Edinburgh";
		String destination = "London";
		Date departureTime = df.parse("15/06/2017/15:30");
		Date landingTime = df.parse("15/06/2017/18:30");

		flight.setAirline(airline);
		flight.setFlightNumber(flightNumber);
		flight.setDepartsFrom(departsFrom);
		flight.setDestination(destination);
		flight.setTakeOffTime(departureTime);
		flight.setLandingTime(landingTime);

		int intialNoOfPassengers = flight.getBookingListForFlight().size();

		Passenger passenger = new BusinessClass("Ben", "Ten", PassengerClass.BUSINESS);
		Booking booking = new Booking (passenger, flight);
		
		flight.getBookingListForFlight().add(booking);

		int expected = intialNoOfPassengers + 1;
		int actual = flight.getBookingListForFlight().size();
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
