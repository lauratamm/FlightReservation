import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import view.UserGUI;
import view.UserInterface;


public class Main  {
	/**
	 * Launch the application.
	 */
	
	private static JLabel lblLoadInterface;

	public static void main(String[] args) {	
		
		if (loadUIorGUI()){
			try {					
				UserGUI window = new UserGUI();
				window.frame.setVisible(true);					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
				UserInterface UI = new UserInterface();
				UI.initialize();
			}
		}
		
	
	public static boolean loadUIorGUI() {
		lblLoadInterface = new JLabel("Choose interface:");
		UIManager.put("OptionPane.okButtonText", "GUI");
		UIManager.put("OptionPane.cancelButtonText", "UI");
		int result = JOptionPane.showConfirmDialog(null, lblLoadInterface, "Cancel Booking", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) return true;
		else return false;
	}
}