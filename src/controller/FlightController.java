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

	public DateFormat df = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
	
	public DateFormat bookingDateFormat = new SimpleDateFormat("E dd/MM/yyyy  HH:mm");
	
	Flight flight;
	AirlineController airlineController =  new AirlineController();

	public ArrayList <Flight> allFlights = new ArrayList <Flight>();

	public void addPassengerToFlight (Flight flight, Passenger passenger) {
		flight.passengerList.add(passenger);	
		//serialize(allFlights, "allFlights.data");
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
		String output = "";
		for (Passenger tempPassenger : flight.passengerList) {
			output = output + tempPassenger.firstname +  "\n";
		}		
		return output;

	}


	public boolean validateFlight(String flightNumber, String date, String takeoffTime, String landingTime, String departsFrom,
			String destination, String airlineName) {

		//validate fields not empty
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
		serialize(allFlights, "allFlights.data");
		setChanged();
		notifyObservers();
	}


	//adds flight to the list of all flights
	public void addFlightToList(Flight flight){//system should observe changes in airline, flight and passenger models?
		this.allFlights.add(flight);
	}

	public ArrayList<String> getAllFlightNumbers() {
		ArrayList <String> allFlightNumbers = new ArrayList<String>();
		for (Flight tempFlight : getFlightList()) {
			allFlightNumbers.add(tempFlight.flightNumber);
		}		
		return allFlightNumbers;
	}

	public ArrayList<Flight> getFlightList() {
		return this.allFlights;
	}



	public Object[] findFlightTimes (String flightDept, String flightDest) {
		System.out.println(allFlights.size() + "  findFlightTimes");
		ArrayList<String> allMatchingFlights = new ArrayList<String>();
		
		for (Flight tempFlight: getFlightList()) {
			
			//find flights where the details match
			if ( tempFlight.departsFrom.equals(flightDept) && tempFlight.destination.equals(flightDest)) {
				 allMatchingFlights.add(bookingDateFormat.format(tempFlight.takeOffTime));
			}			
		}
		if (allMatchingFlights.size() !=0){
			return allMatchingFlights.toArray();
		}
		else return null;
	}
	
	
	public Flight findFlight(String departsFrom,String destination, Date flightTime){
	
		System.out.println(getFlightList().size() + "length of allFlights");
		System.out.println(allFlights.size()  +  "  length of flighlist from gui");
		for (Flight tempFlight: allFlights) {		
			if (tempFlight.takeOffTime.equals(flightTime) && tempFlight.departsFrom.equals(departsFrom) && tempFlight.destination.equals(destination)){
				return tempFlight;
			}
		}
		return null;
	}

}
