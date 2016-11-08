package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.InvalidInputException;
import controller.Controller;

public class View extends JFrame{

	private static final long serialVersionUID = -2054477706332776763L;

	// data elements
	private String error = null;

	// UI elements
	private JLabel errorMessage;
	private JTextField equipmentNameTextField;
	private JLabel equipmentNameLabel;
	private JTextField equipmentDescriptionTextField;
	private JLabel equipmentDescriptionLabel;
	private JTextField equipmentQuantityTextField;
	private JLabel equipmentQuantityLabel;
	private JButton addEquipmentButton;

	/* Creates new form EventRegistrationPage */
	public View(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form. */
	private void initComponents(){
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for event
		equipmentNameTextField = new JTextField();
		equipmentNameLabel = new JLabel();
		equipmentDescriptionTextField = new JTextField();
		equipmentDescriptionLabel = new JLabel();
		equipmentQuantityTextField = new JTextField();
		equipmentQuantityLabel = new JLabel();

		addEquipmentButton = new JButton();

		// global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Create Equipment");

		equipmentNameLabel.setText("Name: ");
		equipmentDescriptionLabel.setText("Description: ");
		equipmentQuantityLabel.setText("Quantity: ");
		addEquipmentButton.setText("Add Food Supply");
		addEquipmentButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				addEquipmentButtonActionPerformed(evt);
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
								.addComponent(equipmentNameLabel)
								.addComponent(equipmentDescriptionLabel)
								.addComponent(equipmentQuantityLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(equipmentNameTextField, 200, 200, 400)
								.addComponent(equipmentDescriptionTextField, 200, 200, 400)
								.addComponent(equipmentQuantityTextField, 200, 200, 400)
								.addComponent(addEquipmentButton)))
				);

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addEquipmentButton, equipmentNameTextField});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentNameLabel)
						.addComponent(equipmentNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentDescriptionLabel)
						.addComponent(equipmentDescriptionTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentQuantityLabel)
						.addComponent(equipmentQuantityTextField))
				.addComponent(addEquipmentButton)
				);

		pack();
	}

	private void refreshData(){
		// error
		errorMessage.setText(error);
		// equipment
		equipmentNameTextField.setText("");
		equipmentDescriptionTextField.setText("");
		equipmentQuantityTextField.setText("");

		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	private void addEquipmentButtonActionPerformed(ActionEvent evt){
		// call the controller
		Controller c = new Controller();
		error = null;
		try {
			c.createEquipment(equipmentNameTextField.getText(), equipmentDescriptionTextField.getText(), equipmentQuantityTextField.getText());
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// update visuals
		refreshData();
	}
}
