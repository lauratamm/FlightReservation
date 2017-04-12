package controller;

import model.Passenger;

public interface FlightControllerInterface {
	
	public String getFlightNumber() ;
	
	public void addPassengerToFlight (Passenger p);
	
	public String getAirline();
	
	public void setAirline(String airline);
	
	public void setFlightNumber(String flightNumber);
	
	public String getDepartsFrom();
	
	public void setDepartsFrom(String departsFrom);
	
	public String getDestination();
	
	public void setDestination(String destination);

}
