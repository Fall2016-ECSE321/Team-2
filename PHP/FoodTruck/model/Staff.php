<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private $name;
  private $role;

  //Staff Associations
  private $timeBlocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aRole)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->timeBlocks = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setRole($aRole)
  {
    $wasSet = false;
    $this->role = $aRole;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getRole()
  {
    return $this->role;
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

  public static function minimumNumberOfTimeBlocks()
  {
    return 0;
  }

  public function addTimeBlock($aTimeBlock)
  {
    $wasAdded = false;
    if ($this->indexOfTimeBlock($aTimeBlock) !== -1) { return false; }
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
    $this->timeBlocks = array();
  }

}
?>