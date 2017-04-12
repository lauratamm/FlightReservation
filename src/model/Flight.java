package model;

import java.util.ArrayList;

public class Flight {
	public String flightNumber;
	public String departsFrom;
	public String destination;
	public String airline;
	public String takeOffTime;
	public String landingTime;	
	public ArrayList<Passenger> passengerList= new ArrayList<Passenger>();
	
	public Flight (String flightNumber, String departsFrom, String destination, String airline, String takeOffTime, String landingTime ){ 
		this.flightNumber = flightNumber;
		this.departsFrom= departsFrom;
		this.destination = destination;
		this.airline = airline;
		this.takeOffTime = takeOffTime;
		this.landingTime = landingTime;
		
	}
}
