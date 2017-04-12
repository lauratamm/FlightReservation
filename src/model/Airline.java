package model;

import java.util.ArrayList;

public class Airline {
	public String airlineName;
	public ArrayList <Flight> allFlightsForAirline = new ArrayList<Flight>();
	
	public Airline (String airlineName){
		this.airlineName=airlineName;
	};
}
