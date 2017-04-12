package controller;

import model.Passenger;

public class PassengerController implements PassengerControllerInterface {
	
	private Passenger passenger;
	

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

}
