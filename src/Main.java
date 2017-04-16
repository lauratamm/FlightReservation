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
		
		
		try {					
			UserGUI window = new UserGUI();
			window.frame.setVisible(true);					
		} catch (Exception e) {
			e.printStackTrace();
		}
};
}