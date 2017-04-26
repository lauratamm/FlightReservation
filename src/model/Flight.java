package model;

import java.io.Serializable;
import java.util.Date;

import interfaces.Model;

public class Flight implements Model, Serializable {
	private String flightNumber;
	private String departsFrom;
	private String destination;
	private Airline airline;
	private Date takeOffTime;
	private Date landingTime;
	
	public Flight (){};
	
	public Flight (String flightNumber, String departsFrom, String destination, Airline airline, Date takeOffTime, Date landingTime ){ 
		this.airline=airline;
		this.flightNumber = flightNumber;
		this.departsFrom= departsFrom;
		this.destination = destination;
		this.takeOffTime = takeOffTime;
		this.landingTime = landingTime;
		
	}
	
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartsFrom() {
		return departsFrom;
	}

	public void setDepartsFrom(String departsFrom) {
		this.departsFrom = departsFrom;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Date getTakeOffTime() {
		return takeOffTime;
	}

	public void setTakeOffTime(Date takeOffTime) {
		this.takeOffTime = takeOffTime;
	}

	public Date getLandingTime() {
		return landingTime;
	}

	public void setLandingTime(Date landingTime) {
		this.landingTime = landingTime;
	}
}
