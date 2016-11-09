package controller2;

import static org.junit.Assert.*;

import java.awt.MenuItem;
import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.Controller;
import controller.InvalidInputException;
import model.Equipment;
import model.FTMS;
import model.Staff;
import model.Supply;
import model.TimeBlock;
import persistence.PersistenceXStream;

public class TestController2 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test" + File.separator + "controller2" + File.separator + "data.xml");
		PersistenceXStream.setAlias("timeBlock", TimeBlock.class);
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("menuItem", MenuItem.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
	}

	@After
	public void tearDown() throws Exception {
		FTMS master =FTMS.getInstance();
		master.delete();
	}

	@Test
	public void test() {
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		String name = "Hank";
		String role = "Engineer";
		Controller c = new Controller();
		try {
			c.createStaff(name, role);
		}  catch (InvalidInputException e) {
			fail();
		}
		checkResultStaff(name, role, master);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		checkResultStaff(name, role, master2);
	}

	@Test
	public void testCreateEquipment(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());
		String name = "Knife";
		String description = "Sharp";
		String quantity = "5";
		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch (InvalidInputException e) {
			fail();
		}
		checkResultEquipment(name, description, quantity, master);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		checkResultEquipment(name, description, quantity, master2);
	}
	
	public void checkResultStaff(String name, String role, FTMS master){
		assertEquals(1, master.getStaffs().size());
		assertEquals(name, master.getStaff(0).getName());
		assertEquals(role, master.getStaff(0).getRole());
		assertEquals(0, master.getEquipments().size());
		assertEquals(0, master.getMenuItems().size());
		assertEquals(0, master.getSupplies().size());
	}
	
	public void checkResultEquipment(String name, String description, String quantity, FTMS master){
		assertEquals(1, master.getEquipments().size());
		assertEquals(name, master.getEquipment(0).getName());
		assertEquals(description, master.getEquipment(0).getDescription());
		assertEquals(quantity, Integer.toString(master.getEquipment(0).getQuantity()));
		assertEquals(0, master.getStaffs().size());
		assertEquals(0, master.getMenuItems().size());
		assertEquals(0, master.getSupplies().size());
	}
}
