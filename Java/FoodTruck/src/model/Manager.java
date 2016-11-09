/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package model;

// line 53 "../model.ump"
// line 108 "../model.ump"
public class Manager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private String permission;

  //Autounique Attributes
  private int id;

  //Manager Associations
  private FTMS fTMS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(String aPermission, FTMS aFTMS)
  {
    permission = aPermission;
    id = nextId++;
    if (!setFTMS(aFTMS))
    {
      throw new RuntimeException("Unable to create Manager due to aFTMS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPermission(String aPermission)
  {
    boolean wasSet = false;
    permission = aPermission;
    wasSet = true;
    return wasSet;
  }

  public String getPermission()
  {
    return permission;
  }

  public int getId()
  {
    return id;
  }

  public FTMS getFTMS()
  {
    return fTMS;
  }

  public boolean setFTMS(FTMS aNewFTMS)
  {
    boolean wasSet = false;
    if (aNewFTMS != null)
    {
      fTMS = aNewFTMS;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    fTMS = null;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "permission" + ":" + getPermission()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fTMS = "+(getFTMS()!=null?Integer.toHexString(System.identityHashCode(getFTMS())):"null")
     + outputString;
  }
}