<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Supply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Supply Attributes
  private $name;
  private $quantity;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aQuantity)
  {
    $this->name = $aName;
    $this->quantity = $aQuantity;
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

  public function setQuantity($aQuantity)
  {
    $wasSet = false;
    $this->quantity = $aQuantity;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getQuantity()
  {
    return $this->quantity;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>