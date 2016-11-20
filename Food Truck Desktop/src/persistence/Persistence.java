package persistence;

import java.util.Iterator;

import model.Equipment;
import model.FTMS;
import model.MenuItem;
import model.Order;
import model.Staff;
import model.Supply;
import model.TimeBlock;

public class Persistence {
	public static void initializeXStream(String filename){
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("timeBlock", TimeBlock.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("order", Order.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
	}

	public static void loadModel(String filename){
		FTMS master = FTMS.getInstance();
		Persistence.initializeXStream(filename);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		if(master2 != null){
			// unfortunately this creates a second RegistrationManager object, even though it is a singleton
			// copy loaded model into singleton instance of RegistrationManger, because this will be used throughout the application
			Iterator<Equipment> eIt = master2.getEquipments().iterator();
			while(eIt.hasNext())
				master.addEquipment(eIt.next());
			Iterator<Supply> suIt = master2.getSupplies().iterator();
			while(suIt.hasNext())
				master.addSupply(suIt.next());
			Iterator<Staff> stIt = master2.getStaffs().iterator();
			while(stIt.hasNext())
				master.addStaff(stIt.next());
			Iterator<MenuItem> mIt = master2.getMenuItems().iterator();
			while(mIt.hasNext())
				master.addMenuItem(mIt.next());
			Iterator<Order> oIt = master2.getOrders().iterator();
			while(oIt.hasNext())
				master.addOrder(oIt.next());

			//			Iterator<Event> eIt = rm2.getEvents().iterator();
			//			while(eIt.hasNext())
			//				rm.addEvent(eIt.next());
			//			Iterator<Registration> rIt = rm2.getRegistrations().iterator();
			//			while(rIt.hasNext())
			//				rm.addRegistration(rIt.next());


		}
	}

}
