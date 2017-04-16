package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Flight implements Model {
	public String flightNumber;
	public String departsFrom;
	public String destination;
	public String airline;
	public Date takeOffTime;
	public Date landingTime;
	public String timePattern = "dd/MM/yyyy/hh:mm";
	public SimpleDateFormat format = new SimpleDateFormat(timePattern);
	public ArrayList<Passenger> passengerList= new ArrayList<Passenger>();

	public Flight (){};
	
	public Flight (String flightNumber, String departsFrom, String destination, String airline, Date takeOffTime, Date landingTime ){ 
		this.flightNumber = flightNumber;
		this.departsFrom= departsFrom;
		this.destination = destination;
		this.airline = airline;
		this.takeOffTime = takeOffTime;
		this.landingTime = landingTime;
		
	}
}
