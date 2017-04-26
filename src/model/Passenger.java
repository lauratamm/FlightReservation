package model;

import java.io.Serializable;
import interfaces.Model;
import interfaces.PassengerClassInterface;

public abstract class Passenger implements Model, Serializable, PassengerClassInterface {
	private String firstname;
	private String lastname;
	
	public Passenger (String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	

}
