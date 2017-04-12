package controller;

import model.Airline;
import model.Flight;

public class AirlineController implements AirlineControllerInterface {
	
	private Airline airline;

	public String getAirlineName() {
		return airline.airlineName;
	}
	public void setAirlineName(String airlineName) {
		airline.airlineName = airlineName;
	}
	@Override
	public void addFlightsToAirline(Flight flight) {
		airline.allFlightsForAirline.add(flight);
		
	}
}
