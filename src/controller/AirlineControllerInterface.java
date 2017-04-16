package controller;

import java.util.ArrayList;

import model.Airline;
import model.Flight;

public interface AirlineControllerInterface {
	public String getAirlineName();
	public void setAirlineName(String airlineName);
	public void addFlightsToAirline (Flight flight);
	public ArrayList<Airline> getAllAirlines();
	public void validateNotEmpty(String airline);
	
	
}
