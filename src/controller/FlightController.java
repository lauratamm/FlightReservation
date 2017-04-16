package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Airline;
import model.Flight;
import model.Passenger;

public class FlightController extends AbstractController {

	Flight flight;
	AirlineController airlineController =  new AirlineController();

	ArrayList <Flight> allFlights = new ArrayList <Flight>();

	public void addPassengerToFlight (Flight flight, Passenger passenger) {
		flight.passengerList.add(passenger);		
	}

	public Airline getAirline(Flight flight) {
		return flight.airline;
	}

	public void setAirline(Flight flight,Airline airline) {
		flight.airline = airline;
	}


	public void setFlightNumber(Flight flight,String flightNumber) {
		flight.flightNumber = flightNumber;
	}
	public String getDepartsFrom(Flight flight) {
		return flight.departsFrom;
	}
	public void setDepartsFrom(Flight flight,String departsFrom) {
		flight.departsFrom = departsFrom;
	}
	public String getDestination(Flight flight) {
		return flight.destination;
	}
	public void setDestination(Flight flight,String destination) {
		flight.destination = destination;
	}

	public void setDepartureTime(Flight flight,Date takeOffTime){
		flight.takeOffTime=takeOffTime;
	}

	public Date getDepartureTime (Flight flight){
		return flight.takeOffTime;
	}

	public void setLandingTime (Flight flight,Date landingTime){
		flight.landingTime=landingTime;
	}

	public Date getLandingTime (Flight flight){
		return flight.landingTime;
	}

	public String getFlightNumber(Flight flight) {
		return flight.flightNumber;
	}


	public String getAllPassengers(Flight flight) {
		System.out.println(flight.flightNumber + "flightnumber inside method");
		String output = "";
		for (Passenger tempPassenger : flight.passengerList) {
			System.out.println(tempPassenger.firstname + "temppassenger name");
			output = output + tempPassenger.firstname +  "\n";
		}		
		//System.out.println(output + "  output");
		return output;

	}


	public boolean validateFlight(String flightNumber, String date, String takeoffTime, String landingTime, String departsFrom,
			String destination, String airlineName) {


		if (flightNumber.isEmpty() || takeoffTime.isEmpty() || landingTime.isEmpty() || airlineName.isEmpty() ||date.isEmpty()) {
			return false;
		}
		
		//find airline object by airline name
		Airline airline = airlineController.findAirline(airlineName);
		
		//convert date strings into a date object
		String dateAndDepartureTime = date + "/" + takeoffTime;
		String dateAndLandingTime = date + "/" + landingTime;
		Date parsedDepartureTime;
		Date parsedLandingTime;

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy/hh:mm");

		try {
			parsedDepartureTime = df.parse(dateAndDepartureTime);
			parsedLandingTime = df.parse(dateAndLandingTime);
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

	private void createFlight(String flightNumber, String departsFrom, String destination, Airline airline, Date takeOffTime, Date landingTime){
		Flight newFlight = new Flight (flightNumber, departsFrom, destination,airline, takeOffTime , landingTime);
		allFlights.add(newFlight);
		setChanged();
		notifyObservers();
	}


	//adds flight to the list of all flights
	public void addFlightToList(Flight flight){//system should observe changes in airline, flight and passenger models?
		this.allFlights.add(flight);
	}

	public ArrayList<String> getAllFlightNumbers() {
		//System.out.println("getflightnumbers called");
		ArrayList <String> allFlightNumbers = new ArrayList<String>();
		for (Flight tempFlight : this.allFlights) {
			allFlightNumbers.add(tempFlight.flightNumber);
			System.out.println( tempFlight.flightNumber + "  inside getallflightnumbers");
		}		
		return allFlightNumbers;
	}

	public ArrayList<Flight> getFlightList() {
		return this.allFlights;
	}



	public Flight findFlight (String flightDept, String flightDest, Date flightDate) {
		for (Flight tempFlight: getFlightList()) {
			//find the flight where the details match
			if (tempFlight.takeOffTime == flightDate && tempFlight.departsFrom == flightDept && tempFlight.destination == flightDest) {
				return tempFlight;
			}
		}
		return null;
	}

}
