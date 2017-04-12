import javax.swing.JOptionPane;

import controller.AirlineController;
import controller.AirlineControllerInterface;
import controller.BookingController;
import controller.BookingControllerInterface;
import controller.FlightController;
import controller.FlightControllerInterface;
import controller.PassengerController;
import controller.PassengerControllerInterface;
import controller.SystemController;
import controller.SystemControllerInterface;
import model.Flight;
import model.ReservationSystem;
import view.UserInterface;

public class Main  {
	AirlineControllerInterface airlineController = new AirlineController();
	BookingControllerInterface bookingController = new BookingController();
	FlightControllerInterface flightController = new FlightController();
	PassengerControllerInterface passengerController = new PassengerController();
	SystemControllerInterface systemController = new SystemController();
	static UserInterface userInterface = new UserInterface();
	
	//public static void main(String[] args) {
	//	userInterface.displayMenu();	
	//}
	
	
	
}