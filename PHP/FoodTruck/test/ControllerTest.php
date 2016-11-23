<?php
require_once __DIR__.'/../controller/Controller.php';
require_once __DIR__.'/../persistence/PersistenceFoodTruck.php';
require_once __DIR__.'/../model/FTMS.php';
require_once __DIR__.'/../model/Equipment.php';
require_once __DIR__.'/../model/Supply.php';
require_once __DIR__.'/../model/Staff.php';
require_once __DIR__.'/../model/MenuItem.php';
require_once __DIR__.'/../model/Order.php';


//TODO: refactor the check if empty ones to a method
//TODO: add the check for empty test cases

class ControllerTest extends PHPUnit_Framework_TestCase
{
	protected $c;
	protected $pm;
	protected $ftms;
	
	protected function setUp()
	{
		$this->c = new Controller();
		$this->pm = new PersistenceFoodTruck();
		$this->ftms = $this->pm->loadDataFromStore();
		$this->ftms->delete();
		$this->pm->writeDataToStore($this->ftms);
	}
	
	protected function tearDown()
	{
	}
	
	public function testCreateEquipment() {
		$this->assertEquals(0, count($this->ftms->getEquipment()));
	
		$name = "Knife";
		$quantity = "3";
	
		try {
			$this->c->createEquipment($name,$quantity);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getEquipment()));
		$this->assertEquals($name, $this->ftms->getEquipment_index(0)->getName());
		$this->assertEquals($quantity, $this->ftms->getEquipment_index(0)->getQuantity());
		
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
// 		$this->assertEquals(0, count($this->ftms->getEvents()));
// 		$this->assertEquals(0, count($this->ftms->getRegistrations()));
	}
	public function testCreateEquipmentNull() {
		$this->assertEquals(0, count($this->ftms->getEquipment()));
	
		$name = null;
		$quantity = null;
		$error = "";
		try {
			$this->c->createEquipment($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Equipment name cannot be empty! @2Equipment quantity cannot be empty!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
// 		$this->assertEquals(0, count($this->rm->getEvents()));
// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	public function testCreateEquipmentEmpty() {
		$this->assertEquals(0, count($this->ftms->getEquipment()));
	
		$name = "";
		$quantity ="";
		$error = "";
		try {
			$this->c->createEquipment($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Equipment name cannot be empty! @2Equipment quantity cannot be empty!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	
	public function testCreateEquipmentQuantityNotInt() {
		$this->assertEquals(0, count($this->ftms->getEquipment()));
	
		$name = "Pan";
		$quantity = "hello";
		$error = "";
		try {
			$this->c->createEquipment($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@2Equipment quantity must be a positive integer!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	
	public function testCreateEquipmentQuantityNegative() {
		$this->assertEquals(0, count($this->ftms->getEquipment()));
	
		$name = "Pan";
		$quantity = -4;
		$error = "";
		try {
			$this->c->createEquipment($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@2Equipment quantity must be a positive integer!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	//**
	public function testCreateSupply() {
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		$name = "Cucumber";
		$quantity = "3";
	
		try {
			$this->c->createSupply($name,$quantity);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getSupplies()));
		$this->assertEquals($name, $this->ftms->getSupply_index(0)->getName());
		$this->assertEquals($quantity, $this->ftms->getSupply_index(0)->getQuantity());
		
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->ftms->getEvents()));
		// 		$this->assertEquals(0, count($this->ftms->getRegistrations()));
	}
	public function testCreateSupplyNull() {
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		$name = null;
		$quantity = null;
		$error = "";
		try {
			$this->c->createSupply($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Supply name cannot be empty! @2Supply quantity cannot be empty!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	public function testCreateSupplyEmpty() {
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		$name = "";
		$quantity ="";
		$error = "";
		try {
			$this->c->createSupply($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Supply name cannot be empty! @2Supply quantity cannot be empty!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	
	public function testCreateSupplyQuantityNotInt() {
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		$name = "Cucumber";
		$quantity = "hello";
		$error = "";
		try {
			$this->c->createSupply($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@2Supply quantity must be a positive integer!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	
	public function testCreateSupplyQuantityNegative() {
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		$name = "Cucumber";
		$quantity = -4;
		$error = "";
		try {	
			$this->c->createSupply($name,$quantity);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@2Supply quantity must be a positive integer!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
	
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	//**
	public function testCreateStaff() {
		$this->assertEquals(0, count($this->ftms->getStaffs()));
	
		$name = "Jenny";
		$role = "Cook";
	
		try {
			$this->c->createStaff($name,$role);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getStaffs()));
		$this->assertEquals($name, $this->ftms->getStaff_index(0)->getName());
		$this->assertEquals($role, $this->ftms->getStaff_index(0)->getRole());
	
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		// 		$this->assertEquals(0, count($this->ftms->getEvents()));
		// 		$this->assertEquals(0, count($this->ftms->getRegistrations()));
	}
	public function testCreateStaffNull() {
		$this->assertEquals(0, count($this->ftms->getStaffs()));
	
		$name = null;
		$role = null;
		$error = "";
		try {
			$this->c->createStaff($name,$role);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Staff name cannot be empty! @2Staff role cannot be empty!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		//this->assertEquals(0, count($this->rm->getEvents()));
		//$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	public function testCreateStaffEmpty() {
		$this->assertEquals(0, count($this->ftms->getStaffs()));
	
		$name = "";
		$role ="";
		$error = "";
		try {
			$this->c->createStaff($name,$role);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Staff name cannot be empty! @2Staff role cannot be empty!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		// 		$this->assertEquals(0, count($this->rm->getEvents()));
		// 		$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	//***
	public function testCreateMenuItem() {
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		
		//first createa supply to link to menu item
		$nameS = "Broth";
		$quantityS = 4;
		try {
			$this->c->createSupply($nameS,$quantityS);
		} catch (Exception $e) {
			$this->fail();
		}
		//create menu item to test
		$name = "Soup";
		$supplies = array($nameS);
		try {
			$this->c->createMenuItem($name,$supplies);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getMenuItems()));
		$this->assertEquals(1, count($this->ftms->getSupplies()));
		$this->assertEquals($nameS, $this->ftms->getSupply_index(0)->getName());
		$this->assertEquals($quantityS, $this->ftms->getSupply_index(0)->getQuantity());
		$this->assertEquals($name, $this->ftms->getMenuItem_index(0)->getName());
		$this->assertEquals($this->ftms->getSupply_index(0), $this->ftms->getMenuItem_index(0)->getSupply_index(0));
		
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		// 		$this->assertEquals(0, count($this->ftms->getEvents()));
		// 		$this->assertEquals(0, count($this->ftms->getRegistrations()));
	}
 	public function testCreateMenuItemNull() {
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		
		$name = null;
		$supplies = array(null);
		$error = "";
		try {
			$this->c->createMenuItem($name,$supplies);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("@1Menu item name cannot be empty! @2Menu item ingredient not found!", $error);
		
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		//this->assertEquals(0, count($this->rm->getEvents()));
		//$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	public function testCreateMenuItemSupplyDoNotExist() {
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		$nameS = "Banana";
		$quantityS = 4;
		try {
			$this->c->createSupply($nameS,$quantityS);
		} catch (Exception $e) {
			$this->fail();
		}
	
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getSupplies()));
		$supply = $this->ftms->getSupply_index(0);
		$this->ftms->delete();
		$this->pm->writeDataToStore($this->ftms);
		
		$name = "Pasta";
		$supplies = array($supply->getName());
		try {
			$this->c->createMenuItem($name,$supplies);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
		 
		// check error
		$this->assertEquals("@2Menu item ingredient not found!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
	}
	
	//***
	public function testCreateOrder() {
		$this->assertEquals(0, count($this->ftms->getOrders()));
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
	
		//first create supply & menu item to link to order
		$nameS = "Banana";
		$quantityS = 4;
		try {
			$this->c->createSupply($nameS,$quantityS);
		} catch (Exception $e) {
			$this->fail();
		}
		
		$nameM = "Salad";
		$supplyM = array($nameS);
		try {
			$this->c->createMenuItem($nameM,$supplyM);
		} catch (Exception $e) {
			$this->fail();
		}
		//create order to test
		$menuItem = array("Salad");
		try {
			$this->c->createOrder($menuItem);
		} catch (Exception $e) {
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getOrders()));
		$this->assertEquals(1, count($this->ftms->getMenuItems()));
		$this->assertEquals(1, count($this->ftms->getSupplies()));
		$this->assertEquals($nameM, $this->ftms->getMenuItem_index(0)->getName());
		$this->assertEquals($this->ftms->getMenuItem_index(0), $this->ftms->getOrder_index(0)->getMenuItem_index(0));
	
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		// 		$this->assertEquals(0, count($this->ftms->getEvents()));
		// 		$this->assertEquals(0, count($this->ftms->getRegistrations()));
	}
	public function testCreateOrderNull() {
		$this->assertEquals(0, count($this->ftms->getOrders()));
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
	
		$menuItem = array(null);
		$error = "";
		try {
			$this->c->createOrder($menuItem);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
	
		// check error
		$this->assertEquals("Order Menu item not found!", $error);
	
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
	
		$this->assertEquals(0, count($this->ftms->getOrders()));
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));
		//this->assertEquals(0, count($this->rm->getEvents()));
		//$this->assertEquals(0, count($this->rm->getRegistrations()));
	}
	public function testCreateOrderMenuItemDoNotExist() {
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
	
		//first create supply & menu item to link to order
		$nameS = "Banana";
		$quantityS = 4;
		try {
			$this->c->createSupply($nameS,$quantityS);
		} catch (Exception $e) {
			$this->fail();
		}
		
		$nameM = "salad";
		$supplyM = array($nameS);
		try {
			$this->c->createMenuItem($nameM,$supplyM);
		} catch (Exception $e) {
			$this->fail();
		}
	
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->ftms->getMenuItems()));
		$menuitem = $this->ftms->getMenuItem_index(0);
		$this->ftms->delete();
		$this->pm->writeDataToStore($this->ftms);
	
		$menuItems = array($menuitem->getName());
		try {
			$this->c->createOrder($menuItems);
		} catch (Exception $e) {
			$error = $e->getMessage();
		}
			
		// check error
		$this->assertEquals("Order Menu item not found!", $error);
		// check file contents
		$this->ftms = $this->pm->loadDataFromStore();
		$this->assertEquals(0, count($this->ftms->getOrders()));
		$this->assertEquals(0, count($this->ftms->getMenuItems()));
		$this->assertEquals(0, count($this->ftms->getStaffs()));
		$this->assertEquals(0, count($this->ftms->getSupplies()));
		$this->assertEquals(0, count($this->ftms->getEquipment()));	}
}

