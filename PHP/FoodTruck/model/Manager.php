<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Manager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private $permission;

  //Autounique Attributes
  private $id;

  //Manager Associations
  private $fTMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aPermission, $aFTMS)
  {
    $this->permission = $aPermission;
    $this->id = self::$nextId++;
    if (!$this->setFTMS($aFTMS))
    {
      throw new Exception("Unable to create Manager due to aFTMS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setPermission($aPermission)
  {
    $wasSet = false;
    $this->permission = $aPermission;
    $wasSet = true;
    return $wasSet;
  }

  public function getPermission()
  {
    return $this->permission;
  }

  public function getId()
  {
    return $this->id;
  }

  public function getFTMS()
  {
    return $this->fTMS;
  }

  public function setFTMS($aNewFTMS)
  {
    $wasSet = false;
    if ($aNewFTMS != null)
    {
      $this->fTMS = $aNewFTMS;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->fTMS = null;
  }

}
?>