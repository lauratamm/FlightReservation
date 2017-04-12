package view;

import java.awt.EventQueue;
import enumerator.DeptDest;
import model.Airline;
import model.Flight;
import model.ReservationSystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;

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

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Button;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTable;

public class UserGUI {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblFlightReservationSystem;
	private JButton btnManageFlights;
	private JButton btnManageAirlines;
	private JButton btnManagePassengers;
	private JSeparator separator;
	private JLabel lblAirlineCard;
	private JLabel lblFlightCard;
	private JButton btnViewAllAirlines;
	private JLabel lblAddNewAirline;
	private JButton btnAddAirline;
	private JTextField textFieldAddFlight;
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private JPanel homeCard;
	private JPanel passengerCard;
	private JPanel airlineCard;
	private JPanel flightCard;
	private JList listOfAllAirlines;
	private JButton btnViewAllFlights;
	private JList listOfFlights;
	private JLabel lblPassengerCard;
	private JButton btnMakeABooking;
	private JButton btnCancelABooking;
	private JButton btnAddFlight;
	private JPopupMenu popupMenu;
	private JButton btnThing;
	private JTextField textFieldAddAirline;
	private JPanel addAirlinePopup;
	private JLabel lblAddNewFlight;
	private JPanel addFlightPopup;
	private JComboBox departureSelector;
	private JLabel lblAddFlightNumber;
	private JTextField textFieldAddFlightNo;
	private JLabel lblAddDeparture;
	private JComboBox destinationSelector;
	private JLabel lblAddDestination;
	private JLabel lblAddFlightAirline;
	private JTextField textFieldAddFlightAirline;
	private JTextArea allFlightsTarget;
	private JTextArea allAirlinesTarget;
	private JTextArea txtSelectAFlight;
	/**
	 * Launch the application.
	 */
	
	AirlineControllerInterface airlineController = new AirlineController();
	BookingControllerInterface bookingController = new BookingController();
	FlightControllerInterface flightController = new FlightController();
	PassengerControllerInterface passengerController = new PassengerController();
	SystemControllerInterface systemController = new SystemController();
	//static UserInterface userInterface = new UserInterface();
	ReservationSystem system = new ReservationSystem();

	public static void main(String[] args) {
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI window = new UserGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 /* Create the application.
	 */
	public UserGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		frame.setBounds(100, 100, 480, 392);
		frame.setTitle("Flight Reservations");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblFlightReservationSystem = new JLabel("Flight Reservation System");
		lblFlightReservationSystem.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFlightReservationSystem.setBounds(124, 11, 234, 44);
		frame.getContentPane().add(lblFlightReservationSystem);
		
		//instantiate card layout
		cardLayout = new CardLayout();
		
		//instantiate panel to contain cards
		cardPanel = new JPanel();
		cardPanel.setBounds(0, 115, 464, 239);
		frame.getContentPane().add(cardPanel);
		cardPanel.setLayout(cardLayout);
		
		//instantiate home card
		homeCard = new JPanel();
		cardPanel.add(homeCard, "homeCard");
	
		
		//instantiate flight card
		flightCard = new JPanel();
		cardPanel.add(flightCard, "flightCard");
		flightCard.setLayout(null);
		
		//instantiate airline card
		airlineCard = new JPanel();
		cardPanel.add(airlineCard, "airlineCard");
		airlineCard.setLayout(null);
		
		//instantiate passenger card
		passengerCard = new JPanel();
		cardPanel.add(passengerCard, "passengerCard");
		passengerCard.setLayout(null);
		
		//instantiate menu buttons
		btnManageFlights = new JButton("Manage Flights");
		btnManageFlights.setBounds(10, 68, 135, 35);
		frame.getContentPane().add(btnManageFlights);
		btnManageFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "flightCard");
			}
		});
			
		btnManageAirlines = new JButton("Manage Airlines");
		btnManageAirlines.setBounds(155, 68, 135, 35);
		frame.getContentPane().add(btnManageAirlines);
		btnManageAirlines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "airlineCard");
			}
		});
		
		btnManagePassengers = new JButton("Manage Passengers");
		btnManagePassengers.setBounds(299, 68, 156, 35);
		frame.getContentPane().add(btnManagePassengers);
		btnManagePassengers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "passengerCard");
			}
		});
		
		//separator of menu and content
		separator = new JSeparator();
		separator.setBounds(10, 114, 445, 2);
		frame.getContentPane().add(separator);
		
		//content for 'manage airlines'
		lblAirlineCard = new JLabel("Manage Airlines:");
		lblAirlineCard.setBounds(178, 16, 105, 29);
		lblAirlineCard.setFont(new Font("Tahoma", Font.BOLD, 12));
				
		allAirlinesTarget = new JTextArea("");
		allAirlinesTarget.setBounds(207, 60, 219, 127);
		airlineCard.add(allAirlinesTarget);
				
		btnViewAllAirlines = new JButton("View All Airlines");
		btnViewAllAirlines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllAirlines();
			}
			
		});
		btnViewAllAirlines.setBounds(24, 60, 133, 23);
		
		btnAddAirline = new JButton("Add Airline");
		btnAddAirline.setForeground(new Color(0, 128, 0));
		btnAddAirline.setBounds(24, 107, 133, 23);
		btnAddAirline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAirline();
				displayAllAirlines();
			}
		});
		
	
		
		
		//content for 'manage flights'
		lblFlightCard = new JLabel("Manage Flights:");
		lblFlightCard.setBounds(178, 16, 105, 29);
		lblFlightCard.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnViewAllFlights = new JButton("View All Flights");
		btnViewAllFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayAllFlights();
			}
		});
		btnViewAllFlights.setBounds(24, 60, 133, 23);
		
		allFlightsTarget = new JTextArea("");
		allFlightsTarget.setBounds(207, 60, 219, 127);
		flightCard.add(allFlightsTarget);
		
		btnAddFlight = new JButton("Add Flight");
		btnAddFlight.setForeground(new Color(0, 128, 0));
		btnAddFlight.setBounds(24, 94, 133, 23);
		btnAddFlight.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				addFlight();
				displayAllFlights();
			}
		});
		
		txtSelectAFlight  = new JTextArea("Select a flight to view passengers:");
		txtSelectAFlight.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtSelectAFlight.setBackground(SystemColor.menu);
		txtSelectAFlight.setLineWrap(true);
		txtSelectAFlight.setBounds(24, 141, 133, 34);
		
		
		
		
		//content for 'manage passengers'
		lblPassengerCard = new JLabel("Manage Passengers:");
		lblPassengerCard.setBounds(178, 16, 140, 29);
		lblPassengerCard.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		btnMakeABooking = new JButton("Make a Booking");
		btnMakeABooking.setForeground(new Color(0, 128, 0));
		btnMakeABooking.setBounds(166, 68, 152, 38);
		
		btnCancelABooking = new JButton("Cancel a Booking");
		btnCancelABooking.setForeground(new Color(255, 0, 0));
		btnCancelABooking.setBounds(166, 117, 152, 38);
		btnCancelABooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		// add components to airline card
		airlineCard.add(btnViewAllAirlines);
		airlineCard.add(btnAddAirline);
		airlineCard.add(lblAirlineCard);
		
		
		// add components to flight card
		flightCard.add(lblFlightCard);
		flightCard.add(btnAddFlight);
		flightCard.add(btnViewAllFlights);
		flightCard.add(txtSelectAFlight);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(24, 177, 105, 20);
		flightCard.add(comboBox);
		
		JButton button = new JButton(">");
		button.setForeground(new Color(50, 205, 50));
		button.setBounds(139, 176, 41, 23);
		flightCard.add(button);
		
	
		// add components to passenger card
		passengerCard.add(lblPassengerCard);
		passengerCard.add(btnMakeABooking);
		passengerCard.add(btnCancelABooking);
		
		
	}
	
	private void addAirline() {
		lblAddNewAirline = new JLabel("Enter name of airline:");
		lblAddNewAirline.setBounds(24, 110, 133, 14);
		textFieldAddAirline = new JTextField();
		textFieldAddAirline.setBounds(24, 135, 133, 20);
		textFieldAddAirline.setColumns(10);
	      
	    addAirlinePopup = new JPanel();
	    addAirlinePopup.add(lblAddNewAirline);
	    addAirlinePopup.add(textFieldAddAirline);
	     
	    UIManager.put("OptionPane.okButtonText", "Add");

	      int result = JOptionPane.showConfirmDialog(null, addAirlinePopup, 
	               "Add airline", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	         System.out.println("New airline: " + textFieldAddAirline.getText());
	       
	         Airline newAirline = new Airline(textFieldAddAirline.getText());
	         systemController.addAirline(newAirline);
	        
	         System.out.println(systemController.displayAllAirlines());
	      }
	}
	
	private void addFlight() {
		lblAddFlightNumber = new JLabel("Flight number:");
		lblAddFlightNumber.setBounds(24, 110, 133, 14);
		
		textFieldAddFlightNo = new JTextField();
		textFieldAddFlightNo.setBounds(24, 135, 133, 20);
		textFieldAddFlightNo.setColumns(10);
		
		lblAddDeparture = new JLabel("Departs from:" );
		lblAddDeparture.setBounds(24, 110, 133, 14);
		
		lblAddDestination = new JLabel("Destination:");
		lblAddDestination.setBounds(24, 110, 133, 14);
		
		destinationSelector = new JComboBox(DeptDest.values());
		
		departureSelector = new JComboBox(DeptDest.values());
		
		lblAddFlightAirline = new JLabel("Airline:");
		lblAddFlightAirline.setBounds(24, 110, 133, 14);
		
		textFieldAddFlightAirline = new JTextField();
		textFieldAddFlightAirline.setBounds(24, 135, 133, 20);
		textFieldAddFlightAirline.setColumns(10);
		
	    addFlightPopup = new JPanel(new GridLayout(0, 2,20,20));
	    addFlightPopup.add(lblAddFlightNumber);
	    addFlightPopup.add(textFieldAddFlightNo);
	    addFlightPopup.add(lblAddDeparture);
	    addFlightPopup.add(lblAddDestination);
	    addFlightPopup.add(departureSelector);
	    addFlightPopup.add(destinationSelector);
	    addFlightPopup.add(lblAddFlightAirline);
	    addFlightPopup.add(textFieldAddFlightAirline);
	     
	    UIManager.put("OptionPane.okButtonText", "Add");
	    
	      int result = JOptionPane.showConfirmDialog(frame, addFlightPopup, 
	               "Add flight", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	        
	         Flight newFlight = new Flight (textFieldAddFlightNo.getText(), departureSelector.getSelectedItem().toString(), destinationSelector.getSelectedItem().toString(), textFieldAddFlightAirline.getText(), null, null );
	         System.out.println(newFlight.destination);
	         systemController.addFlight(newFlight);
	         displayAllAirlines();
	      }
	}
	
	private void displayAllAirlines() {
		String AllAirlinesOutput=systemController.displayAllAirlines();
		System.out.println(AllAirlinesOutput);
		allAirlinesTarget.setText(AllAirlinesOutput + "\n");
	}
	
	private void displayAllFlights() {
		String AllFlightsOutput=systemController.displayAllFlights();
		System.out.println(AllFlightsOutput);
		allFlightsTarget.setText(AllFlightsOutput + "\n");
	}
}
