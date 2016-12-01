package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import controller.InvalidInputException;
import model.FTMS;
import model.Staff;

public class StaffSchedulePage extends JFrame {

	private static final long serialVersionUID = 4051431489190676309L;

	/* UI elements */
	private JLabel errorMessage;
	// for create staff
	private JLabel staffNameLabel;
	private JLabel staffRoleLabel;
	private JTextField staffNameTextField;
	private JTextField staffRoleTextField;
	private JButton addStaffButton;
	// for create time block
	private JLabel tbTimeLabel;
	private JLabel tbDayOfWeekLabel;
	private JLabel tbStaffLabel;
	private JComboBox<String> tbTimeList;
	private JComboBox<String> tbDayOfWeekList;
	private JComboBox<String> tbStaffList;
	private JButton addTBButton;
	// for display staff
	private JList<String> staffList;
	private ArrayList<String> staffNames;
	private JScrollPane staffScrollPane;
	private JTextArea staffInfo;
	private String staffName;
	private String staffRole;
	// for display schedule
	private JLabel mondayLabel;
	private JLabel tuesdayLabel;
	private JLabel wednesdayLabel;
	private JLabel thursdayLabel;
	private JLabel fridayLabel;
	private JLabel saturdayLabel;
	private JLabel sundayLabel;
	private JTextArea mondayInfo;
	private JTextArea tuesdayInfo;
	private JTextArea wednesdayInfo;
	private JTextArea thursdayInfo;
	private JTextArea fridayInfo;
	private JTextArea saturdayInfo;
	private JTextArea sundayInfo;
	// data elements
	private String error = null;
	private Integer selectedTime = -1;
	// 34 elements
	private String[] timeBlocks = {"5:00 AM - 5:29 AM", "5:30 AM - 5:59 AM", "6:00 AM - 6:29 AM", "6:30 AM - 6:59 AM",
			"7:00 AM - 7:29 AM", "7:30 AM - 7:59 AM", "8:00 AM - 8:29 AM", "8:30 AM - 8:59 AM", "9:00 AM - 9:29 AM",
			"9:30 AM - 9:59 AM", "10:00 AM - 10:29 AM", "10:30 AM - 10:59 AM", "11:00 AM - 11:29 AM", "11:30 AM - 11:59 AM",
			"12:00 PM - 12:29 PM", "12:30 PM - 12:59 PM", "1:00 PM - 1:29 PM", "1:30 PM - 1:59 PM", "2:00 PM - 2:29 PM",
			"2:30 PM - 2:59 PM", "3:00 PM - 3:29 PM", "3:30 PM - 3:59 PM", "4:00 PM - 4:29 PM", "4:30 PM - 4:59 PM",
			"5:00 PM - 5:29 PM", "5:30 PM - 5:59 PM", "6:00 PM - 6:29 PM", "6:30 PM - 6:59 PM", "7:00 PM - 7:29 PM",
			"7:30 PM - 7:59 PM", "8:00 PM - 8:29 PM", "8:30 PM - 8:59 PM", "9:00 PM - 9:29 PM", "9:30 PM - 9:59PM"};
	private Integer selectedTBStaff = -1;
	private HashMap<Integer, Staff> staff;
	private Integer selectedTBDayOfWeek = -1;
	private String[] dayOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	/* Creates new form StaffSchedulePage */
	public StaffSchedulePage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for create staff
		staffNameLabel = new JLabel();
		staffRoleLabel = new JLabel();
		staffNameTextField = new JTextField();
		staffRoleTextField = new JTextField();
		addStaffButton = new JButton();

		// elements for create time block
		tbTimeLabel = new JLabel();
		tbDayOfWeekLabel = new JLabel();
		tbStaffLabel = new JLabel();
		tbTimeList= new JComboBox<String>(timeBlocks);
		tbTimeList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTime = cb.getSelectedIndex();
			}
		});
		tbDayOfWeekList = new JComboBox<String>(dayOfWeek);
		tbDayOfWeekList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTBDayOfWeek = cb.getSelectedIndex();
			}
		});
		tbStaffList = new JComboBox<String>(new String[0]);
		tbStaffList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedTBStaff = cb.getSelectedIndex();
			}
		});
		addTBButton = new JButton();

		// elements for display staff
		staffNames = new ArrayList<String>();
		staffList = new JList<String>();
		staffList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		staffScrollPane = new JScrollPane(staffList);
		staffInfo = new JTextArea();
		staffName = "";
		staffRole = "";

		// elements for display schedule
		mondayLabel = new JLabel();
		tuesdayLabel = new JLabel();
		wednesdayLabel = new JLabel();
		thursdayLabel = new JLabel();
		fridayLabel = new JLabel();
		saturdayLabel = new JLabel();
		sundayLabel = new JLabel();
		mondayInfo = new JTextArea();
		tuesdayInfo = new JTextArea();
		wednesdayInfo = new JTextArea();
		thursdayInfo = new JTextArea();
		fridayInfo = new JTextArea();
		saturdayInfo = new JTextArea();
		sundayInfo = new JTextArea();

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Staff & Schedule");

		// create staff settings and listener
		staffNameLabel.setText("Name: ");
		staffRoleLabel.setText("Role :");
		addStaffButton.setText("Add Staff");
		addStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addStaffButtonActionPerformed(evt);
			}
		});

		// create time block settings and listener
		tbTimeLabel.setText("Choose Time: ");
		tbDayOfWeekLabel.setText("Day: ");
		tbStaffLabel.setText("Staff: ");
		addTBButton.setText("Add Time Block");
		addTBButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addTimeBlockButtonActionPerformed(evt);
			}
		});

		// display schedule settings
		mondayLabel.setText("Monday");
		tuesdayLabel.setText("Tuesday");
		wednesdayLabel.setText("Wednesday");
		thursdayLabel.setText("Thursday");
		fridayLabel.setText("Friday");
		saturdayLabel.setText("Saturday");
		sundayLabel.setText("Sunday");
		// create list listeners
		staffList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				staffListValueChanged(evt);
			}
		});
		// layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(staffNameLabel)
								.addComponent(staffRoleLabel)
								.addComponent(tbTimeLabel)
								.addComponent(tbDayOfWeekLabel)
								.addComponent(tbStaffLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(staffNameTextField, 200, 200, 400)
								.addComponent(staffRoleTextField, 200, 200, 400)
								.addComponent(addStaffButton)
								.addComponent(tbTimeList)
								.addComponent(tbDayOfWeekList)
								.addComponent(tbStaffList)
								.addComponent(addTBButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(staffScrollPane, 200, 200, 400)
								.addComponent(staffInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(mondayLabel)
								.addComponent(mondayInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(tuesdayLabel)
								.addComponent(tuesdayInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(wednesdayLabel)
								.addComponent(wednesdayInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(thursdayLabel)
								.addComponent(thursdayInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(fridayLabel)
								.addComponent(fridayInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(saturdayLabel)
								.addComponent(saturdayInfo))
						.addGroup(layout.createParallelGroup()
								.addComponent(sundayLabel)
								.addComponent(sundayInfo))
						)
				);
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addTBButton, addStaffButton, staffNameTextField, staffRoleTextField} );
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {staffNameTextField, tbTimeList, tbDayOfWeekList, tbStaffList});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {staffScrollPane, staffInfo});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, sundayLabel} );

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup()
										.addComponent(staffNameLabel)
										.addComponent(staffNameTextField))
								.addGroup(layout.createParallelGroup()
										.addComponent(staffRoleLabel)
										.addComponent(staffRoleTextField))
								.addGroup(layout.createParallelGroup()
										.addComponent(addStaffButton))
								.addGroup(layout.createParallelGroup()
										.addComponent(tbTimeLabel)
										.addComponent(tbTimeList))
								.addGroup(layout.createParallelGroup()
										.addComponent(tbDayOfWeekLabel)
										.addComponent(tbDayOfWeekList))
								.addGroup(layout.createParallelGroup()
										.addComponent(tbStaffLabel)
										.addComponent(tbStaffList))
								.addGroup(layout.createParallelGroup()
										.addComponent(addTBButton)))
						.addGroup(layout.createSequentialGroup()
								.addComponent(staffScrollPane, 200, 200, 400)
								.addComponent(staffInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(mondayLabel)
								.addComponent(mondayInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(tuesdayLabel)
								.addComponent(tuesdayInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(wednesdayLabel)
								.addComponent(wednesdayInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(thursdayLabel)
								.addComponent(thursdayInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(fridayLabel)
								.addComponent(fridayInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(saturdayLabel)
								.addComponent(saturdayInfo))
						.addGroup(layout.createSequentialGroup()
								.addComponent(sundayLabel)
								.addComponent(sundayInfo)))
				);

		pack();
	}

	private void refreshData() {
		FTMS m = FTMS.getInstance();
		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {
			// update staff info
			String text = String.format("Name: %s\nRole: %s", staffName, staffRole);
			staffInfo.setText(text);

			// update lists
			DefaultListModel model1 = new DefaultListModel();
			staffNames.removeAll(staffNames);
			for (int x = 0; x < m.getStaffs().size(); x++)
				staffNames.add(m.getStaff(x).getName());
			for (int x = 0; x < staffNames.size(); x++)
				model1.addElement(staffNames.get(x));
			staffList.setModel(model1);
			staffList.setVisibleRowCount(staffNames.size());

			staff = new HashMap<Integer, Staff>();
			tbStaffList.removeAllItems();
			Iterator<Staff> stIt = m.getStaffs().iterator();
			Integer index = 0;
			while (stIt.hasNext()) {
				Staff s = stIt.next();
				staff.put(index, s);
				tbStaffList.addItem(s.getName());
				index++;
			}
			// reset the inputs
			staffNameTextField.setText("");
			staffRoleTextField.setText("");
			selectedTBDayOfWeek = -1;
			tbDayOfWeekList.setSelectedIndex(selectedTBDayOfWeek);
		}
		selectedTime = -1;
		tbTimeList.setSelectedIndex(selectedTime);
		selectedTBStaff = -1;
		tbStaffList.setSelectedIndex(selectedTBStaff);
		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	public void addStaffButtonActionPerformed(ActionEvent evt) {
		// call the controller
		Controller c = new Controller();
		error = null;
		try {
			c.createStaff(staffNameTextField.getText(), staffRoleTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	public void addTimeBlockButtonActionPerformed(ActionEvent evt) {
		FTMS m = FTMS.getInstance();
		// call the controller
		Controller c = new Controller();
		error = null;
		// JSpinner actually returns a date and time
		// force the same date for start and end time to ensure that only the times differ
		Calendar cal = Calendar.getInstance();
		Time sTime = null;
		Time eTime = null;
		if (tbTimeList.getSelectedIndex() >= 0) {
			switch (tbTimeList.getSelectedIndex()) {
			case 0 :
				cal.set(2000, 1, 1, 5, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 5, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 1 :
				cal.set(2000, 1, 1, 5, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 5, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 2 :
				cal.set(2000, 1, 1, 6, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 6, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 3 :
				cal.set(2000, 1, 1, 6, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 6, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 4 :
				cal.set(2000, 1, 1, 7, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 7, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 5 :
				cal.set(2000, 1, 1, 7, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 7, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 6 :
				cal.set(2000, 1, 1, 8, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 8, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 7 :
				cal.set(2000, 1, 1, 8, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 8, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 8 :
				cal.set(2000, 1, 1, 9, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 9, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 9 :
				cal.set(2000, 1, 1, 9, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 9, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 10 :
				cal.set(2000, 1, 1, 10, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 10, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 11 :
				cal.set(2000, 1, 1, 10, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 10, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 12 :
				cal.set(2000, 1, 1, 11, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 11, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 13 :
				cal.set(2000, 1, 1, 11, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 11, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 14 :
				cal.set(2000, 1, 1,12, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 12, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 15 :
				cal.set(2000, 1, 1, 12, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 12, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 16 :
				cal.set(2000, 1, 1, 13, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 13, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 17 :
				cal.set(2000, 1, 1, 13, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 13, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 18 :
				cal.set(2000, 1, 1, 14, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 14, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 19 :
				cal.set(2000, 1, 1, 14, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 14, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 20 :
				cal.set(2000, 1, 1, 15, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 15, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 21 :
				cal.set(2000, 1, 1, 15, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 15, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 22 :
				cal.set(2000, 1, 1, 16, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 16, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 23 :
				cal.set(2000, 1, 1, 16, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 16, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 24 :
				cal.set(2000, 1, 1, 17, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 17, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 25 :
				cal.set(2000, 1, 1, 17, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 17, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 26 :
				cal.set(2000, 1, 1, 18, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 18, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 27 :
				cal.set(2000, 1, 1, 18, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 18, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 28 :
				cal.set(2000, 1, 1, 19, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 19, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 29 :
				cal.set(2000, 1, 1, 19, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 19, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 30 :
				cal.set(2000, 1, 1, 20, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 20, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 31 :
				cal.set(2000, 1, 1, 20, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 20, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 32 :
				cal.set(2000, 1, 1, 21, 00, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 21, 29, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			case 33 :
				cal.set(2000, 1, 1, 21, 30, 0);
				sTime = new Time(cal.getTimeInMillis());
				cal.set(2000, 1, 1, 21, 59, 0);
				eTime = new Time(cal.getTimeInMillis());
				break;
			default : 
				sTime = null;
				break;
			}
		}
		Staff tbStaff;
		if(tbStaffList.getSelectedIndex() < 0)
			tbStaff = null;
		else
			tbStaff = m.getStaff(tbStaffList.getSelectedIndex());
		try {
			c.createTimeBlock(sTime, eTime, tbDayOfWeekList.getSelectedIndex(), tbStaff);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	private void staffListValueChanged(ListSelectionEvent evt) {
		FTMS m = FTMS.getInstance();
		staffName = m.getStaff(staffList.getSelectedIndex()).getName();
		staffRole = m.getStaff(staffList.getSelectedIndex()).getRole();
		// update visuals
		refreshData();
	}
}
