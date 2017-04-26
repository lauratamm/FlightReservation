package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Airline;
import model.Flight;

public class FlightController extends AbstractController {

	private DateFormat dateFormatForFlightModel = new SimpleDateFormat("dd/MM/yyyy/HH:mm");	
	private DateFormat dateFormatForBooking = new SimpleDateFormat("E dd/MM/yyyy  HH:mm");
	private ArrayList <Flight> allFlights = new ArrayList <Flight>();
	public String fileName = "allFlights.data";

	//Singleton
	private static FlightController flightController = null;

	private FlightController() {}

	public static FlightController getInstance() {
		if (flightController == null){
			flightController = new FlightController();
		}
		return flightController;
	}

	//validate user input for flight
	public boolean validateFlight(String flightNumber, String date, String takeoffTime, String landingTime, String departsFrom,
			String destination, String airlineName) {

		//validate fields not empty
		if (flightNumber.isEmpty() || takeoffTime.isEmpty() || landingTime.isEmpty() || airlineName.isEmpty() ||date.isEmpty()) {
			return false;
		}

		//find airline object by airline name
		Airline airline = AirlineController.getInstance().findAirline(airlineName);

		//convert date strings into a date object
		String dateAndDepartureTime = date + "/" + takeoffTime;
		String dateAndLandingTime = date + "/" + landingTime;
		Date parsedDepartureTime;
		Date parsedLandingTime;

		try {
			parsedDepartureTime = dateFormatForFlightModel.parse(dateAndDepartureTime);
			parsedLandingTime = dateFormatForFlightModel.parse(dateAndLandingTime);
			createFlight(flightNumber, departsFrom, destination,airline, parsedDepartureTime , parsedLandingTime);
			return true;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	//returns a specific flight
	public Flight getFlight(int index) {
		return this.allFlights.get(index);
	}

	//create flight
	private void createFlight(String flightNumber, String departsFrom, String destination, Airline airline, Date takeOffTime, Date landingTime){
		Flight newFlight = new Flight (flightNumber, departsFrom, destination,airline, takeOffTime , landingTime);
		allFlights.add(newFlight);
		serialize(allFlights, this.fileName);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Flight> getFlightList() {
		return this.allFlights;
	}

	//find flight times for routes
	public Object[] findFlightTimes (String flightDept, String flightDest) {
		ArrayList<String> allMatchingFlights = new ArrayList<String>();
		for (Flight tempFlight: getFlightList()) {
			//find flights where the details match
			if (tempFlight.getDepartsFrom().equals(flightDept) && tempFlight.getDestination().equals(flightDest)) {
				allMatchingFlights.add(dateFormatForBooking.format(tempFlight.getTakeOffTime()));
			}			
		}
		if (allMatchingFlights.size() !=0){
			return allMatchingFlights.toArray();
		}
		else return null;
	}

	public Flight findFlight(String departsFrom,String destination, Date flightTime){
		for (Flight tempFlight: allFlights) {	
			if (tempFlight.getTakeOffTime().equals(flightTime) && tempFlight.getDepartsFrom().equals(departsFrom) 
					&& tempFlight.getDestination().equals(destination)){	
				return tempFlight;
			}
		}
		return null;
	}

	public DateFormat getDateFormatForFlightModel() {
		return dateFormatForFlightModel;
	}

	public DateFormat getDateFormatForBooking() {
		return dateFormatForBooking;
	}

}
