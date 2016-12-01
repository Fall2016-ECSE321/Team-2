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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import controller.InvalidInputException;
import model.FTMS;
import model.MenuItem;

public class OrdersPage extends JFrame{

	private static final long serialVersionUID = 5430196057077191792L;

	// UI elements
	private JLabel errorMessage;

	// for create order
	private JComboBox<String> itemList1;
	private JComboBox<String> itemList2;
	private JComboBox<String> itemList3;
	private JComboBox<String> itemList4;
	private JLabel itemLabel1;
	private JLabel itemLabel2;
	private JLabel itemLabel3;
	private JLabel itemLabel4;
	private JButton addOrderButton;

	// for display order history
	private JList orderList;
	private ArrayList<String> orderNames;
	private JScrollPane  orderScrollPane;
	private JTextArea orderInfo;
	private String orderNumber;
	private String orderItems;

	// refreshButton
	private JButton refreshButton;

	// data elements
	private String error = null;
	private Integer selectedItem1 = -1;
	private Integer selectedItem2 = -1;
	private Integer selectedItem3 = -1;
	private Integer selectedItem4 = -1;
	private HashMap<Integer, MenuItem> items;

	public OrdersPage() {
		initComponents();
		refreshData();
	}

	private void initComponents() {
		// elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);

		// elements for create order
		itemList1 = new JComboBox<String>(new String[0]);
		itemList1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedItem1 = cb.getSelectedIndex();
			}
		});
		itemList2 = new JComboBox<String>(new String[0]);
		itemList2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedItem2 = cb.getSelectedIndex();
			}
		});
		itemList3 = new JComboBox<String>(new String[0]);
		itemList3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedItem3 = cb.getSelectedIndex();
			}
		});
		itemList4 = new JComboBox<String>(new String[0]);
		itemList4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox<String> cb = (JComboBox<String>) evt.getSource();
				selectedItem4 = cb.getSelectedIndex();
			}
		});

		itemLabel1 = new JLabel();
		itemLabel2 = new JLabel();
		itemLabel3 = new JLabel();
		itemLabel4 = new JLabel();
		addOrderButton = new JButton();

		// elements for displaying orders
		orderNames = new ArrayList<String>();
		orderList = new JList();
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderScrollPane = new JScrollPane(orderList);
		orderInfo = new JTextArea();
		orderNumber = "";
		orderItems = "";

		// refresh button
		refreshButton = new JButton();
		refreshButton.setText("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				refreshButtonActionPerformed(evt);
			}
		});

		// global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Orders");

		// create order settings and listener
		itemLabel1.setText("Choose Menu Item");
		itemLabel2.setText("Choose Menu Item");
		itemLabel3.setText("Choose Menu Item");
		itemLabel4.setText("Choose Menu Item");
		addOrderButton.setText("Add Order");
		addOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addOrderButtonActionPerformed(evt);
			}
		});

		// create list listeners
		orderList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				orderListValueChanged(evt);
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
								.addComponent(itemLabel1)
								.addComponent(itemLabel2)
								.addComponent(itemLabel3)
								.addComponent(itemLabel4))
						.addGroup(layout.createParallelGroup()
								.addComponent(itemList1, 200, 200, 200)
								.addComponent(itemList2)
								.addComponent(itemList3)
								.addComponent(itemList4)
								.addComponent(addOrderButton)))
				.addComponent(orderScrollPane)
				.addComponent(orderInfo)
				.addComponent(refreshButton, 300, 300, 400)
				);
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {itemList1, itemList2, itemList3, itemList4});
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {itemList1, addOrderButton});

		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(itemLabel1)
						.addComponent(itemList1))
				.addGroup(layout.createParallelGroup()
						.addComponent(itemLabel2)
						.addComponent(itemList2))
				.addGroup(layout.createParallelGroup()
						.addComponent(itemLabel3)
						.addComponent(itemList3))
				.addGroup(layout.createParallelGroup()
						.addComponent(itemLabel4)
						.addComponent(itemList4))
				.addComponent(addOrderButton)
				.addComponent(orderScrollPane)
				.addComponent(orderInfo)
				.addComponent(refreshButton)
				);
		pack();
	}

	private void refreshData() {

		FTMS m = FTMS.getInstance();

		// error
		errorMessage.setText(error);
		if (error == null || error.length() == 0) {

			// update items
			items = new HashMap<Integer, MenuItem>();
			itemList1.removeAllItems();
			itemList2.removeAllItems();
			itemList3.removeAllItems();
			itemList4.removeAllItems();
			Iterator<MenuItem> mIt = m.getMenuItems().iterator();
			Integer index = 0;
			while (mIt.hasNext()) {
				MenuItem item = mIt.next();
				items.put(index, item);
				itemList1.addItem(item.getName());
				itemList2.addItem(item.getName());
				itemList3.addItem(item.getName());
				itemList4.addItem(item.getName());
				index++;
			}
		}
		// update order info
		String text = String.format("Order Number: %s\nOrder Items:%s", orderNumber, orderItems);
		orderInfo.setText(text);
		// update order list
		DefaultListModel model1 = new DefaultListModel();
		orderNames.removeAll(orderNames);
		for (int x = 0; x < m.getOrders().size(); x++)
			orderNames.add(Integer.toString(m.getOrder(x).getId()));
		for (int x = 0; x < orderNames.size(); x++)
			model1.addElement(orderNames.get(x));
		orderList.setModel(model1);
		orderList.setVisibleRowCount(orderNames.size());
		selectedItem1 = -1;
		selectedItem2 = -1;
		selectedItem3 = -1;
		selectedItem4 = -1;
		itemList1.setSelectedIndex(selectedItem1);
		itemList2.setSelectedIndex(selectedItem2);
		itemList3.setSelectedIndex(selectedItem3);
		itemList4.setSelectedIndex(selectedItem4);
		pack();
	}

	private void addOrderButtonActionPerformed(ActionEvent evt) {
		// call the controller
		Controller c = new Controller();
		error = null;
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		if(selectedItem1 != -1)
			menuItems.add(items.get(selectedItem1));
		if(selectedItem2 != -1)
			menuItems.add(items.get(selectedItem2));
		if(selectedItem3 != -1)
			menuItems.add(items.get(selectedItem3));
		if(selectedItem4 != -1)
			menuItems.add(items.get(selectedItem4));
		try {
			c.createOrder(menuItems);
		} catch (InvalidInputException e){
			error = e.getMessage();
			refreshData();
		}
		// update visuals
		refreshData();
	}

	private void orderListValueChanged(ListSelectionEvent evt) {
		FTMS master = FTMS.getInstance();
		orderNumber = Integer.toString(master.getOrder(orderList.getSelectedIndex()).getId());
		orderItems = "";
		for (int x = 0; x < master.getOrder(orderList.getSelectedIndex()).getMenuItems().size(); x++) {
			orderItems += master.getOrder(orderList.getSelectedIndex()).getMenuItem(x).getName();
			if (x != master.getOrder(orderList.getSelectedIndex()).getMenuItems().size() -1)
				orderItems += ", ";
		}
		// update visuals
		refreshData();
	}
	
	private void refreshButtonActionPerformed(ActionEvent evt) {
		refreshData();
	}
}
