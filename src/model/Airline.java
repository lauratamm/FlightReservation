package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airline implements Model, Serializable {
	private String airlineName;
	private List <Flight> allFlightsForAirline = new ArrayList<Flight>();
	
	public Airline(){};
	
	public Airline (String airlineName){
		this.airlineName=airlineName;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public List<Flight> getAllFlightsForAirline() {
		return allFlightsForAirline;
	}
}
	