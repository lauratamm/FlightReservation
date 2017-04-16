package controller;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import model.Airline;
import model.Flight;

public class AirlineControllerTest extends AirlineController {
	FlightController flightController = new FlightController();
	AirlineController airlineController = new AirlineController();
	
	@Test
	public void testAddFlightsToAirline() throws ParseException {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
		Airline newAirline = new Airline();
		String airlineName = "Ryanair";
		airlineController.setAirlineName(newAirline, airlineName);
		
		int intialNoOfFlights = newAirline.allFlightsForAirline.size();
		
		Flight flight = new Flight ();
		String flightNumber = "ABC123";
		Airline airline = newAirline;
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
		
		airlineController.addFlightsToAirline(newAirline,flight);
		
		int expected = intialNoOfFlights +1;
		int actual = newAirline.allFlightsForAirline.size();
		assertTrue(expected == actual);
		
		
	}

	//@Test
	//public void testGetAllAirlines() {
	//	fail("Not yet implemented");
	//}

	//@Test
	//public void testValidateNotEmpty() {
	
	//}

}
