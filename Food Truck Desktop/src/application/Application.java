package application;

import persistence.Persistence;
import view.EquipmentSuppliesPage;
import view.MenuItemsPage;

public class Application {

	public static void main(String[] args) {
		Persistence.loadModel("data.xml");
		// start UI
		java.awt.EventQueue.invokeLater(new Runnable(){
			public void run(){
				new EquipmentSuppliesPage().setVisible(true);
				new MenuItemsPage().setVisible(true);
			}
		});
	}

}
