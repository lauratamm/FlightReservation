package controller;

import java.awt.Window;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Airline;
import model.Flight;
import model.Model;

public class AirlineController extends AbstractController implements Serializable {
	
	public List<Airline> allAirlines = new  ArrayList<>();
	
	
	

	public String getAirlineName(Airline airline) {
		return airline.airlineName;
	}
	public void setAirlineName(Airline newAirline, String airlineName) {
		newAirline.airlineName = airlineName;
	}

	public void addFlightsToAirline(Airline airline, Flight flight) {
		airline.allFlightsForAirline.add(flight);
	}
	
	public List<Airline> getAllAirlines() {
		return allAirlines;	
	}
	
	public Object[] getAllAirlineNames() {
		ArrayList<String> airlineNames = new ArrayList<String>();
		for (Airline tempAirline : getAllAirlines()){			
			airlineNames.add(tempAirline.airlineName);			
		}
		return airlineNames.toArray();
	}
	
	private void addAirline(String airlineName) {
		Airline newAirline = new Airline (airlineName);
		allAirlines.add(newAirline);
		serialize(allAirlines, "allAirlines.data");
		setChanged();
		notifyObservers();
	}

	public boolean validateNotEmpty(String airlineName) {
		if (!airlineName.isEmpty() ) {
			addAirline(airlineName);
			return true;
		}
		else {			
			return false;
		}
	}
	
	public Airline findAirline(String airlineName){
		for (Airline tempAirline: allAirlines){
			if (tempAirline.airlineName.equals(airlineName)){
				return tempAirline;
			}
		}
		return null;
	}

	
}
