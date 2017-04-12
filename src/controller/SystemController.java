package controller;

import java.util.ArrayList;

import model.Airline;
import model.Flight;
import model.ReservationSystem;

public class SystemController implements SystemControllerInterface{
	
	private ReservationSystem reservationSys=new ReservationSystem();
	//private Flight flight;
	
	
	public void addFlight(Flight flight){
		reservationSys.allFlights.add(flight);
	}

	@Override
	public String displayAllFlights() {
		String output = "";
		for (Flight tempFlight : reservationSys.allFlights) {
			
			output = output + tempFlight.flightNumber + "  " + tempFlight.departsFrom + " - " +tempFlight.destination + "\n";
		}		
		return output;
	}

	@Override
	public void addAirline(Airline airline) {
		reservationSys.allAirlines.add(airline);		
	}

	@Override
	public String displayAllAirlines() {
		String output = "";
		for (Airline tempAirline : reservationSys.allAirlines) {
			
			output = output + tempAirline.airlineName + "\n";
		}		
		return output;
	}
	
	
}
