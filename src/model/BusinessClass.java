package model;

import enumerator.PassengerClass;

public class BusinessClass extends Passenger {
	
	PassengerClass passengerClass;
	
	public BusinessClass (String firstname, String lastname, PassengerClass passengerClass) {
		super (firstname, lastname);
		this.passengerClass = passengerClass;
	}
	
}
