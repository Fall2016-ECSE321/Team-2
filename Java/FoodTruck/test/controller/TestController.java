package controller;

import static org.junit.Assert.*;

import java.awt.MenuItem;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
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
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			c.createSupply("Potato", "hi", "20");
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Test
	public void testCreateMenuItemNull(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getMenuItems().size());
		String name = null;
		ArrayList<Supply> ingredients = null;
		String error = null;
		Controller c = new Controller();
		try{
			c.createMenuItem(name, ingredients);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		// check error
		assertEquals("Menu item name cannot be empty! Menu item ingredients cannot be empty!", error);
		assertEquals(0, master.getMenuItems().size());
	}

	@Test
	public void testCreateMenuItemEmpty(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getMenuItems().size());
		String name = "";
		ArrayList<Supply> ingredients = new ArrayList<Supply>();
		String error = null;
		Controller c = new Controller();
		try{
			c.createMenuItem(name, ingredients);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		// check error
		assertEquals("Menu item name cannot be empty! Menu item ingredients cannot be empty!", error);
		assertEquals(0, master.getMenuItems().size());
	}

	@Test
	public void testCreateMenuItemSpaces(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getMenuItems().size());
		String name = " ";
		ArrayList<Supply> ingredients = new ArrayList<Supply>();
		String error = null;
		Controller c = new Controller();
		try{
			c.createMenuItem(name, ingredients);
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		// check error
		assertEquals("Menu item name cannot be empty! Menu item ingredients cannot be empty!", error);
		assertEquals(0, master.getMenuItems().size());
	}

	@Test
	public void testCreateTimeBlock(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		Controller c = new Controller();
		try {
			c.createStaff("Hank", "Engineer");
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(1, master.getStaffs().size());
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
		Staff staff = master.getStaff(0);
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.OCTOBER, 31, 9, 00, 0);
		Time startTime1 = new Time(cal.getTimeInMillis());
		cal.set(2016, Calendar.OCTOBER, 31, 10, 00, 0);
		Time endTime1 = new Time(cal.getTimeInMillis());
		int dayOfWeek1 = 1;
		cal.set(2016, Calendar.OCTOBER, 30, 10, 00, 0);
		Time startTime2 = new Time(cal.getTimeInMillis());
		cal.set(2016, Calendar.OCTOBER, 30, 11, 00, 0);
		Time endTime2 = new Time(cal.getTimeInMillis());
		int dayOfWeek2 = 3;
		try {
			c.createTimeBlock(startTime1, endTime1, dayOfWeek1, staff);
			c.createTimeBlock(startTime2, endTime2, dayOfWeek2, staff);
		} catch (InvalidInputException e) {
			fail();
		}
		checkResultTimeBlock(startTime1, endTime1, dayOfWeek1, startTime2, endTime2, dayOfWeek2, staff, master);
		FTMS master2 = (FTMS) PersistenceXStream.loadFromXMLwithXStream();
		checkResultTimeBlock(startTime1, endTime1, dayOfWeek1, startTime2, endTime2, dayOfWeek2, staff, master2);
	}

	@Test
	public void testCreateTimeBlockNull(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		Controller c = new Controller();
		try {
			c.createStaff("Hank", "Engineer");
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(1, master.getStaffs().size());
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
		String error = null;
		Staff staff = master.getStaff(0);
		Time startTime = null;
		Time endTime = null;
		int dayOfWeek1 = 1;
		try {
			c.createTimeBlock(startTime, endTime, dayOfWeek1, staff);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Time block start time cannot be empty! Time block end time cannot be empty!", error);
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
	}

	@Test
	public void testCreateTimeBlockEndTimeBeforeStartTime(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		Controller c = new Controller();
		try {
			c.createStaff("Hank", "Engineer");
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(1, master.getStaffs().size());
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
		String error = null;
		Staff staff = master.getStaff(0);
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.OCTOBER, 31, 10, 00, 0);
		Time startTime = new Time(cal.getTimeInMillis());
		cal.set(2016, Calendar.OCTOBER, 31, 9, 00, 0);
		Time endTime = new Time(cal.getTimeInMillis());
		int dayOfWeek1 = 1;
		try {
			c.createTimeBlock(startTime, endTime, dayOfWeek1, staff);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Time block end time cannot be before start time!", error);
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
	}

	@Test
	public void testCreateTimeBlockDayOfWeekOutOfRange(){
		FTMS master = FTMS.getInstance();
		assertEquals(0, master.getStaffs().size());
		Controller c = new Controller();
		try {
			c.createStaff("Hank", "Engineer");
		} catch (InvalidInputException e) {
			fail();
		}
		assertEquals(1, master.getStaffs().size());
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
		String error = null;
		Staff staff = master.getStaff(0);
		Calendar cal = Calendar.getInstance();
		cal.set(2016, Calendar.OCTOBER, 31, 9, 00, 0);
		Time startTime = new Time(cal.getTimeInMillis());
		cal.set(2016, Calendar.OCTOBER, 31, 10, 00, 0);
		Time endTime = new Time(cal.getTimeInMillis());
		int dayOfWeek1 = 12;
		try {
			c.createTimeBlock(startTime, endTime, dayOfWeek1, staff);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("Time block day of the week must be between Monday and Sunday!", error);
		assertEquals(0, master.getStaff(0).getTimeBlocks().size());
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
		assertEquals(2, master.getSupplies().size());
		assertEquals(1, master.getMenuItems().size());
		assertEquals(name, master.getMenuItem(0).getName());
		assertEquals(ingredients.get(0).getName(), master.getMenuItem(0).getSupply(0).getName());
		assertEquals(ingredients.get(1).getName(), master.getMenuItem(0).getSupply(1).getName());
	}

	public void checkResultTimeBlock(Time startTime1, Time endTime1, int dayOfWeek1, Time startTime2, Time endTime2, int dayOfWeek2, Staff staff, FTMS master){
		assertEquals(1, master.getStaffs().size());
		assertEquals(2, master.getStaff(0).getTimeBlocks().size());
		assertEquals(staff.getClass(), master.getStaff(0).getClass());
		assertEquals(startTime1.toString(), master.getStaff(0).getTimeBlock(0).getStartTime().toString());
		assertEquals(endTime1.toString(), master.getStaff(0).getTimeBlock(0).getEndTime().toString());
		assertEquals(dayOfWeek1, master.getStaff(0).getTimeBlock(0).getDayOfWeek());
		assertEquals(startTime2.toString(), master.getStaff(0).getTimeBlock(1).getStartTime().toString());
		assertEquals(endTime2.toString(), master.getStaff(0).getTimeBlock(1).getEndTime().toString());
		assertEquals(dayOfWeek2, master.getStaff(0).getTimeBlock(1).getDayOfWeek());
	}
}
