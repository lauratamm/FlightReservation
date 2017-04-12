package controller;

import model.Flight;
import model.Passenger;

public class FlightController implements FlightControllerInterface{

	private Flight flight;
	
	public void addPassengerToFlight (Passenger p) {
		flight.passengerList.add(p);		
	}
	
	public String getAirline() {
		return flight.airline;
	}

	public void setAirline(String airline) {
		flight.airline = airline;
	}

	
	public void setFlightNumber(String flightNumber) {
		flight.flightNumber = flightNumber;
	}
	public String getDepartsFrom() {
		return flight.departsFrom;
	}
	public void setDepartsFrom(String departsFrom) {
		flight.departsFrom = departsFrom;
	}
	public String getDestination() {
		return flight.destination;
	}
	public void setDestination(String destination) {
		flight.destination = destination;
	}

	@Override
	public String getFlightNumber() {
		return flight.flightNumber;
	}
}
