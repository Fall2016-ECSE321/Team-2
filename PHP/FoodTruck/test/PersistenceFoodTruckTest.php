<?php
require_once __DIR__.'/../persistence/PersistenceFoodTruck.php';
require_once __DIR__.'/../model/FTMS.php';
require_once __DIR__.'/../model/Equipment.php';
require_once __DIR__.'/../model/Supply.php';
require_once __DIR__.'/../model/Staff.php';

class PersistenceFoodTruckManagement extends PHPUnit_Framework_TestCase
{
	protected $pm;
	protected function setup()
	{
		$this->pm = new PersistenceFoodTruck();
	}
	protected function tearDown()
	{
	}
	public function testPersistence()
	{
		// 1. Create test data
		$ftms = FTMS::getInstance();
		//create equipment
		$equipment = new Equipment("Pan",5);
		$ftms->addEquipment($equipment);
		//create supply
		$supply = new Supply("Tomatoes",4);
		$ftms->addSupply($supply);
		//create staff
		$staff = new Staff("Emma","waitress");
		$ftms->addStaff($staff);
		
		
		// 2. Write all of the data
		$this->pm->writeDataToStore($ftms);
		
		// 3. Clear the data from memory
		$ftms->delete();
		
		$this->assertEquals(0,count($ftms->getEquipment()));
		$this->assertEquals(0,count($ftms->getSupplies()));
		$this->assertEquals(0,count($ftms->getStaffs()));
		
		
		//4. Load it back in 
		$ftms = $this->pm->loadDataFromStore();
		
		//5. Check that we got it back
		//check equipment
		$this->assertEquals(1,count($ftms->getEquipment()));
		$myEquipment = $ftms->getEquipment_index(0);
		$this->assertEquals("Pan",$myEquipment->getName());
		$this->assertEquals(5,$myEquipment->getQuantity());
		//check supply
		$this->assertEquals(1,count($ftms->getSupplies()));
		$mySupply = $ftms->getSupply_index(0);
		$this->assertEquals("Tomatoes",$mySupply->getName());
		$this->assertEquals(4,$mySupply->getQuantity());
		//check staff
		$this->assertEquals(1,count($ftms->getStaffs()));
		$myStaff = $ftms->getStaff_index(0);
		$this->assertEquals("Emma",$myStaff->getName());
		$this->assertEquals("waitress",$myStaff->getRole());
		
	}
}