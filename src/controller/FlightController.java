package controller;

import java.util.ArrayList;
import java.util.Date;

import model.Flight;
import model.Passenger;

public class FlightController extends AbstractController implements FlightControllerInterface{

	private Flight flight;
	ArrayList <Flight> allFlights = new ArrayList <Flight>();
	
	public void addPassengerToFlight (Passenger p) {
		flight.passengerList.add(p);		
	}
	
	public String getAirline() {
		return flight.airline;
	}

	public void setAirline(String airline) {
		flight.airline = airline;
	}

	
	public void setFlightNumber(String flightNumber) {
		flight.flightNumber = flightNumber;
	}
	public String getDepartsFrom() {
		return flight.departsFrom;
	}
	public void setDepartsFrom(String departsFrom) {
		flight.departsFrom = departsFrom;
	}
	public String getDestination() {
		return flight.destination;
	}
	public void setDestination(String destination) {
		flight.destination = destination;
	}

	@Override
	public String getFlightNumber() {
		return flight.flightNumber;
	}
	
	@Override
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

	@Override
	public void validateFlight(String flightNumber, String takeoffTime, String landingTime, String departsFrom,
			String destination, String airline) {
	}

	//returns a specific flight
	public Flight getFlight(int index) {
		return this.allFlights.get(index);
	}
	
	//adds flight to the list of all flights
		public void addFlight(Flight flight){//system should observe changes in airline, flight and passenger models?
			this.allFlights.add(flight);
		}

		public ArrayList<String> getAllFlightNumbers() {
			ArrayList <String> allFlightNumbers = new ArrayList<String>();
			for (Flight tempFlight : this.allFlights) {
				allFlightNumbers.add(tempFlight.flightNumber);
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
