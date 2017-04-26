package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import controller.*;
import model.Airline;
import model.Booking;
import model.Flight;

public class UserInterface {
	AirlineController airlineController = AirlineController.getInstance();
	BookingController bookingController = BookingController.getInstance();
	FlightController flightController = FlightController.getInstance();
	PassengerController passengerController = PassengerController.getInstance();
	private JLabel lblLoadData;
	public UserInterface() {}

	public void initialize(){
		UIManager.put("OptionPane.okButtonText", "OK");
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		if (loadDataOrBlankSystem()){
			//deserialize 
			airlineController.getAirlineList().addAll(airlineController.deserialize(airlineController.getAirlineList(), airlineController.fileName));
			flightController.getFlightList().addAll(flightController.deserialize(flightController.getFlightList(), flightController.fileName));
			passengerController.getPassengerList().addAll(passengerController.deserialize(passengerController.getPassengerList(), passengerController.fileName));
			bookingController.getBookingList().addAll(bookingController.deserialize(bookingController.getBookingList(), bookingController.fileName));
		}
		displayMenu();

	}

	public void displayMenu() {
		String output, usersChoiceString;
		int usersChoice;

		do
		{
			output = "Flight Reservation System \n";
			output = output + "Select a menu option \n\n";
			output = output + "1 - Add airline \n";
			output = output + "2 - View airlines \n";
			output = output + "3 - View all flights \n";
			output = output + "4 - Add flight \n";
			output = output + "5 - View passengers on a flight \n";
			output = output + "6 - Book a flight \n";
			output = output + "7 - Cancel a booking \n";
			output = output + "8 - Quit";

			usersChoiceString = askUser(output);
			// convert usersChoiceString to an integer
			try {
				usersChoice = Integer.parseInt(usersChoiceString);
			} catch (NumberFormatException e) {
				usersChoice = 99;
				//e.printStackTrace();
			}

			switch (usersChoice) {
			case 1 :
				getAirlineDetails();
				break;
			case 2 :
				displayAllAirlines();
				break;
			case 3 :
				displayAllFlights();
				break;
			case 4 :
				getFlightDetails();
				break;
			case 5 :
				displayPassengers();
				break;
			case 6 :
				getBookingDetails();
				break;
			case 7 :
				getCancelBookingDetails();
				break;
			case 8 :
				System.exit(0);
			default :
				output = "Please enter a number between 1-8";
				JOptionPane.showMessageDialog(null, output, "Error", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}while(usersChoice != 8);

	} // end displayMenu method



	/** Display String */	
	private void display(String aMessage) {
		JOptionPane.showMessageDialog(null, aMessage, "Electronic Address Book", JOptionPane.INFORMATION_MESSAGE);
	}

	/** Get user input */
	private String askUser(String aMessage) {
		return JOptionPane.showInputDialog(aMessage);
	}

	private void reportError(String aMessage) {
		JOptionPane.showMessageDialog(null, aMessage, "Electronic Address Book", JOptionPane.ERROR_MESSAGE);
	}


	private boolean loadDataOrBlankSystem() {
		lblLoadData = new JLabel("Load data?");
		int result = JOptionPane.showConfirmDialog(null, lblLoadData, "Cancel Booking", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) return true;
		else return false;
	}

	private void displayAllAirlines() {
		String output="";
		for(Airline tempAirline:airlineController.getAirlineList()){
			output = output + tempAirline.getAirlineName()+ "\n";
		}
		display(output);
	}

	private void getAirlineDetails() {
		String airlineName;	 
		airlineName = askUser("Please enter airline name: \n");
		airlineController.validateAirlineFieldNotEmpty(airlineName);
	}

	private void displayAllFlights() {
		String output="";
		for(Flight tempFlight:flightController.getFlightList()){
			output = output + tempFlight.getFlightNumber()+ "  " + tempFlight.getDepartsFrom() + " - " + tempFlight.getDestination()   
			+ "  " + tempFlight.getTakeOffTime() + " - "+ tempFlight.getLandingTime() + "\n";
		}
		display(output);
	}

	private void getFlightDetails() {
		String flightNumber;
		String departsFrom;
		String destination;
		String airline;
		String date;
		String takeOffTime;
		String landingTime;

		try {
			flightNumber = askUser("Please enter the flight number:");
			departsFrom = askUser("Please enter departure location:");
			destination = askUser("Please enter destination location:");
			airline = askUser("Please enter the airline:");
			date = askUser("Please enter departure date (dd/MM/yyyy):");
			takeOffTime = askUser("Please enter departure time (hh:mm)");
			landingTime = askUser("Please enter landing time (hh:mm)");

			if (!flightController.validateFlight(flightNumber, date, takeOffTime, landingTime, departsFrom, destination,
					airline)){
				reportError("Invalid entry");
			}
		}
		catch (Exception e){
			reportError("Invalid entry");
		}		
	}


	private void displayPassengers() {
		String flightNumber;
		Flight flight;
		String output = "";
		
		try {
			flightNumber = askUser("Please enter flight number:");
			flight = flightController.findFlightByFlightNo(flightNumber);
			
			System.out.println(bookingController.getBookingList().size());
			for (Booking tempBooking : bookingController.getBookingList()) {
				if (flight.getFlightNumber().equals(tempBooking.getFlight().getFlightNumber())){
					output = output + "Booking ref: "+ tempBooking.getBookingRef() + "   " + tempBooking.getPassenger().getFirstname() + "  "+ tempBooking.getPassenger().getLastname() + "\n";
				}
			}
			if (output=="") {
				reportError("No passengers found.");
			}
			else
				display(output);
		}
		catch (Exception e) {
			reportError("Invalid flight number.");
			}
		
		
	}

	private void getBookingDetails() {
		try{
			String departsFrom = askUser("Please enter departure location:");
			String destination = askUser("Please enter destination location:");
			String date = askUser("Please enter departure date (E dd/MM/yyyy):");
			String takeOffTime = askUser("Please enter departure time (hh:mm)");
			String takeOff = date+" "+takeOffTime;
			String firstName = askUser("Please enter your first name");
			String lastName = askUser("Please enter your last name");
			String passengerClass= askUser("Please enter passenger class (STANDARD/BUSINESS)");


			switch (bookingController.validateBooking(departsFrom, destination, takeOff, firstName, lastName,
					passengerClass)) {
					case 1:
						reportError("Please enter a valid date.");
						break;
					case 2:
						reportError("Number of bookings exceeded!");
						break;
					case 3:
						display("Booking successful!");
						break;
			}
		}
		catch (Exception e) {
			reportError("Invalid details.");	
		}
	}

	private void getCancelBookingDetails() {
		String bookingRefString = askUser("Please enter booking reference:");

		if (!bookingController.validateBookingRef(bookingRefString)) {
			reportError("Invalid booking reference!");
		}
		else {
			display("Booking cancelled!");
		}
	}	
}