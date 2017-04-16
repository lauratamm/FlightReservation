package controller;

import java.util.ArrayList;

import model.Booking;
import model.BusinessClass;
import model.Flight;
import model.Passenger;
import model.StandardClass;

public class PassengerController extends AbstractController implements PassengerControllerInterface {
	
	private Passenger passenger;
	private ArrayList<Passenger> allPassengers = new ArrayList<Passenger>();

	public String getFirstname() {
		return passenger.firstname;
	}

	public void setFirstname(String firstname) {
		passenger.firstname = firstname;
	}

	public String getLastname() {
		return passenger.lastname;
	}

	public void setLastname(String lastname) {
		passenger.lastname = lastname;
	}


	public ArrayList<Passenger> getPassengerList() {
		return this.allPassengers;

	}
	
	public Passenger findPassenger (String firstName, String lastName) {
		for (Passenger tempPassenger : 	getPassengerList()) {
			//check if passenger exists
			if ( firstName == tempPassenger.firstname && lastName == tempPassenger.lastname) {
				return tempPassenger;
				}
		}
		return null;
				
	}
	
	public boolean isPassengerEligibleToBook (Passenger passenger, Flight flight) {// pass the flight into this as well pal
		String airline = flight.airline;
		int bookingsPerAirline = 0;
		for (Booking tempBooking: passenger.bookings) {
			if (tempBooking.flight.airline == airline) {
				bookingsPerAirline++;
				}
			}
		
		if (passenger instanceof BusinessClass ){
			System.out.println("business class");
			if ( bookingsPerAirline<3 )	{
			return true;
				}
			else {
				return false;
				}
		}
		else{ //if passenger is an instance of StandardClass
			System.out.println("standard class");
			if ( bookingsPerAirline<1 ){
			return true;
			}
		else {
			return false;
			}
		}
	}
		
	
	public Passenger addPassenger (String firstName,  String lastName, String passengerClass) {
		if (passengerClass == "Business") {
			Passenger passenger = new BusinessClass(firstName, lastName, passengerClass);
			
			return passenger;
			}
		else {Passenger passenger = new StandardClass(firstName, lastName, passengerClass);
			return passenger;
		}
	}		
	
}
