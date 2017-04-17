package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airline implements Model, Serializable {
	public String airlineName;
	public List <Flight> allFlightsForAirline = new ArrayList<Flight>();
	
	public Airline(){};
	
	public Airline (String airlineName){
		this.airlineName=airlineName;
	}
}
	