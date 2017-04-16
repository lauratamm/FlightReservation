package model;

public class BusinessClass extends Passenger {
	
	String passengerClass;
	
	public BusinessClass (String firstname, String lastname, String passengerClass) {
		super (firstname, lastname);
		this.passengerClass = passengerClass;
	}
	
}
