package controller;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Equipment;
import model.FTMS;
import persistence.PersistenceXStream;

public class TestController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test" + File.separator + "controller" + File.separator + "data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
	}


	@After
	public void tearDown() throws Exception {
		// clear all equipments
		FTMS master = FTMS.getInstance();
		master.delete();
	}

	@Test
	public void testCreateEquipment() {
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());

		String name = "Butter knife";
		String description = "Careful it is sharp!!";
		String quantity = "5";

		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}

		checkResultEquipment(name, description, quantity,  master);

		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();

		// check file contents
		checkResultEquipment(name, description, quantity, master2);

	}

	@Test
	public void testCreateEquipmentNull(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());

		String name = null;
		String description = null;
		String quantity = null;

		String error = null;
		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch(InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty!", error);

		// check no change in memory
		assertEquals(0, master.getEquipments().size());
	}

	@Test
	public void TestCreateEquipmentEmpty(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());

		String name = "";
		String description = "";
		String quantity = "";

		String error = null;
		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty!", error);

		// check model in memory
		assertEquals(0, master.getEquipments().size());
	}

	@Test
	public void testCreateEquipmentSpaces(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());
		
		String name = " ";
		String description = " ";
		String quantity = " ";
		
		String error = null;
		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Equipment name cannot be empty! Equipment quantity cannot be empty!", error);
		
		// check model in memory
		assertEquals(0, master.getEquipments().size());
	}
	
	@Test
	public void testCreateEquipmentOutOfRange(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());
		
		String name = "123456789012345678901234567890123456789012345678901";
		String description = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012";
		String quantity = "1000000000";
		
		String error = null;
		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Equipment name cannot be too long! Equipment description cannot be too long! Equipment quantity cannot be too large!", error);
		
		// check model in memory
		assertEquals(0, master.getEquipments().size());
	}
	
	@Test
	public void testCreateEquipmentQuanityNotInteger(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());
		
		String name = "Butter knife";
		String description = "Careful it is sharp!";
		String quantity = "Hank";
		
		String error = null;
		Controller c = new Controller();
		try {
			c.createEquipment(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		// check error
		assertEquals("Equipment quantity must be an integer!", error);
		
		// check model in memory
		assertEquals(0, master.getEquipments().size());
	}
	
	public void checkResultEquipment(String name, String description, String quantity, FTMS master){
		assertEquals(1, master.getEquipments().size());
		assertEquals(name, master.getEquipment(0).getName());
		assertEquals(description, master.getEquipment(0).getDescription());
		assertEquals(quantity, Integer.toString(master.getEquipment(0).getQuantity()));
	}

}
