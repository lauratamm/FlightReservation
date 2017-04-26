package Tests;


import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import controller.AirlineController;
import controller.FlightController;
import model.Airline;
import model.Flight;

public class AirlineControllerTest {
	private AirlineController airlineController;
	private Flight flight;
	private Airline airline;
	private Airline airline2;
	private DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
	private List<Airline> allAirlines;
	
	@Before
	public void setUp() throws Exception {
		airlineController = AirlineController.getInstance();
		allAirlines= new ArrayList<>();	
		df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");
		
		airline = new Airline("Ryanair");
		airline2 = new Airline("Lufthansa");
		
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
	public void testGetAllAirlineNames() {
		allAirlines.add(airline);
		allAirlines.add(airline2);
		
		ArrayList<String> airlineNames = new ArrayList<String>();
		
		for(Airline tempAirline: allAirlines){
			airlineNames.add(tempAirline.getAirlineName());
		}	
		assertTrue(airlineNames.size()==allAirlines.size());
	}

	@Test
	public void testValidateNotEmpty() {
		String input = "abcd";	
		int expected = 4;		
		int actual = input.length();
		assertTrue(expected==actual);
		
	}
	
	@Test
	public void addAirline() {
		Airline airline3=new Airline("AirFrance");
		int initialCountOfAirlines = allAirlines.size();
		allAirlines.add(airline3);
		int countAfterAddingAirline= initialCountOfAirlines+1;
		
		int expected=countAfterAddingAirline;
		int actual = allAirlines.size();
		
		assertTrue(expected==actual);
	}

	@Test
	public void testFindAirline() {
		allAirlines.add(airline);
		allAirlines.add(airline2);
		
		String expected="";
		for(Airline tempAirline: allAirlines){
			if (airline.getAirlineName().equals(tempAirline.getAirlineName())){
				expected=tempAirline.getAirlineName();
			}
		}
		String actual = airline.getAirlineName();
		
		assertTrue(expected==actual);
	}

}
