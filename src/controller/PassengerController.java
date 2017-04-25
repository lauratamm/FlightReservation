package controller;

import java.util.ArrayList;

import enumerator.PassengerClass;
import model.Airline;
import model.Booking;
import model.BusinessClass;
import model.Flight;
import model.Passenger;
import model.StandardClass;

public class PassengerController extends AbstractController  {
	
	public String fileName= "allPassengers.data";
	private ArrayList<Passenger> allPassengers = new ArrayList<Passenger>();
	private static PassengerController passengerController = null;
	
	private PassengerController() {}
	
	public static PassengerController getInstance() {
		if (passengerController == null){
			passengerController = new PassengerController();
		}
		return passengerController;
	}
	
	

	public ArrayList<Passenger> getPassengerList() {
		return PassengerController.getInstance().allPassengers;
	}
	
	public Passenger findPassenger (String firstName, String lastName) {
		for (Passenger tempPassenger : 	getPassengerList()) {
			//check if passenger exists
			if ( firstName.equals(tempPassenger.getFirstname()) && lastName.equals(tempPassenger.getLastname())) {
				return tempPassenger;
				}
		}
		return null;
				
	}
	
	public boolean isPassengerEligibleToBook (Passenger passenger, Flight flight) {// pass the flight into this as well pal

		System.out.println(passenger.getBookings().size() + "  in eligiblity");
		
		Airline airline = flight.getAirline();
		int bookingsPerAirline = 0;
		
		for (Booking tempBooking: passenger.getBookings()) {			
			if (tempBooking.getFlight().getAirline().getAirlineName().equals(airline.getAirlineName())) {
				bookingsPerAirline++;
				}
			}
		
		System.out.println(bookingsPerAirline + "  bookings per airline");
		if (passenger instanceof BusinessClass ){
			
			System.out.println("business class");
			if ( bookingsPerAirline<4 )	{
			return true;
				}
			else {
				return false;
			}
		}
		else{ //if passenger is an instance of StandardClass
			System.out.println("standard class");
			if ( bookingsPerAirline<2 ){
			System.out.println("returned true");
			return true;
			}
			else {
				System.out.println("returned false");
			return false;
			}
		}
	}
			
	public Passenger addPassenger (String firstName,  String lastName, PassengerClass passengerClass) {
		if (passengerClass == PassengerClass.BUSINESS) {
			System.out.println(" new business passenger");
			Passenger passenger = new BusinessClass(firstName, lastName, PassengerClass.BUSINESS);
			PassengerController.getInstance().allPassengers.add(passenger);
			serialize(this.allPassengers, this.fileName);
			return passenger;
			}
		else {
			System.out.println(" new standard passenger");
			Passenger passenger = new StandardClass(firstName, lastName, PassengerClass.STANDARD);
			PassengerController.getInstance().allPassengers.add(passenger);
			serialize(this.allPassengers, this.fileName);
			return passenger;
		}
	}		
	
}
