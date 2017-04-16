import controller.AirlineController;
import controller.BookingController;
import controller.FlightController;
import controller.PassengerController;
import view.UserGUI;


public class Main  {
	/**
	 * Launch the application.
	 */
	
	

	public static void main(String[] args) {	
		AirlineController airlineController = new AirlineController();
		BookingController bookingController = new BookingController();
		FlightController flightController = new FlightController();
		PassengerController passengerController = new PassengerController();
		
		try {					
			UserGUI window = new UserGUI(airlineController, bookingController, flightController, passengerController);
			window.frame.setVisible(true);	
			airlineController.addObserver(window);
			bookingController.addObserver(window);
			flightController.addObserver(window);					
		} catch (Exception e) {
			e.printStackTrace();
		}
};
}