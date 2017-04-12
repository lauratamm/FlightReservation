package controller;

import model.Flight;

public interface AirlineControllerInterface {
	public String getAirlineName();
	public void setAirlineName(String airlineName);
	public void addFlightsToAirline (Flight flight);
}
