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
	
	public Passenger findPassenger (String firstName, String lastName, PassengerClass pClass) {
		for (Passenger tempPassenger : 	getPassengerList()) {
			//check if passenger exists
			if ( firstName.equals(tempPassenger.getFirstname()) && lastName.equals(tempPassenger.getLastname()) && 
				pClass.equals(tempPassenger.getPassengerClass())) {
				return tempPassenger;
				}
		}
		return null;
				
	}
	
	public boolean isPassengerEligibleToBook (Passenger passenger, Flight flight) {
		
		Airline airline = flight.getAirline();
		int bookingsPerAirline = 0;
		
		for (Booking tempBooking: BookingController.getInstance().getBookingList()) {			
			if (tempBooking.getPassenger().getFirstname().equals(passenger.getFirstname()) &&
				tempBooking.getPassenger().getLastname().equals(passenger.getLastname()) &&
				tempBooking.getFlight().getAirline().getAirlineName().equals(airline.getAirlineName())) {
				bookingsPerAirline++;
				}
			}
		
		if (passenger instanceof BusinessClass ){
			if ( bookingsPerAirline<4 )	{
			return true;
				}
			else {
				return false;
			}
		}
		else{ //if passenger is an instance of StandardClass
			if ( bookingsPerAirline<2 ){
			return true;
			}
			else {
			return false;
			}
		}
	}
			
	public Passenger addPassenger (String firstName,  String lastName, PassengerClass passengerClass) {
		if (passengerClass == PassengerClass.BUSINESS) {
			Passenger passenger = new BusinessClass(firstName, lastName, PassengerClass.BUSINESS);
			PassengerController.getInstance().allPassengers.add(passenger);
			serialize(this.allPassengers, this.fileName);
			return passenger;
			}
		else {
			Passenger passenger = new StandardClass(firstName, lastName, PassengerClass.STANDARD);
			PassengerController.getInstance().allPassengers.add(passenger);
			serialize(this.allPassengers, this.fileName);
			return passenger;
		}
	}		
	
}
