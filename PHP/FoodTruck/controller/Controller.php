<?php
require_once __DIR__.'/InputValidator.php';
require_once __DIR__.'/../persistence/PersistenceFoodTruck.php';
require_once __DIR__.'/../model/Equipment.php';
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
		if ($name!=null && strlen($name)!=0 && $quantity!=null && strlen($quantity)!=0 && $isInt){
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
			if ($quantity == null || strlen($quantity) ==0) {
				$error .= "@2Equipment quantity cannot be empty! ";
			}
			else if (!$isInt) {
				$error .= "@2Equipment quantity must be a positive integer! ";
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
		if ($name!=null && strlen($name)!=0 && $quantity!=null && strlen($quantity)!=0 && $isInt){
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
			if ($quantity == null || strlen($quantity) ==0) {
				$error .= "@2Supply quantity cannot be empty! ";
			}
			else if (!$isInt) {
				$error .= "@2Supply quantity must be a positive integer! ";
			}
			throw new Exception(trim($error));
		}
	}
	public function createStaff($staff_name, $staff_role){
		//1. Validate input
		$error = "";
		$name = InputValidator::validate_input($staff_name);
		$role = InputValidator::validate_input($staff_role);
		if ($name!=null && strlen($name)!=0 && $role!=null && strlen($role)!=0){
			//2. load all of the data
			$pm = new PersistenceFoodTruck();
			$rm = $pm->loadDataFromStore();
	
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
			if ($role == null || strlen($role) ==0) {
				$error .= "@2Staff role cannot be empty! ";
			}
			
			throw new Exception(trim($error));
		}
	}
	
	public function createMenuItem($item_name, $item_supplies){	
		//3. load all of the data
		$pm = new PersistenceFoodTruck();
		$rm = $pm -> loadDataFromStore();
		
		//1. Validate input
		$error = "";
		$name = InputValidator::validate_input($item_name);
		//2. Find supplies
		$is_supplies = False;
		foreach ($rm->getSupplies() as $supply){
			foreach ($item_supplies as $key => $supplyName){
				$item_supplies[$key] = InputValidator::validate_input($supplyName);
				if (strcmp($supply->getName(), $supplyName)==0){
					$is_supplies = True;
					break;
				}
			}
		}
		
		if ($name!=null && strlen($name)!=0 && $is_supplies){
			//4. Add the new Menu Item
			$menuItem = new MenuItem($name);
			
			//5. Link supplies to Menu Item
			foreach ($rm->getSupplies() as $supply){
				foreach ($item_supplies as $supplyName){
					if (strcmp($supply->getName(), $supplyName)==0){
						$menuItem->addSupply($supply);
					}
				}
			}
			$rm->addMenuItem($menuItem);
	
			//5. Write all of the data
			$pm->writeDataToStore($rm);
		}
		else {
			if ($name == null || strlen($name)==0) {
				$error .= "@1Menu item name cannot be empty! ";
			}
			if (!$is_supplies) {
				$error .= "@2Menu item ingredient not found! ";
			}
				
			throw new Exception(trim($error));
		}
	}
	
	
}
