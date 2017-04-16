package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Flight implements Model {
	public String flightNumber;
	public String departsFrom;
	public String destination;
	public Airline airline;
	public Date takeOffTime;
	public Date landingTime;
	
	public ArrayList<Passenger> passengerList= new ArrayList<Passenger>();

	public Flight (){};
	
	public Flight (String flightNumber, String departsFrom, String destination, Airline airline, Date takeOffTime, Date landingTime ){ 
		this.airline=airline;
		this.flightNumber = flightNumber;
		this.departsFrom= departsFrom;
		this.destination = destination;
		this.takeOffTime = takeOffTime;
		this.landingTime = landingTime;
		
	}
}
