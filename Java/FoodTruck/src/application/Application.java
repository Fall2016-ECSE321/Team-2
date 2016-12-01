package application;

import persistence.Persistence;
import view.EquipmentSuppliesPage;
import view.MenuItemsPage;
import view.OrdersPage;
import view.StaffSchedulePage;

public class Application {

	public static void main(String[] args) {
		Persistence.loadModel("data.xml");
		// start UI
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new EquipmentSuppliesPage().setVisible(true);
				new MenuItemsPage().setVisible(true);
				new OrdersPage().setVisible(true);
				new StaffSchedulePage().setVisible(true);
			}
		});
	}
}
