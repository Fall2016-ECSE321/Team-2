<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class FTMS
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FTMS Associations
  private $equipment;
  private $supplies;
  private $staffs;
  private $orders;
  private $menuItems;
  private $timeBlocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->equipment = array();
    $this->supplies = array();
    $this->staffs = array();
    $this->orders = array();
    $this->menuItems = array();
    $this->timeBlocks = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new FTMS();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipment[$index];
    return $aEquipment;
  }

  public function getEquipment()
  {
    $newEquipment = $this->equipment;
    return $newEquipment;
  }

  public function numberOfEquipment()
  {
    $number = count($this->equipment);
    return $number;
  }

  public function hasEquipment()
  {
    $has = $this->numberOfEquipment() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipment as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getSupply_index($index)
  {
    $aSupply = $this->supplies[$index];
    return $aSupply;
  }

  public function getSupplies()
  {
    $newSupplies = $this->supplies;
    return $newSupplies;
  }

  public function numberOfSupplies()
  {
    $number = count($this->supplies);
    return $number;
  }

  public function hasSupplies()
  {
    $has = $this->numberOfSupplies() > 0;
    return $has;
  }

  public function indexOfSupply($aSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->supplies as $supply)
    {
      if ($supply->equals($aSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getStaff_index($index)
  {
    $aStaff = $this->staffs[$index];
    return $aStaff;
  }

  public function getStaffs()
  {
    $newStaffs = $this->staffs;
    return $newStaffs;
  }

  public function numberOfStaffs()
  {
    $number = count($this->staffs);
    return $number;
  }

  public function hasStaffs()
  {
    $has = $this->numberOfStaffs() > 0;
    return $has;
  }

  public function indexOfStaff($aStaff)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->staffs as $staff)
    {
      if ($staff->equals($aStaff))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getOrder_index($index)
  {
    $aOrder = $this->orders[$index];
    return $aOrder;
  }

  public function getOrders()
  {
    $newOrders = $this->orders;
    return $newOrders;
  }

  public function numberOfOrders()
  {
    $number = count($this->orders);
    return $number;
  }

  public function hasOrders()
  {
    $has = $this->numberOfOrders() > 0;
    return $has;
  }

  public function indexOfOrder($aOrder)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->orders as $order)
    {
      if ($order->equals($aOrder))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getMenuItem_index($index)
  {
    $aMenuItem = $this->menuItems[$index];
    return $aMenuItem;
  }

  public function getMenuItems()
  {
    $newMenuItems = $this->menuItems;
    return $newMenuItems;
  }

  public function numberOfMenuItems()
  {
    $number = count($this->menuItems);
    return $number;
  }

  public function hasMenuItems()
  {
    $has = $this->numberOfMenuItems() > 0;
    return $has;
  }

  public function indexOfMenuItem($aMenuItem)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->menuItems as $menuItem)
    {
      if ($menuItem->equals($aMenuItem))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getTimeBlock_index($index)
  {
    $aTimeBlock = $this->timeBlocks[$index];
    return $aTimeBlock;
  }

  public function getTimeBlocks()
  {
    $newTimeBlocks = $this->timeBlocks;
    return $newTimeBlocks;
  }

  public function numberOfTimeBlocks()
  {
    $number = count($this->timeBlocks);
    return $number;
  }

  public function hasTimeBlocks()
  {
    $has = $this->numberOfTimeBlocks() > 0;
    return $has;
  }

  public function indexOfTimeBlock($aTimeBlock)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->timeBlocks as $timeBlock)
    {
      if ($timeBlock->equals($aTimeBlock))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEquipment()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipment[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipment[$this->indexOfEquipment($aEquipment)]);
      $this->equipment = array_values($this->equipment);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfSupplies()
  {
    return 0;
  }

  public function addSupply($aSupply)
  {
    $wasAdded = false;
    if ($this->indexOfSupply($aSupply) !== -1) { return false; }
    $this->supplies[] = $aSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSupply($aSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfSupply($aSupply) != -1)
    {
      unset($this->supplies[$this->indexOfSupply($aSupply)]);
      $this->supplies = array_values($this->supplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addSupplyAt($aSupply, $index)
  {  
    $wasAdded = false;
    if($this->addSupply($aSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveSupplyAt($aSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfSupply($aSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSupplies()) { $index = $this->numberOfSupplies() - 1; }
      array_splice($this->supplies, $this->indexOfSupply($aSupply), 1);
      array_splice($this->supplies, $index, 0, array($aSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addSupplyAt($aSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfStaffs()
  {
    return 0;
  }

  public function addStaff($aStaff)
  {
    $wasAdded = false;
   //if ($this->indexOfStaff($aStaff) !== -1) { return false; }
    $this->staffs[] = $aStaff;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStaff($aStaff)
  {
    $wasRemoved = false;
    if ($this->indexOfStaff($aStaff) != -1)
    {
      unset($this->staffs[$this->indexOfStaff($aStaff)]);
      $this->staffs = array_values($this->staffs);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addStaffAt($aStaff, $index)
  {  
    $wasAdded = false;
    if($this->addStaff($aStaff))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveStaffAt($aStaff, $index)
  {
    $wasAdded = false;
    if($this->indexOfStaff($aStaff) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfStaffs()) { $index = $this->numberOfStaffs() - 1; }
      array_splice($this->staffs, $this->indexOfStaff($aStaff), 1);
      array_splice($this->staffs, $index, 0, array($aStaff));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addStaffAt($aStaff, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfOrders()
  {
    return 0;
  }

  public function addOrder($aOrder)
  {
    $wasAdded = false;
    //if ($this->indexOfOrder($aOrder) !== -1) { return false; }
    $this->orders[] = $aOrder;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeOrder($aOrder)
  {
    $wasRemoved = false;
    if ($this->indexOfOrder($aOrder) != -1)
    {
      unset($this->orders[$this->indexOfOrder($aOrder)]);
      $this->orders = array_values($this->orders);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addOrderAt($aOrder, $index)
  {  
    $wasAdded = false;
    if($this->addOrder($aOrder))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveOrderAt($aOrder, $index)
  {
    $wasAdded = false;
    if($this->indexOfOrder($aOrder) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfOrders()) { $index = $this->numberOfOrders() - 1; }
      array_splice($this->orders, $this->indexOfOrder($aOrder), 1);
      array_splice($this->orders, $index, 0, array($aOrder));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addOrderAt($aOrder, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfMenuItems()
  {
    return 0;
  }

  public function addMenuItem($aMenuItem)
  {
    $wasAdded = false;
    if ($this->indexOfMenuItem($aMenuItem) !== -1) { return false; }
    $this->menuItems[] = $aMenuItem;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeMenuItem($aMenuItem)
  {
    $wasRemoved = false;
    if ($this->indexOfMenuItem($aMenuItem) != -1)
    {
      unset($this->menuItems[$this->indexOfMenuItem($aMenuItem)]);
      $this->menuItems = array_values($this->menuItems);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addMenuItemAt($aMenuItem, $index)
  {  
    $wasAdded = false;
    if($this->addMenuItem($aMenuItem))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfMenuItems()) { $index = $this->numberOfMenuItems() - 1; }
      array_splice($this->menuItems, $this->indexOfMenuItem($aMenuItem), 1);
      array_splice($this->menuItems, $index, 0, array($aMenuItem));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveMenuItemAt($aMenuItem, $index)
  {
    $wasAdded = false;
    if($this->indexOfMenuItem($aMenuItem) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfMenuItems()) { $index = $this->numberOfMenuItems() - 1; }
      array_splice($this->menuItems, $this->indexOfMenuItem($aMenuItem), 1);
      array_splice($this->menuItems, $index, 0, array($aMenuItem));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addMenuItemAt($aMenuItem, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfTimeBlocks()
  {
    return 0;
  }

  public function addTimeBlock($aTimeBlock)
  {
    $wasAdded = false;
    //if ($this->indexOfTimeBlock($aTimeBlock) !== -1) { return false; }
    $this->timeBlocks[] = $aTimeBlock;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeTimeBlock($aTimeBlock)
  {
    $wasRemoved = false;
    if ($this->indexOfTimeBlock($aTimeBlock) != -1)
    {
      unset($this->timeBlocks[$this->indexOfTimeBlock($aTimeBlock)]);
      $this->timeBlocks = array_values($this->timeBlocks);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addTimeBlockAt($aTimeBlock, $index)
  {  
    $wasAdded = false;
    if($this->addTimeBlock($aTimeBlock))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTimeBlocks()) { $index = $this->numberOfTimeBlocks() - 1; }
      array_splice($this->timeBlocks, $this->indexOfTimeBlock($aTimeBlock), 1);
      array_splice($this->timeBlocks, $index, 0, array($aTimeBlock));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveTimeBlockAt($aTimeBlock, $index)
  {
    $wasAdded = false;
    if($this->indexOfTimeBlock($aTimeBlock) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTimeBlocks()) { $index = $this->numberOfTimeBlocks() - 1; }
      array_splice($this->timeBlocks, $this->indexOfTimeBlock($aTimeBlock), 1);
      array_splice($this->timeBlocks, $index, 0, array($aTimeBlock));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addTimeBlockAt($aTimeBlock, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->equipment = array();
    $this->supplies = array();
    $this->staffs = array();
    $this->orders = array();
    $this->menuItems = array();
    $this->timeBlocks = array();
  }

}
?>