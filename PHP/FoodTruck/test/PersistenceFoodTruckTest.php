<?php
require_once __DIR__.'/../persistence/PersistenceFoodTruck.php';
require_once __DIR__.'/../model/FTMS.php';
require_once __DIR__.'/../model/Equipment.php';

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
		$equipment = new Equipment("Pan",5);
		$ftms->addEquipment($equipment);
		
		// 2. Write all of the data
		$this->pm->writeDataToStore($ftms);
		
		// 3. Clear the data from memory
		$ftms->delete();
		
		$this->assertEquals(0,count($ftms->getEquipment()));
		
		//4. Load it back in 
		$ftms = $this->pm->loadDataFromStore();
		
		//5. Check that we got it back
		$this->assertEquals(1,count($ftms->getEquipment()));
		$myEquipment = $ftms->getEquipment_index(0);
		$this->assertEquals("Pan",$myEquipment->getName());
		$this->assertEquals(5,$myEquipment->getQuantity());
		
	}
}