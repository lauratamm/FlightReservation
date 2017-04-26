package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import controller.AirlineController;
import controller.BookingController;
import controller.FlightController;
import controller.PassengerController;
import enumerator.Locations;
import model.Airline;
import model.Booking;
import model.Flight;
import java.text.DateFormat;

public class UserGUI implements Observer {

	public JFrame frame;
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
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private JPanel homeCard;
	private JPanel passengerCard;
	private JPanel airlineCard;
	private JPanel flightCard;
	private JButton btnViewAllFlights;
	private JLabel lblPassengerCard;
	private JButton btnMakeABooking;
	private JButton btnCancelABooking;
	private JButton btnAddFlight;
	private JTextField textFieldAddAirline;
	private JPanel addAirlinePopup;
	private JPanel addFlightPopup;
	private JComboBox departureSelector;
	private JLabel lblAddFlightNumber;
	private JTextField textFieldAddFlightNo;
	private JLabel lblAddDeparture;
	private JComboBox destinationSelector;
	private JLabel lblAddDestination;
	private JLabel lblAddFlightAirline;
	private JComboBox textFieldAddFlightAirline;
	private JTextArea allAirlinesTarget;
	private JTextArea txtSelectAFlight;
	private DefaultTableModel tableModel;
	private JTextArea allPassengersTarget;
	private JTable table;
	private JPanel viewPassengersPopup;
	private JComboBox selectDeparture;
	private JComboBox selectDestination;
	private JTextField AddPassengerFirstname;
	private JTextField AddPassengerLastname;
	private JLabel lblAddFlightDate;
	private JTextField textFieldAddFlightDate;
	private JLabel lblAddFlightTakeoffTime;
	private JTextField textFieldAddTakeoffTime;
	private JLabel lblAddFlightLandingTime;
	private JTextField textFieldAddLandingTime;
	private JComboBox selectDate;
	private JPanel bookingPanel;
	private JLabel lblBookingRef;
	private JTextField textFieldBookingRef;
	private JPanel cancelBookingPopup;
	private JLabel lblLoadData;
	
	//instantiate controllers
	AirlineController airlineController = AirlineController.getInstance();
	BookingController bookingController = BookingController.getInstance();
	FlightController flightController = FlightController.getInstance();
	PassengerController passengerController = PassengerController.getInstance();



	//add GUI as an observer 
	public UserGUI() {
		airlineController.addObserver(this);
		bookingController.addObserver(this);
		flightController.addObserver(this);
		passengerController.addObserver(this);

		initialize();
	}


	private void initialize() {
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "OK");
		//give the option to load data or start with a blank system
		if (loadDataOrBlankSystem()){
		//deserialize 
		airlineController.getAirlineList().addAll(airlineController.deserialize(airlineController.getAirlineList(), airlineController.fileName));
		flightController.getFlightList().addAll(flightController.deserialize(flightController.getFlightList(), flightController.fileName));
		passengerController.getPassengerList().addAll(passengerController.deserialize(passengerController.getPassengerList(), passengerController.fileName));
		bookingController.getBookingList().addAll(bookingController.deserialize(bookingController.getBookingList(), bookingController.fileName));
		}
		
		
		
		//initialize contents
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.controlHighlight);
		frame.getContentPane().setLayout(null);

		frame.setBounds(100, 100, 572, 392);
		frame.setTitle("Flight Reservations");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lblFlightReservationSystem = new JLabel("Flight Reservation System");
		lblFlightReservationSystem.setFont(new Font("Verdana", Font.BOLD, 13));
		lblFlightReservationSystem.setBounds(188, 11, 234, 44);
		frame.getContentPane().add(lblFlightReservationSystem);

		// instantiate card layout
		cardLayout = new CardLayout();

		// instantiate panel to contain cards
		cardPanel = new JPanel();
		cardPanel.setBounds(0, 115, 550, 239);
		frame.getContentPane().add(cardPanel);
		cardPanel.setLayout(cardLayout);

		// instantiate flight card
		flightCard = new JPanel();
		flightCard.setBackground(SystemColor.controlHighlight);
		cardPanel.add(flightCard, "flightCard");
		flightCard.setLayout(null);

		// instantiate airline card
		airlineCard = new JPanel();
		airlineCard.setBackground(SystemColor.controlHighlight);
		cardPanel.add(airlineCard, "airlineCard");
		airlineCard.setLayout(null);

		// instantiate passenger card
		passengerCard = new JPanel();
		passengerCard.setBackground(SystemColor.controlHighlight);
		cardPanel.add(passengerCard, "passengerCard");
		passengerCard.setLayout(null);

		// instantiate menu buttons
		btnManageFlights = new JButton("Manage Flights");
		btnManageFlights.setBounds(22, 69, 135, 35);
		frame.getContentPane().add(btnManageFlights);
		btnManageFlights.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "flightCard");
			}
		});

		btnManageAirlines = new JButton("Manage Airlines");
		btnManageAirlines.setBounds(202, 69, 135, 35);
		frame.getContentPane().add(btnManageAirlines);
		btnManageAirlines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "airlineCard");
			}
		});

		btnManagePassengers = new JButton("Manage Passengers");
		btnManagePassengers.setBounds(378, 69, 156, 35);
		frame.getContentPane().add(btnManagePassengers);
		btnManagePassengers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "passengerCard");
			}
		});

		// separator of menu and content
		separator = new JSeparator();
		separator.setBounds(10, 115, 536, 1);
		frame.getContentPane().add(separator);

		// content for 'manage airlines'
		lblAirlineCard = new JLabel("Manage Airlines:");
		lblAirlineCard.setBounds(222, 11, 105, 29);
		lblAirlineCard.setFont(new Font("Tahoma", Font.BOLD, 12));

		allAirlinesTarget = new JTextArea("");
		allAirlinesTarget.setBounds(135, 49, 279, 127);
		airlineCard.add(allAirlinesTarget);

		btnViewAllAirlines = new JButton("View All Airlines");
		btnViewAllAirlines.setBounds(138, 187, 133, 23);
		btnViewAllAirlines.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayAllAirlines();
			}
		});

		btnAddAirline = new JButton("Add Airline");
		btnAddAirline.setForeground(new Color(0, 128, 0));
		btnAddAirline.setBounds(281, 187, 133, 23);
		btnAddAirline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchAirlinePopup();
			}
		});

		// content for 'manage flights'
		lblFlightCard = new JLabel("Manage Flights:");
		lblFlightCard.setBounds(227, 11, 105, 29);
		lblFlightCard.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnViewAllFlights = new JButton("View All Flights");
		btnViewAllFlights.setBounds(24, 60, 133, 23);
		btnViewAllFlights.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				displayAllFlights();
			}
		});

		btnAddFlight = new JButton("Add Flight");
		btnAddFlight.setForeground(new Color(0, 128, 0));
		btnAddFlight.setBounds(24, 94, 133, 23);
		btnAddFlight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchFlightPopup();
			}
		});

		tableModel = new DefaultTableModel(6, 6);
		table = new JTable(tableModel);
		table.setShowGrid(false);
		table.setBackground(Color.WHITE);
		table.setShowVerticalLines(false);
		table.setBounds(191, 64, 336, 138);
		flightCard.add(table);

		JButton viewPassengers = new JButton("View Passengers");
		viewPassengers.setBounds(24, 180, 157, 23);
		viewPassengers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchPassengersPopup();
			}
		});

		// content for 'manage passengers'
		lblPassengerCard = new JLabel("Manage Passengers:");
		lblPassengerCard.setBounds(209, 11, 140, 29);
		lblPassengerCard.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnMakeABooking = new JButton("Make a Booking");
		btnMakeABooking.setForeground(new Color(0, 128, 0));
		btnMakeABooking.setBounds(197, 65, 152, 38);
		btnMakeABooking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchRoutePopup();
			}
		});

		btnCancelABooking = new JButton("Cancel a Booking");
		btnCancelABooking.setForeground(new Color(255, 0, 0));
		btnCancelABooking.setBounds(197, 126, 152, 38);
		btnCancelABooking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launchCancelBooking();
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
		flightCard.add(viewPassengers);

		// add components to passenger card
		passengerCard.add(lblPassengerCard);
		passengerCard.add(btnMakeABooking);
		passengerCard.add(btnCancelABooking);

		// instantiate home card
		homeCard = new JPanel();
		homeCard.setBounds(0, 115, 464, 239);
		frame.getContentPane().add(homeCard);
	}

	private void launchAirlinePopup() {
		lblAddNewAirline = new JLabel("Enter name of airline:");
		lblAddNewAirline.setBounds(24, 110, 133, 14);
		textFieldAddAirline = new JTextField();
		textFieldAddAirline.setBounds(24, 135, 133, 20);
		textFieldAddAirline.setColumns(10);

		addAirlinePopup = new JPanel();
		addAirlinePopup.add(lblAddNewAirline);
		addAirlinePopup.add(textFieldAddAirline);

		UIManager.put("OptionPane.okButtonText", "Add");

		int result = JOptionPane.showConfirmDialog(null, addAirlinePopup, "Add airline", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (!airlineController.validateAirlineFieldNotEmpty(textFieldAddAirline.getText())) {
				reportMessage("Enter airline name");
				launchAirlinePopup();
			}
		}
	}

	private void launchFlightPopup() {
		lblAddFlightNumber = new JLabel("Flight number:");
		lblAddFlightNumber.setBounds(24, 110, 133, 14);

		textFieldAddFlightNo = new JTextField();
		textFieldAddFlightNo.setBounds(24, 135, 133, 20);
		textFieldAddFlightNo.setColumns(10);

		lblAddDeparture = new JLabel("Departs from:");
		lblAddDeparture.setBounds(24, 110, 133, 14);

		lblAddDestination = new JLabel("Destination:");
		lblAddDestination.setBounds(24, 110, 133, 14);

		destinationSelector = new JComboBox(Locations.values());

		departureSelector = new JComboBox(Locations.values());

		lblAddFlightAirline = new JLabel("Airline:");
		lblAddFlightAirline.setBounds(24, 110, 133, 14);

		textFieldAddFlightAirline = new JComboBox(airlineController.getAllAirlineNames());
		textFieldAddFlightAirline.setBounds(24, 135, 133, 20);

		lblAddFlightDate = new JLabel("Date (dd/MM/yy): ");
		lblAddFlightDate.setBounds(24, 110, 133, 14);

		textFieldAddFlightDate = new JTextField();
		textFieldAddFlightDate.setBounds(24, 135, 133, 20);
		textFieldAddFlightDate.setColumns(10);

		lblAddFlightTakeoffTime = new JLabel("Departure time (hh:mm): ");
		lblAddFlightTakeoffTime.setBounds(24, 110, 133, 14);

		textFieldAddTakeoffTime = new JTextField();
		textFieldAddTakeoffTime.setBounds(24, 135, 133, 20);
		textFieldAddTakeoffTime.setColumns(10);

		lblAddFlightLandingTime = new JLabel("Landing time (hh:mm): ");
		lblAddFlightLandingTime.setBounds(24, 110, 133, 14);

		textFieldAddLandingTime = new JTextField();
		textFieldAddLandingTime.setBounds(24, 135, 133, 20);
		textFieldAddLandingTime.setColumns(10);

		addFlightPopup = new JPanel(new GridLayout(0, 2, 20, 20));
		addFlightPopup.add(lblAddFlightNumber);
		addFlightPopup.add(textFieldAddFlightNo);
		addFlightPopup.add(lblAddDeparture);
		addFlightPopup.add(lblAddDestination);
		addFlightPopup.add(departureSelector);
		addFlightPopup.add(destinationSelector);
		addFlightPopup.add(lblAddFlightAirline);
		addFlightPopup.add(textFieldAddFlightAirline);
		addFlightPopup.add(lblAddFlightDate);
		addFlightPopup.add(textFieldAddFlightDate);
		addFlightPopup.add(lblAddFlightTakeoffTime);
		addFlightPopup.add(textFieldAddTakeoffTime);
		addFlightPopup.add(lblAddFlightLandingTime);
		addFlightPopup.add(textFieldAddLandingTime);

		UIManager.put("OptionPane.okButtonText", "Add");

		int result = JOptionPane.showConfirmDialog(frame, addFlightPopup, "Add flight", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String flightNumber = textFieldAddFlightNo.getText();
			String takeoffTime = textFieldAddTakeoffTime.getText();
			String date = textFieldAddFlightDate.getText();
			String landingTime = textFieldAddLandingTime.getText();
			String departsFrom = departureSelector.getSelectedItem().toString();
			String destination = destinationSelector.getSelectedItem().toString();
			String airline;
			try {
				airline = textFieldAddFlightAirline.getSelectedItem().toString();

			} catch (Exception e) {
				reportMessage("Create an airline before proceeding");
				return;
			}

			if (!flightController.validateFlight(flightNumber, date, takeoffTime, landingTime, departsFrom, destination,
					airline)) {
				reportMessage("Invalid entry");
				launchFlightPopup();
			}
		}
	}

	private void displayAllAirlines() {
		if (airlineController.getAirlineList() != null) {
			String AllAirlinesOutput = "";
			for (Airline tempAirline : airlineController.getAirlineList()) {
				AllAirlinesOutput = AllAirlinesOutput + tempAirline.getAirlineName() + "\n";
			}
			allAirlinesTarget.setText(AllAirlinesOutput + "\n");
		}
	}

	private void displayAllFlights() {
		// empty table
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		// display flights in a table row
		for (int i = 0; i < flightController.getFlightList().size(); i++) {
			String flightnumber = flightController.getFlight(i).getFlightNumber();
			String depart = flightController.getFlight(i).getDepartsFrom();
			String destination = flightController.getFlight(i).getDestination();
			String airline = flightController.getFlight(i).getAirline().getAirlineName();

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat timeFormat = new SimpleDateFormat("HH:mm");
			String date = dateFormat.format(flightController.getFlight(i).getTakeOffTime());
			String time = timeFormat.format(flightController.getFlight(i).getLandingTime());

			Object[] data = { flightnumber, depart, destination, date, time, airline };
			tableModel.insertRow(i, data);
		}
	}

	private void launchPassengersPopup() {

		int column = 0;
			int row = table.getSelectedRow();
			System.out.println(row);
			String selectedFlightNumber="";
			try{
				selectedFlightNumber = table.getModel().getValueAt(row, column).toString();}
			catch (Exception e){
				reportMessage("Please choose a flight.");
			}
				
			for (Flight tempFlight : flightController.getFlightList()) {
				if (tempFlight.getFlightNumber().equals(selectedFlightNumber)) {
					System.out.println("matches");
	
					allPassengersTarget = new JTextArea();
					allPassengersTarget.setBackground(null);
	
					String AllPassengersOutput = displayAllPassengers(tempFlight);
					allPassengersTarget.setText(AllPassengersOutput + "\n");
	
					lblAddFlightNumber = new JLabel("Flight number:  " + selectedFlightNumber);
					lblAddFlightNumber.setBounds(24, 110, 133, 14);
	
					viewPassengersPopup = new JPanel(new GridLayout(0, 1, 10, 10));
					viewPassengersPopup.add(lblAddFlightNumber);
					viewPassengersPopup.add(allPassengersTarget);
					
					UIManager.put("OptionPane.okButtonText", "OK");
					JOptionPane.showConfirmDialog(frame, viewPassengersPopup, "View Passengers",
							JOptionPane.DEFAULT_OPTION);
	
					}		
			}		
	}

	private String displayAllPassengers(Flight flight) {
		System.out.println(flight.getFlightNumber() + "  flight");
		System.out.println(bookingController.getBookingList().size());
		String output = "";
		for (Booking tempBooking : bookingController.getBookingList()) {
			System.out.println(tempBooking.getFlight().getFlightNumber() + "  temp");
			if (flight.getFlightNumber().equals(tempBooking.getFlight().getFlightNumber())){
				output = output + "Booking ref: "+ tempBooking.getBookingRef() + "   " + tempBooking.getPassenger().getFirstname() + "  "+ tempBooking.getPassenger().getLastname() + "\n";
			}			
		}	
		return output;
	}

	private void launchRoutePopup() {

		JPanel bookingPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		bookingPanel.setBounds(10, 11, 249, 349);

		JLabel lblDepartFrom = new JLabel("Depart from:");
		lblDepartFrom.setBounds(10, 114, 95, 38);
		bookingPanel.add(lblDepartFrom);

		selectDeparture = new JComboBox(Locations.values());
		selectDeparture.setBounds(115, 123, 124, 29);
		bookingPanel.add(selectDeparture);

		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setBounds(10, 163, 95, 30);
		bookingPanel.add(lblDestination);

		selectDestination = new JComboBox(Locations.values());
		selectDestination.setBounds(115, 168, 124, 30);
		bookingPanel.add(selectDestination);

		UIManager.put("OptionPane.okButtonText", "Find flights");

		int result = JOptionPane.showConfirmDialog(frame, bookingPanel, "Find flights", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			String flightDept = selectDeparture.getSelectedItem().toString();
			String flightDest = selectDestination.getSelectedItem().toString();

			if (flightController.findFlightTimes(flightDept, flightDest) != null) {
				launchBookingStep2(flightDept, flightDest, flightController.findFlightTimes(flightDept, flightDest));
			} else
				reportMessage("No flights available for selected locations.");

		}
	}

	private void launchBookingStep2(String departsFrom, String destination, Object[] listOfFlightTimes) {
		bookingPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		bookingPanel.setBounds(10, 11, 249, 349);

		JLabel lblFirstname = new JLabel("First name");
		lblFirstname.setBounds(10, 32, 95, 30);
		bookingPanel.add(lblFirstname);

		AddPassengerFirstname = new JTextField();
		AddPassengerFirstname.setBounds(115, 37, 124, 26);
		bookingPanel.add(AddPassengerFirstname);
		AddPassengerFirstname.setColumns(10);

		JLabel lblLastname = new JLabel("Last name");
		lblLastname.setBounds(10, 73, 95, 30);
		bookingPanel.add(lblLastname);

		AddPassengerLastname = new JTextField();
		AddPassengerLastname.setBounds(115, 78, 124, 25);
		bookingPanel.add(AddPassengerLastname);
		AddPassengerLastname.setColumns(10);

		JLabel lblDate = new JLabel("Date: ");
		lblDate.setBounds(10, 204, 95, 38);
		bookingPanel.add(lblDate);

		selectDate = new JComboBox(listOfFlightTimes);
		selectDate.setBounds(115, 168, 124, 30);
		bookingPanel.add(selectDate);

		JLabel lblSelectClass = new JLabel("Select class:");
		lblSelectClass.setBounds(10, 251, 95, 26);
		bookingPanel.add(lblSelectClass);

		JRadioButton rdbtnBusiness = new JRadioButton("Business");
		rdbtnBusiness.setActionCommand("Business");
		rdbtnBusiness.setBounds(112, 253, 71, 24);
		bookingPanel.add(rdbtnBusiness);

		lblSelectClass = new JLabel();
		lblSelectClass.setBounds(10, 251, 95, 26);
		bookingPanel.add(lblSelectClass);

		JRadioButton rdbtnStandard = new JRadioButton("Standard");
		rdbtnStandard.setActionCommand("Standard");
		rdbtnStandard.setBounds(115, 280, 109, 23);
		bookingPanel.add(rdbtnStandard);

		ButtonGroup selectClass = new ButtonGroup();
		selectClass.add(rdbtnBusiness);
		selectClass.add(rdbtnStandard);
		
		UIManager.put("OptionPane.okButtonText", "Book");

		int result = JOptionPane.showConfirmDialog(frame, bookingPanel, "Find flights", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			try {
				String firstName = AddPassengerFirstname.getText();
				String lastName = AddPassengerLastname.getText();
				String time = selectDate.getSelectedItem().toString();
				String passengerClass = selectClass.getSelection().getActionCommand().toUpperCase();

				switch (bookingController.validateBooking(departsFrom, destination, time, firstName, lastName,
						passengerClass)) {
						case 1:
							reportMessage("Please complete all fields.");
							launchBookingStep2(departsFrom, destination, listOfFlightTimes);
							break;
						case 2:
							reportMessage("Number of bookings exceeded!");
							break;
						case 3:
							reportMessage("Booking successful!");
							break;
				}

			} catch (Exception e) {
				reportMessage("Please complete all fields");
				launchBookingStep2(departsFrom, destination, listOfFlightTimes);
			}
		}
	}

	public void reportMessage(String message) {
		UIManager.put("OptionPane.okButtonText", "OK");
		JOptionPane.showMessageDialog(null, message, "Flight Reservation System", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("update called");
		displayAllAirlines();
		displayAllFlights();
	}
	
	private void launchCancelBooking() {
		lblBookingRef = new JLabel("Enter booking reference to cancel:");
		lblBookingRef.setBounds(24, 110, 133, 14);
		textFieldBookingRef = new JTextField();
		textFieldBookingRef.setBounds(24, 135, 133, 20);
		textFieldBookingRef.setColumns(10);

		cancelBookingPopup = new JPanel();
		cancelBookingPopup.add(lblBookingRef);
		cancelBookingPopup.add(textFieldBookingRef);
	
		UIManager.put("OptionPane.cancelButtonText", "Back");
		UIManager.put("OptionPane.okButtonText", "Cancel Booking");

		int result = JOptionPane.showConfirmDialog(null, cancelBookingPopup, "Cancel Booking", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			if (!bookingController.validateBookingRef(textFieldBookingRef.getText())) {
				reportMessage("Invalid booking reference!");
				launchCancelBooking();
			}
		}
		
	}
	
	public boolean loadDataOrBlankSystem() {
		lblLoadData = new JLabel("Load data?");
		int result = JOptionPane.showConfirmDialog(null, lblLoadData, "Cancel Booking", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) return true;
		else return false;
	}

}
