package model;

import enumerator.PassengerClass;

public class BusinessClass extends Passenger {
	
	private PassengerClass passengerClass;
	
	public BusinessClass (String firstname, String lastname, PassengerClass passengerClass) {
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
