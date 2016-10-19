package persistence;

import java.util.Iterator;

import model.Equipment;
import model.FTMS;
import model.Staff;
import model.Supply;

public class Persistence {
	public static void initializeXStream(String filename){
		PersistenceXStream.setFilename(filename);
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
	}
	
	public static void loadEventRegistrationModel(String filename){
		FTMS master = FTMS.getInstance();
		Persistence.initializeXStream(filename);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		if(master2 != null){
			// unfortunately this creates a second RegistrationManager object, even though it is a singleton
			// copy loaded model into singleton instance of RegistrationManger, because this will be used throughout the application
//			Iterator<Participant> pIt = rm2.getParticipants().iterator();
//			while(pIt.hasNext())
//				rm.addParticipant(pIt.next());
//			Iterator<Event> eIt = rm2.getEvents().iterator();
//			while(eIt.hasNext())
//				rm.addEvent(eIt.next());
//			Iterator<Registration> rIt = rm2.getRegistrations().iterator();
//			while(rIt.hasNext())
//				rm.addRegistration(rIt.next());


		}
	}
	
}
