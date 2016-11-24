<?php
require_once __DIR__.'/InputValidator.php';
require_once __DIR__.'/../persistence/PersistenceFoodTruck.php';
require_once __DIR__.'/../model/Equipment.php';
require_once __DIR__.'/../model/Supply.php';
require_once __DIR__.'/../model/MenuItem.php';
require_once __DIR__.'/../model/Order.php';
require_once __DIR__.'/../model/TimeBlock.php';
require_once __DIR__.'/../model/Staff.php';


require_once __DIR__.'/../model/FTMS.php';

class Controller{

	public function __construct(){

	}
//TODO: length evaluation (string less than 50 etc)
	public function createEquipment($equipment_name, $equipment_quantity){
		//1. Validate input
		$error = "";
		$name = InputValidator::validate_input($equipment_name);
		$quantity = InputValidator::validate_input($equipment_quantity);
		$isInt = preg_match('/^[0-9]+$/',$quantity);
		if ($name!=null && strlen($name)!=0 && strlen($name) <=50 && $quantity!=null && strlen($quantity)!=0 && strlen($quantity)<=9 && $isInt){
			//2. load all of the data
			$pm = new PersistenceFoodTruck();
			$rm = $pm->loadDataFromStore();
		
			//3. Add the new Equipment
			$equipment = new Equipment($name,intval($quantity));
			$rm->addEquipment($equipment);
		
			//4. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else {
			if ($name == null || strlen($name)==0) {
				$error .= "@1Equipment name cannot be empty! ";
			}
			else if (strlen($name) > 50){
				$error .="@1Equipment name cannot be longer than 50 characters! ";
			}
			if ($quantity == null || strlen($quantity) ==0) {
				$error .= "@2Equipment quantity cannot be empty! ";
			}
			else if (!$isInt) {
				$error .= "@2Equipment quantity must be a positive integer! ";
			}
			else if(strlen($quantity) > 9){
				$error .= "@2Equipment quantity cannot have more than 9 digits! ";
			}
			throw new Exception(trim($error));
		} 
	}
	
	public function createSupply($supply_name, $supply_quantity){
		//1. Validate input
		$error = "";
		$name = InputValidator::validate_input($supply_name);
		$quantity = InputValidator::validate_input($supply_quantity);
		$isInt = preg_match('/^[0-9]+$/',$quantity);
		if ($name!=null && strlen($name)!=0 && strlen($name) <=50 && $quantity!=null && strlen($quantity)!=0 && strlen($quantity) <=9 && $isInt){
			//2. load all of the data
			$pm = new PersistenceFoodTruck();
			$rm = $pm->loadDataFromStore();
	
			//3. Add the new Supply
			$supply = new Supply($name,intval($quantity));
			$rm->addSupply($supply);
	
			//4. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else {
			if ($name == null || strlen($name)==0) {
				$error .= "@1Supply name cannot be empty! ";
			}
			else if (strlen($name) > 50){
				$error .="@1Supply name cannot be longer than 50 characters! ";
			}
			if ($quantity == null || strlen($quantity) ==0) {
				$error .= "@2Supply quantity cannot be empty! ";
			}
			else if (!$isInt) {
				$error .= "@2Supply quantity must be a positive integer! ";
			}
			else if (strlen($quantity) > 9){
				$error .="@2Supply quantity cannot have more than 9 digits! ";
			}
			throw new Exception(trim($error));
		}
	}

	public function createStaff($staff_name, $staff_role){
		//1. Validate input
		$error = "";
		$name = InputValidator::validate_input($staff_name);
		$role = InputValidator::validate_input($staff_role);
		
		//2. load all of the data
		$pm = new PersistenceFoodTruck();
		$rm = $pm->loadDataFromStore();
		
		if ($name!=null && strlen($name)!=0 && strlen($name) <=50 && $role!=null && strlen($role)!=0 && strlen($role) <=50){
			
	
			//3. Add the new Staff
			$staff = new Staff($name,$role);
			$rm->addStaff($staff);
	
			//4. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else {
			if ($name == null || strlen($name)==0) {
				$error .= "@1Staff name cannot be empty! ";
			}
			else if (strlen($name) > 50){
				$error .="@1Staff name cannot be longer than 50 characters! ";
			}
			if ($role == null || strlen($role) ==0) {
				$error .= "@2Staff role cannot be empty! ";
			}
			else if (strlen($name) > 50){
				$error .="@2Staff role cannot be longer than 50 characters! ";
			}
			
			throw new Exception(trim($error));
		}
	}
	
	public function createTimeBlock($block_starttime, $block_endtime, $block_dayofweek,$block_staff){
		//1. Validate input
		$error = "";
		$day = InputValidator::validate_input($block_dayofweek);
		$s = preg_match("/(2[0-3]|[01][0-9]):([0-5][0-9])/", $block_starttime);
		$e = preg_match("/(2[0-3]|[01][0-9]):([0-5][0-9])/", $block_endtime);
		
		//2. load all of the data
		$pm = new PersistenceFoodTruck();
		$rm = $pm->loadDataFromStore();
		
		//3. Find staff
		$myKey = -1;
		foreach ($rm->getStaffs() as $key => $staff){
			if (strcmp($staff->getName(), $block_staff)==0){
				$myKey = $key;
				break;
			}
		}
		
		if ($day != null && strlen($day)!= 0 && $s && $e && $block_starttime<=$block_endtime && $myKey != -1){
			//4. Add the new time block
			$timeblock = new TimeBlock($block_starttime, $block_endtime, $day);
			$rm->addTimeBlock($timeblock);
			
			//5. Add time block to staff
			$rm->getStaff_index($myKey)->addTimeBlock($timeblock);	
			
			//6. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else{
			if($day == null || strlen($day) ==0){
				$error .= "@1Time block day of the week cannot be empty! ";
			}
			if (!$s){
				$error .= "@2Time block start time must be specified correctly (HH:MM)! ";
			}
			if (!$e) {
				$error .= "@3Time block end time must be specified correctly (HH:MM)! ";
			}
			if ($block_endtime < $block_starttime){
				$error .= "@3Time block end time cannot be before event start time! ";
			}
			if($myKey == -1){
				$error .= "@4Staff not found! ";
			}
			throw new Exception(trim($error));
		}
	}
	
	public function createMenuItem($item_name, $item_supplies){	
		//1. load all of the data
		$pm = new PersistenceFoodTruck();
		$rm = $pm -> loadDataFromStore();
		
		//2. Validate input
		$error = "";
		$name = InputValidator::validate_input($item_name);
		
		//3. Find supplies
		$is_supplies = False;
		foreach ($rm->getSupplies() as $supply){
			foreach ($item_supplies as $supplyName){
				if (strcmp($supply->getName(), $supplyName)==0){
					$is_supplies = True;
					break;
				}
			}
		}
		
		if ($name!=null && strlen($name)!=0 && strlen($name)<=50 && $is_supplies){
			//4. Add the new Menu Item
			$menuItem = new MenuItem($name);
			
			//5. Find supplies again and link to Menu Item
			foreach ($rm->getSupplies() as $supply){
				foreach ($item_supplies as $supplyName){
					if (strcmp($supply->getName(), $supplyName)==0){
						$menuItem->addSupply($supply);
					}
				}
			}
			$rm->addMenuItem($menuItem);
	
			//6. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else {
			if ($name == null || strlen($name)==0) {
				$error .= "@1Menu item name cannot be empty! ";
			}
			else if (strlen($name) > 50){
				$error .="@1Menu item name cannot be longer than 50 characters! ";
			}
			if (!$is_supplies) {
				$error .= "@2Menu item ingredient not found! ";
			}
				
			throw new Exception(trim($error));
		}
	}
	
	public function createOrder($order_menuItem){
		//1. load all of the data
		$pm = new PersistenceFoodTruck();
		$rm = $pm -> loadDataFromStore();
	
		//2. Validate input
		$error = "";
		
		//3. Find menu items
		$is_menuitem = False;
		foreach ($rm->getMenuItems() as $menuitem){
			foreach ($order_menuItem as $itemName){
				if (strcmp($menuitem->getName(), $itemName)==0){
					$is_menuitem = True;
					break;
				}
			}
		}
	
		if ($is_menuitem){
			//4. Add the new Order
			$order = new Order();
				
			//5. Find menuitems again and link to order
			foreach ($rm->getMenuItems() as $menuitem){
				foreach ($order_menuItem as $itemName){
					if (strcmp($menuitem->getName(), $itemName)==0){
						$order->addMenuItem($menuitem);
					}
				}
			}
			$rm->addOrder($order);
	
			//6. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else {
			if (!$is_menuitem) {
				$error .= "Order Menu item not found!";
			}
	
			throw new Exception($error);
		}
	}
}
