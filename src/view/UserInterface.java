package view;

import javax.swing.JOptionPane;

import controller.FlightController;
import controller.FlightControllerInterface;

public class UserInterface {
	public void displayMenu() {
		String output, userInputString;
		Integer userInput;
		
		FlightControllerInterface flight = new FlightController();
		
		System.out.println(flight.getFlightNumber());
		
		do {
			output = "Flight Reservation Menu \n";
			output = output + "Select an option: \n\n";
			output = output + "1:	Display all flights \n";
			output = output + "2:	Add new flights \n";
			output = output + "3:	List all airlines \n";
			output = output + "4:	Add new airline \n";
			output = output + "5:	View all passengers on a flight \n";
			output = output + "6:	Make a booking \n";
			output = output + "7:	Cancel a booking \n";
			output = output + "8:	Quit \n";
			
			//ask for input and try to convert into an integer
			userInputString = askUser(output);
			try {
				userInput = Integer.parseInt(userInputString);
			}	catch (NumberFormatException e) {
				userInput = 99;
			}
			
			switch (userInput){
			case 1:
			//displayAllFlights();
			break;
			case 2:
			//addFlight();
			break;
			default:
				output="wat";
				JOptionPane.showMessageDialog(null, output, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}
			
		} while (userInput !=7);
	}
	
	 public String askUser(String aMessage) {
		    return JOptionPane.showInputDialog(aMessage);
		  }
	 
	 
	 /*private Flight getFlightDetails() {
			String flightNumber, departsFrom, destination, airline;
			
			//validate inputs as well
			flightNumber = askUser("Please enter the flight number:");
			departsFrom = askUser("Please enter the departure location:");
			destination = askUser("Please enter the destination:");
			airline = askUser("Please enter the airline:");
			
			Flight newFlight = new Flight ();
			return newFlight;
		}*/
}
