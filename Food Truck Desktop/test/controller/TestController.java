package controller;

import static org.junit.Assert.*;

import java.awt.MenuItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Equipment;
import model.FTMS;
import model.Staff;
import model.Supply;
import persistence.PersistenceXStream;

public class TestController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test" + File.separator + "controller" + File.separator + "data.xml");
		PersistenceXStream.setAlias("staff", Staff.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("FTMS", FTMS.class);
		PersistenceXStream.setAlias("supply", Supply.class);
		PersistenceXStream.setAlias("menuItem", MenuItem.class);
	}

	@After
	public void tearDown() throws Exception {
		// clear all
		FTMS master = FTMS.getInstance();
		master.delete();
	}

	@Test
	public void testCreateEquipment() {
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getEquipments().size());
		String name = "Butter knife";
		String description = "Careful it is sharp!";
		String quantity = "10";
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
	
	@Test
	public void testCreateSupply() {
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getSupplies().size());
		String name = "Tomato";
		String description = "Do not use if rotten!";
		String quantity = "10";
		Controller c = new Controller();
		try {
			c.createSupply(name, description, quantity);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		checkResultSupply(name, description, quantity,  master);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		// check file contents
		checkResultSupply(name, description, quantity, master2);
	}

	@Test
	public void testCreateSupplyNull(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getSupplies().size());
		String name = null;
		String description = null;
		String quantity = null;
		String error = null;
		Controller c = new Controller();
		try {
			c.createSupply(name, description, quantity);
		} catch(InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty!", error);
		// check no change in memory
		assertEquals(0, master.getSupplies().size());
	}

	@Test
	public void TestCreateSupplyEmpty(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getSupplies().size());
		String name = "";
		String description = "";
		String quantity = "";
		String error = null;
		Controller c = new Controller();
		try {
			c.createSupply(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty!", error);
		// check model in memory
		assertEquals(0, master.getSupplies().size());
	}

	@Test
	public void testCreateSupplySpaces(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getSupplies().size());
		String name = " ";
		String description = " ";
		String quantity = " ";
		String error = null;
		Controller c = new Controller();
		try {
			c.createSupply(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Supply name cannot be empty! Supply quantity cannot be empty!", error);
		// check model in memory
		assertEquals(0, master.getSupplies().size());
	}
	
	@Test
	public void testCreateSupplyOutOfRange(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getSupplies().size());
		String name = "123456789012345678901234567890123456789012345678901";
		String description = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012";
		String quantity = "1000000000";
		String error = null;
		Controller c = new Controller();
		try {
			c.createSupply(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Supply name cannot be too long! Supply description cannot be too long! Supply quantity cannot be too large!", error);
		// check model in memory
		assertEquals(0, master.getSupplies().size());
	}
	
	@Test
	public void testCreateSupplyQuanityNotInteger(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getSupplies().size());
		String name = "Tomato";
		String description = "Do not use if rotten!";
		String quantity = "Hank";
		String error = null;
		Controller c = new Controller();
		try {
			c.createSupply(name, description, quantity);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Supply quantity must be an integer!", error);
		// check model in memory
		assertEquals(0, master.getSupplies().size());
	}
	
	@Test
	public void testCreateStaff() {
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		String name = "Hank";
		String role = "Manager";
		Controller c = new Controller();
		try {
			c.createStaff(name, role);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		checkResultStaff(name, role,  master);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		// check file contents
		checkResultStaff(name, role, master2);
	}
	
	@Test
	public void testCreateStaffNull(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		String name = null;
		String role = null;
		String error = null;
		Controller c = new Controller();
		try {
			c.createStaff(name, role);
		} catch(InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Staff name cannot be empty! Staff role cannot be empty!", error);
		// check no change in memory
		assertEquals(0, master.getStaffs().size());
	}
	
	@Test
	public void TestCreateStaffEmpty(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		String name = "";
		String role = "";
		String error = null;
		Controller c = new Controller();
		try {
			c.createStaff(name, role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Staff name cannot be empty! Staff role cannot be empty!", error);
		// check model in memory
		assertEquals(0, master.getStaffs().size());
	}

	@Test
	public void testCreateStaffSpaces(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		String name = " ";
		String role = " ";
		String error = null;
		Controller c = new Controller();
		try {
			c.createStaff(name, role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Staff name cannot be empty! Staff role cannot be empty!", error);
		// check model in memory
		assertEquals(0, master.getStaffs().size());
	}
	
	@Test
	public void testCreateStaffOutOfRange(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		String name = "123456789012345678901234567890123456789012345678901";
		String role = "123456789012345678901234567890123456789012345678901";
		String error = null;
		Controller c = new Controller();
		try {
			c.createStaff(name, role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Staff name cannot be too long! Staff role cannot be too long!", error);
		// check model in memory
		assertEquals(0, master.getStaffs().size());
	}
	
	@Test
	public void testCreateMenuItem(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getMenuItems().size());
		String name = "Tomato chips";
		ArrayList<Supply> ingredients = new ArrayList<Supply>();
		Controller c = new Controller();
		try {
			c.createSupply("Tomato", "hi", "10");
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			c.createSupply("Potato", "hi", "20");
		} catch (InvalidInputException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ingredients.add(master.getSupply(0));
		ingredients.add(master.getSupply(1));
		try {
			c.createMenuItem(name, ingredients);
		} catch (InvalidInputException e) {
			// check that no error occurred
			fail();
		}
		checkResultMenuItem(name, ingredients, master);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		// check file contents
		checkResultMenuItem(name, ingredients, master2);
	}
	
	public void checkResultEquipment(String name, String description, String quantity, FTMS master){
		assertEquals(1, master.getEquipments().size());
		assertEquals(name, master.getEquipment(0).getName());
		assertEquals(description, master.getEquipment(0).getDescription());
		assertEquals(quantity, Integer.toString(master.getEquipment(0).getQuantity()));
	}

	public void checkResultSupply(String name, String description, String quantity, FTMS master){
		assertEquals(1, master.getSupplies().size());
		assertEquals(name, master.getSupply(0).getName());
		assertEquals(description, master.getSupply(0).getDescription());
		assertEquals(quantity, Integer.toString(master.getSupply(0).getQuantity()));
	}
	
	public void checkResultStaff(String name, String role, FTMS master){
		assertEquals(1, master.getStaffs().size());
		assertEquals(name, master.getStaff(0).getName());
		assertEquals(role, master.getStaff(0).getRole());
	}
	
	public void checkResultMenuItem(String name, ArrayList<Supply> ingredients, FTMS master){
		assertEquals(1, master.getMenuItems().size());
		assertEquals(name, master.getMenuItem(0).getName());
		assertEquals(ingredients.get(0).getName(), master.getMenuItem(0).getSupply(0).getName());
		assertEquals(ingredients.get(1).getName(), master.getMenuItem(0).getSupply(1).getName());
	}
}
