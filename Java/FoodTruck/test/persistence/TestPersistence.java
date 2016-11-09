package persistence;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import persistence.PersistenceXStream;
import model.Equipment;
import model.FTMS;
import model.MenuItem;
import model.Staff;
import model.Supply;

public class TestPersistence {

	@Before
	public void setUp() throws Exception {

		FTMS master = FTMS.getInstance();

		// create Equipment
		Equipment eq1 = new Equipment("Butter knife", "Careful it is sharp!", 5);
		Equipment eq2 = new Equipment("Chef hat", "Do not wear it if you are not a chef!", 10);

		// create Supply
		Supply s1 = new Supply("Tomato", "Do not use if rotten!", 10);
		Supply s2 = new Supply("Potato", "Do not use if dark!", 20);
		
		// create Staff
		Staff st1 = new Staff("Hank", "Manager");
		Staff st2 = new Staff("Ali", "Engineer");

		// create MenuItem
		MenuItem mi1 = new MenuItem("Tomato chips");
		mi1.addSupply(s1);
		mi1.addSupply(s2);
		MenuItem mi2 = new MenuItem("Potato ketchup");
		mi2.addSupply(s2);
		mi2.addSupply(s1);
		
		// manage 
		master.addEquipment(eq1);
		master.addEquipment(eq2);
		master.addSupply(s1);
		master.addSupply(s2);
		master.addStaff(st1);
		master.addStaff(st2);
		master.addMenuItem(mi1);
		master.addMenuItem(mi2);
	}

	@After
	public void tearDown() throws Exception {
		// clear all
		FTMS master = FTMS.getInstance();
		master.delete();
	}

	@Test
	public void test() {
		// save model
		FTMS master = FTMS.getInstance();
		PersistenceXStream.setFilename("test" + File.separator + "persistence" + File.separator + "data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("menuItem", MenuItem.class);
		if(!PersistenceXStream.saveToXMLwithXStream(master))
			fail("Could not save file.");

		// clear the model in memory
		master.delete();
		assertEquals(0, master.getEquipments().size());
		assertEquals(0, master.getSupplies().size());
		assertEquals(0, master.getStaffs().size());
		assertEquals(0, master.getMenuItems().size());

		// load model
		master = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		if(master == null)
			fail("Could not load file.");

		// check equipments
		assertEquals(2, master.getEquipments().size());
		assertEquals("Butter knife", master.getEquipment(0).getName());
		assertEquals("Chef hat", master.getEquipment(1).getName());

		assertEquals("Careful it is sharp!", master.getEquipment(0).getDescription());
		assertEquals("Do not wear it if you are not a chef!", master.getEquipment(1).getDescription());

		assertEquals(5, master.getEquipment(0).getQuantity());
		assertEquals(10, master.getEquipment(1).getQuantity());
		
		// check supplies
		assertEquals(2, master.getSupplies().size());
		assertEquals("Tomato", master.getSupply(0).getName());
		assertEquals("Potato", master.getSupply(1).getName());
		
		assertEquals("Do not use if rotten!", master.getSupply(0).getDescription());
		assertEquals("Do not use if dark!", master.getSupply(1).getDescription());
		
		assertEquals(10, master.getSupply(0).getQuantity());
		assertEquals(20, master.getSupply(1).getQuantity());
		
		// check staffs
		assertEquals(2, master.getStaffs().size());
		assertEquals("Hank", master.getStaff(0).getName());
		assertEquals("Ali", master.getStaff(1).getName());
		
		assertEquals("Manager", master.getStaff(0).getRole());
		assertEquals("Engineer", master.getStaff(1).getRole());
		
		// check menu items
		assertEquals(2, master.getMenuItems().size());
		assertEquals("Tomato chips", master.getMenuItem(0).getName());
		assertEquals("Potato ketchup", master.getMenuItem(1).getName());
		
		assertEquals(master.getSupply(0), master.getMenuItem(0).getSupply(0));
		assertEquals(master.getSupply(1), master.getMenuItem(0).getSupply(1));
		assertEquals(master.getSupply(1), master.getMenuItem(1).getSupply(0));
		assertEquals(master.getSupply(0), master.getMenuItem(1).getSupply(1));
	}

}
