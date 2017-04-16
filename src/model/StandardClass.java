package model;

public class StandardClass extends Passenger {

	String passengerClass;
	
	public StandardClass (String firstname, String lastname, String passengerClass) {
		super (firstname, lastname);
		this.passengerClass = passengerClass;
	}
	
}
