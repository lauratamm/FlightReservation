package controller;

import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import model.Airline;
import model.Flight;
import model.Model;

public class AirlineController extends AbstractController  {

	private List<Airline> allAirlines = new ArrayList<>();
	public String fileName = "allAirlines.data";
	
	private static AirlineController airlineController = null;

	private AirlineController() {
	}

	public static AirlineController getInstance() {
		if (airlineController == null) {
			airlineController = new AirlineController();
		}
		return airlineController;
	}

	public void addFlightsToAirline(Airline airline, Flight flight) {
		airline.getAllFlightsForAirline().add(flight);
	}

	public List<Airline> getAirlineList() {
		return this.allAirlines;
	}
	
	//get an array of airline names for the dropdown menu
	public Object[] getAllAirlineNames() {
		ArrayList<String> airlineNames = new ArrayList<String>();
		for (Airline tempAirline : getAirlineList()) {
			airlineNames.add(tempAirline.getAirlineName());
		}
		return airlineNames.toArray();
	}

	//add airline to list of airlines
	private void addAirline(String airlineName) {
		Airline newAirline = new Airline(airlineName);
		this.allAirlines.add(newAirline);
		serialize(this.allAirlines, this.fileName);
		setChanged();
		notifyObservers();
	}

	public boolean validateAirlineFieldNotEmpty(String airlineName) {
		if (!airlineName.isEmpty()) {
			addAirline(airlineName);
			return true;
		} else {
			return false;
		}
	}

	public Airline findAirline(String airlineName) {
		for (Airline tempAirline : this.allAirlines) {
			if (tempAirline.getAirlineName().equals(airlineName)) {
				return tempAirline;
			}
		}
		return null;
	}

}
