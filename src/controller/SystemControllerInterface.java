package controller;

import java.util.ArrayList;

import model.Airline;
import model.Flight;

public interface SystemControllerInterface {

	public void addFlight(Flight flight);
	
	public void addAirline(Airline airline);
	
	public String displayAllFlights();
	
	public String displayAllAirlines();
}
