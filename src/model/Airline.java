package model;

import java.util.ArrayList;
import java.util.List;

public class Airline implements Model {
	public String airlineName;
	public List <Flight> allFlightsForAirline = new ArrayList<Flight>();
	
	public Airline(){};
	
	public Airline (String airlineName){
		this.airlineName=airlineName;
	}
}
	