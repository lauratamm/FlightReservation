package model;

import enumerator.PassengerClass;

public class StandardClass extends Passenger {

	PassengerClass passengerClass;
	
	public StandardClass (String firstname, String lastname, PassengerClass passengerClass) {
		super (firstname, lastname);
		this.passengerClass = passengerClass;
	}
	
}
