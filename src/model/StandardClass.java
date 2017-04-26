package model;

import enumerator.PassengerClass;

public class StandardClass extends Passenger {

	private PassengerClass passengerClass;
	
	public StandardClass (String firstname, String lastname, PassengerClass passengerClass) {
		super (firstname, lastname);
		this.passengerClass = passengerClass;
	}

	@Override
	public PassengerClass getPassengerClass() {
		return passengerClass;
	}

	public void setPassengerClass(PassengerClass passengerClass) {
		this.passengerClass = passengerClass;
	}	
}
