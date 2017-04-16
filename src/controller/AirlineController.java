package controller;

import java.util.ArrayList;
import java.util.List;

import model.Airline;
import model.Flight;
import model.Model;

public class AirlineController extends AbstractController {
	
	public static List<Airline> allAirlines = new  ArrayList<>();
	
	private Airline airline;

	public String getAirlineName() {
		return airline.airlineName;
	}
	public void setAirlineName(String airlineName) {
		airline.airlineName = airlineName;
	}

	public void addFlightsToAirline(Flight flight) {
		airline.allFlightsForAirline.add(flight);
	}
	
	public List<Airline> getAllAirlines() {
		return allAirlines;	
	}

	public void validateNotEmpty(String airlineName) {
		if (airlineName != null) {
			Airline newAirline = new Airline (airlineName);
			System.out.println(newAirline.airlineName + "in validation method");
			allAirlines.add(airline);
			//serialize(allAirlines);
			setChanged();
			notifyObservers();
		}
		else reportError("Enter airline name");
	}
	
	
}
