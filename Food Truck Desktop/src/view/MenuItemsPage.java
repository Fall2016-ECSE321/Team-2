package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import controller.Controller;
import controller.InvalidInputException;
import model.Supply;

public class MenuItemsPage extends JFrame {

	private static final long serialVersionUID = -6758362230412641428L;

	// UI elements
	private JLabel errorMessage;

	// for create equipment
	private JTextField menuItemNameTextField;
	private JLabel menuItemNameLabel;
	private JLabel supplyLabel;
	private JComboBox<String> supplyList;
	private JButton addMenuItemButton;

	// for display equipment
	private JList menuItemList;
	private ArrayList<String> menuItemNames;
	private JScrollPane menuItemScrollPane;
	private JTextArea menuItemInfo;
	private String menuItemName;
	private String menuItemDescription;

	// data elements
	private String error = null;
	private Integer selectedSupply = -1;
	private HashMap<Integer, Supply> supplies;

	/* Creates new form MenuItemsPage */
	public MenuItemsPage(){
		initComponents();
		refreshData();
	}

	/* This method is called from within the constructor to initialize the form. */
	private void initComponents(){
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for create menu item
		supplyList = new JComboBox<String>(new String[0]);
		supplyList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply = cb.getSelectedIndex();
			}
		});
		supplyLabel = new JLabel();

		menuItemNameTextField = new JTextField();
		menuItemNameLabel = new JLabel();
		addMenuItemButton = new JButton();

		// elements for display menu items
		menuItemNames = new ArrayList<String>();
		menuItemList = new JList();
		menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuItemScrollPane = new JScrollPane(menuItemList);
		menuItemInfo = new JTextArea();
		menuItemName = "";
		menuItemDescription = "";

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Menu Items");

		// create equipment settings and listener
		menuItemNameLabel.setText("Name: ");
		supplyLabel.setText("Select Supplies: ");
		addMenuItemButton.setText("Add Menu Item");
		addMenuItemButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				addMenuItemButtonActionPerformed(evt);
			}
		});
	}

	private void refreshData(){

	}

	private void addMenuItemButtonActionPerformed(ActionEvent evt){
		// call the controller
		Controller c = new Controller();
		error = null;
		try {
			c.createEquipment(menuItemNameTextField.getText(), );
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}
}

