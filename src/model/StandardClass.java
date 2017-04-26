package model;

import enumerator.PassengerClass;
import interfaces.PassengerClassInterface;

public class StandardClass extends Passenger implements PassengerClassInterface {

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
