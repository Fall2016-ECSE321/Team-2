package view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.InvalidInputException;
import model.FTMS;
import controller.Controller;

public class EquipmentSuppliesPage extends JFrame{

	private static final long serialVersionUID = -2054477706332776763L;

	// data elements
	private String error = null;

	// UI elements
	private JLabel errorMessage;
	
	// for create equipment
	private JTextField equipmentNameTextField;
	private JLabel equipmentNameLabel;
	private JTextField equipmentDescriptionTextField;
	private JLabel equipmentDescriptionLabel;
	private JTextField equipmentQuantityTextField;
	private JLabel equipmentQuantityLabel;
	private JButton addEquipmentButton;
	
	// for create supply
	private JLabel supplyNameLabel;
	private JTextField supplyNameTextField;
	private JLabel supplyDescriptionLabel;
	private JTextField supplyDescriptionTextField;
	private JLabel supplyQuantityLabel;
	private JTextField supplyQuantityTextField;
	private JButton addSupplyButton;

	// for display equipment
	private JList equipmentList;
	private ArrayList<String> equipmentNames;
	private JScrollPane equipmentScrollPane;
	private JTextArea equipmentInfo;
	private String equipmentName;
	private String equipmentDescription;
	private String equipmentQuantity;

	// for display supply
	private JList supplyList;
	private ArrayList<String> supplyNames;
	private JScrollPane supplyScrollPane;
	private JTextArea supplyInfo;
	private String supplyName;
	private String supplyDescription;
	private String supplyQuantity;

	// for testing
	private JLabel testLabel;

	/* Creates new form EquipmentSuppliesPage */
	public EquipmentSuppliesPage(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form. */
	private void initComponents(){
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		// elements for create equipment
		equipmentNameTextField = new JTextField();
		equipmentNameLabel = new JLabel();
		equipmentDescriptionTextField = new JTextField();
		equipmentDescriptionLabel = new JLabel();
		equipmentQuantityTextField = new JTextField();
		equipmentQuantityLabel = new JLabel();
		addEquipmentButton = new JButton();
		
		// elements for create supply
		supplyNameTextField = new JTextField();
		supplyNameLabel = new JLabel();
		supplyDescriptionTextField = new JTextField();
		supplyDescriptionLabel = new JLabel();
		supplyQuantityTextField = new JTextField();
		supplyQuantityLabel = new JLabel();
		addSupplyButton = new JButton();
		
		// elements for display equipment
		equipmentNames = new ArrayList<String>();
		equipmentList = new JList();
		equipmentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		equipmentScrollPane = new JScrollPane(equipmentList);
		equipmentInfo = new JTextArea();
		equipmentName = "";
		equipmentDescription = "";
		equipmentQuantity = "";
		
		//elements for display supply
		supplyNames = new ArrayList<String>();
		supplyList = new JList();
		supplyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		supplyScrollPane = new JScrollPane(supplyList);
		supplyInfo = new JTextArea();
		supplyName = "";
		supplyDescription = "";
		supplyQuantity = "";
		
		// elements for testing
		testLabel = new JLabel();
		testLabel.setText("Test");
		
		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Equipment and Supplies");
		
		// create equipment settings and listener
		equipmentNameLabel.setText("Name: ");
		equipmentDescriptionLabel.setText("Description: ");
		equipmentQuantityLabel.setText("Quantity: ");
		addEquipmentButton.setText("Add Equipment");
		addEquipmentButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				addEquipmentButtonActionPerformed(evt);
			}
		});
		
		// create supply settings and listener
		supplyNameLabel.setText("Name: ");
		supplyDescriptionLabel.setText("Description: ");
		supplyQuantityLabel.setText("Quantity: ");
		addSupplyButton.setText("Add Supply");
		addSupplyButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		// create list listeners
		equipmentList.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent evt){
						equipmentListValueChanged(evt);
					}
				});
		supplyList.addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent evt){
						supplyListValueChanged(evt);
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
								.addComponent(addEquipmentButton))
						.addGroup(layout.createParallelGroup()
								.addComponent(supplyNameLabel)
								.addComponent(supplyDescriptionLabel)
								.addComponent(supplyQuantityLabel))
						.addGroup(layout.createParallelGroup()
								.addComponent(supplyNameTextField, 200, 200, 400)
								.addComponent(supplyDescriptionTextField, 200, 200, 400)
								.addComponent(supplyQuantityTextField, 200, 200, 400)
								.addComponent(addSupplyButton)))
				.addGroup(layout.createSequentialGroup()
						.addComponent(equipmentScrollPane)
						.addComponent(supplyScrollPane))
				.addGroup(layout.createSequentialGroup()
						.addComponent(equipmentInfo)
						.addComponent(supplyInfo))
				);

		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addEquipmentButton, equipmentNameTextField});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addSupplyButton, supplyNameTextField});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentNameLabel)
						.addComponent(equipmentNameTextField)
						.addComponent(supplyNameLabel)
						.addComponent(supplyNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentDescriptionLabel)
						.addComponent(equipmentDescriptionTextField)
						.addComponent(supplyDescriptionLabel)
						.addComponent(supplyDescriptionTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentQuantityLabel)
						.addComponent(equipmentQuantityTextField)
						.addComponent(supplyQuantityLabel)
						.addComponent(supplyQuantityTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(addEquipmentButton)
						.addComponent(addSupplyButton))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentScrollPane)
						.addComponent(supplyScrollPane))
				.addGroup(layout.createParallelGroup()
						.addComponent(equipmentInfo)
						.addComponent(supplyInfo))
				);
		pack();
	}

	private void refreshData(){
		FTMS master = FTMS.getInstance();
		// error
		errorMessage.setText(error);
		if(error == null || error.length() == 0){
			// update equipment info
			String text = String.format("Name: %s\nDescription: %s\nQuantity: %s", equipmentName, equipmentDescription, equipmentQuantity);
			equipmentInfo.setText(text);
			
			// update supply info
			text = String.format("Name: %s\nDescription: %s\nQuantity: %s", supplyName, supplyDescription, supplyQuantity);
			supplyInfo.setText(text);
			
			// update  equipment list
			DefaultListModel model1 = new DefaultListModel();
			equipmentNames.removeAll(equipmentNames);
			for(int x = 0; x < master.getEquipments().size(); x++)
				equipmentNames.add(master.getEquipment(x).getName());
			for(int x = 0; x < equipmentNames.size(); x++)
				model1.addElement(equipmentNames.get(x));
			equipmentList.setModel(model1);
			equipmentList.setVisibleRowCount(equipmentNames.size());

			// update supply list
			DefaultListModel model2 = new DefaultListModel();
			supplyNames.removeAll(supplyNames);
			for(int x = 0; x < master.getSupplies().size(); x++)
				supplyNames.add(master.getSupply(x).getName());
			for(int x =0; x < supplyNames.size(); x++)
				model2.addElement(supplyNames.get(x));
			supplyList.setModel(model2);
			supplyList.setVisibleRowCount(supplyNames.size());
			
			// equipment
			equipmentNameTextField.setText("");
			equipmentDescriptionTextField.setText("");
			equipmentQuantityTextField.setText("");

			// supply
			supplyNameTextField.setText("");
			supplyDescriptionTextField.setText("");
			supplyQuantityTextField.setText("");
		}

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

	public void addSupplyButtonActionPerformed(ActionEvent evt){
		// call the controller
		Controller c = new Controller();
		error = null;
		try {
			c.createSupply(supplyNameTextField.getText(), supplyDescriptionTextField.getText(), supplyQuantityTextField.getText());
		} catch(InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}
	
	public void equipmentListValueChanged(ListSelectionEvent evt){
		FTMS master = FTMS.getInstance();
		equipmentName = master.getEquipment(equipmentList.getSelectedIndex()).getName();
		equipmentDescription = master.getEquipment(equipmentList.getSelectedIndex()).getDescription();
		equipmentQuantity = Integer.toString(master.getEquipment(equipmentList.getSelectedIndex()).getQuantity());
		//update visuals
		refreshData();
	}
	
	public void supplyListValueChanged(ListSelectionEvent evt){
		FTMS master = FTMS.getInstance();
		supplyName = master.getSupply(supplyList.getSelectedIndex()).getName();
		supplyDescription = master.getSupply(supplyList.getSelectedIndex()).getDescription();
		supplyQuantity = Integer.toString(master.getSupply(supplyList.getSelectedIndex()).getQuantity());
		//update visuals
		refreshData();
	}
}
