package controller;

import java.util.Date;

import model.Flight;
import model.Passenger;

public interface FlightControllerInterface {
	
	public String getFlightNumber(Flight flight) ;
	
	public void addPassengerToFlight (Passenger p);
	
	public String getAirline();
	
	public void setAirline(String airline);
	
	public void setFlightNumber(String flightNumber);
	
	public String getDepartsFrom();
	
	public void setDepartsFrom(String departsFrom);
	
	public String getDestination();
	
	public void setDestination(String destination);
	
	public String getAllPassengers(Flight flight);
	
	public void validateFlight(String flightNumber, String takeoffTime,String landingTime, String departsFrom, String destination,String airline);
}
