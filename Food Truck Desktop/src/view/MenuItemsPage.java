package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import model.Supply;

public class MenuItemsPage extends JFrame {

	private static final long serialVersionUID = -6758362230412641428L;

	// UI elements
	private JLabel errorMessage;

	// for create menu item
	private JTextField menuItemNameTextField;
	private JComboBox<String> supplyList1;
	private JComboBox<String> supplyList2;
	private JComboBox<String> supplyList3;
	private JComboBox<String> supplyList4;
	private JLabel menuItemNameLabel;
	private JLabel menuItemSupplyLabel1;
	private JLabel menuItemSupplyLabel2;
	private JLabel menuItemSupplyLabel3;
	private JLabel menuItemSupplyLabel4;
	private JButton addMenuItemButton;

	// for display menu item
	private JList menuItemList;
	private ArrayList<String> menuItemNames;
	private JScrollPane menuItemScrollPane;
	private JTextArea menuItemInfo;
	private String menuItemName;
	private String menuItemIngredients;

	// data elements
	private String error = null;
	private Integer selectedSupply1 = -1;
	private Integer selectedSupply2 = -1;
	private Integer selectedSupply3 = -1;
	private Integer selectedSupply4 = -1;
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
		supplyList1 = new JComboBox<String>(new String[0]);
		supplyList1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply1 = cb.getSelectedIndex();
			}
		});
		supplyList2 = new JComboBox<String>(new String[0]);
		supplyList2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply2 = cb.getSelectedIndex();
			}
		});
		supplyList3 = new JComboBox<String>(new String[0]);
		supplyList3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply3 = cb.getSelectedIndex();
			}
		});
		supplyList4 = new JComboBox<String>(new String[0]);
		supplyList4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedSupply4 = cb.getSelectedIndex();
			}
		});

		menuItemNameTextField = new JTextField();
		menuItemNameLabel = new JLabel();
		menuItemSupplyLabel1 = new JLabel();
		menuItemSupplyLabel2 = new JLabel();
		menuItemSupplyLabel3 = new JLabel();
		menuItemSupplyLabel4 = new JLabel();
		addMenuItemButton = new JButton();

		// elements for display menu items
		menuItemNames = new ArrayList<String>();
		menuItemList = new JList();
		menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuItemScrollPane = new JScrollPane(menuItemList);
		menuItemInfo = new JTextArea();
		menuItemName = "";
		menuItemIngredients = "";

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Menu Items");

		// create equipment settings and listener
		menuItemNameLabel.setText("Name: ");
		menuItemSupplyLabel1.setText("Ingredient 1: ");
		menuItemSupplyLabel2.setText("Ingredient 2: ");
		menuItemSupplyLabel3.setText("Ingredient 3: ");
		menuItemSupplyLabel4.setText("Ingredient 4: ");
		addMenuItemButton.setText("Add Menu Item");
		addMenuItemButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				addMenuItemButtonActionPerformed(evt);
			}
		});

		// create list listeners
		menuItemList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent evt){
				menuItemListValueChanged(evt);
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
								.addComponent(menuItemNameLabel)
								.addComponent(menuItemSupplyLabel1)
								.addComponent(menuItemSupplyLabel2)
								.addComponent(menuItemSupplyLabel3)
								.addComponent(menuItemSupplyLabel4))
						.addGroup(layout.createParallelGroup()
								.addComponent(menuItemNameTextField, 200, 400, 400)
								.addComponent(supplyList1)
								.addComponent(supplyList2)
								.addComponent(supplyList3)
								.addComponent(supplyList4)
								.addComponent(addMenuItemButton)))
						.addComponent(menuItemScrollPane)
						.addComponent(menuItemInfo)
				);
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {menuItemNameTextField, supplyList1, supplyList2, supplyList3, supplyList4, addMenuItemButton});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(menuItemNameLabel)
						.addComponent(menuItemNameTextField))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuItemSupplyLabel1)
						.addComponent(supplyList1))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuItemSupplyLabel2)
						.addComponent(supplyList2))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuItemSupplyLabel3)
						.addComponent(supplyList3))
				.addGroup(layout.createParallelGroup()
						.addComponent(menuItemSupplyLabel4)
						.addComponent(supplyList4))
				.addComponent(addMenuItemButton)
				.addComponent(menuItemScrollPane)
				.addComponent(menuItemInfo)

				);
		pack();
	}

	private void refreshData(){

		FTMS m = FTMS.getInstance();

		// error
		errorMessage.setText(error);
		if(error == null || error.length() == 0){

			// update menu item info
			String text = String.format("Name: %s\nIngredients: %s", menuItemName, menuItemIngredients);
			menuItemInfo.setText(text);
			// update  menu item list
			DefaultListModel model1 = new DefaultListModel();
			menuItemNames.removeAll(menuItemNames);
			for(int x = 0; x < m.getMenuItems().size(); x++)
				menuItemNames.add(m.getMenuItem(x).getName());
			for(int x = 0; x < menuItemNames.size(); x++)
				model1.addElement(menuItemNames.get(x));
			menuItemList.setModel(model1);
			menuItemList.setVisibleRowCount(menuItemNames.size());

			// supply lists
			supplies = new HashMap<Integer, Supply>();
			supplyList1.removeAllItems();
			supplyList2.removeAllItems();
			supplyList3.removeAllItems();
			supplyList4.removeAllItems();
			Iterator<Supply> sIt = m.getSupplies().iterator();
			Integer index = 0;
			while(sIt.hasNext()){
				Supply p = sIt.next();
				supplies.put(index, p);
				supplyList1.addItem(p.getName());
				supplyList2.addItem(p.getName());
				supplyList3.addItem(p.getName());
				supplyList4.addItem(p.getName());
				index++;
			}
			selectedSupply1 = -1;
			selectedSupply2 = -1;
			selectedSupply3 = -1;
			selectedSupply4 = -1;
			supplyList1.setSelectedIndex(selectedSupply1);
			supplyList2.setSelectedIndex(selectedSupply2);
			supplyList3.setSelectedIndex(selectedSupply3);
			supplyList4.setSelectedIndex(selectedSupply4);

			menuItemNameTextField.setText("");
		}

		// this is needed because the size of the window changes depending on whether an error message is shown or not
		pack();
	}

	private void addMenuItemButtonActionPerformed(ActionEvent evt){
		// call the controller
		Controller c = new Controller();
		error = null;
		ArrayList<Supply> ingredients = new ArrayList<Supply>();
		if(selectedSupply1 != -1)
			ingredients.add(supplies.get(selectedSupply1));
		if(selectedSupply2 != -1)
			ingredients.add(supplies.get(selectedSupply2));
		if(selectedSupply3 != -1)
			ingredients.add(supplies.get(selectedSupply3));
		if(selectedSupply4 != -1)
			ingredients.add(supplies.get(selectedSupply4));
		try {
			c.createMenuItem(menuItemNameTextField.getText(), ingredients);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// update visuals
		refreshData();
	}

	public void menuItemListValueChanged(ListSelectionEvent evt){
		FTMS master = FTMS.getInstance();
		menuItemName = master.getMenuItem(menuItemList.getSelectedIndex()).getName();
		menuItemIngredients = "";
		for(int x = 0; x < master.getMenuItem(menuItemList.getSelectedIndex()).getSupplies().size(); x++){
			menuItemIngredients += master.getMenuItem(menuItemList.getSelectedIndex()).getSupply(x).getName();
			if(x != master.getMenuItem(menuItemList.getSelectedIndex()).getSupplies().size() - 1)
				menuItemIngredients += ", ";
		}
		//update visuals
		refreshData();
	}
}

