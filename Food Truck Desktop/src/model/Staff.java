/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package model;
import java.util.*;
import java.sql.Time;

// line 21 "../model.ump"
// line 68 "../model.ump"
public class Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Attributes
  private String name;
  private String role;

  //Staff Associations
  private List<TimeBlock> timeBlocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aName, String aRole)
  {
    name = aName;
    role = aRole;
    timeBlocks = new ArrayList<TimeBlock>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setRole(String aRole)
  {
    boolean wasSet = false;
    role = aRole;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getRole()
  {
    return role;
  }

  public TimeBlock getTimeBlock(int index)
  {
    TimeBlock aTimeBlock = timeBlocks.get(index);
    return aTimeBlock;
  }

  public List<TimeBlock> getTimeBlocks()
  {
    List<TimeBlock> newTimeBlocks = Collections.unmodifiableList(timeBlocks);
    return newTimeBlocks;
  }

  public int numberOfTimeBlocks()
  {
    int number = timeBlocks.size();
    return number;
  }

  public boolean hasTimeBlocks()
  {
    boolean has = timeBlocks.size() > 0;
    return has;
  }

  public int indexOfTimeBlock(TimeBlock aTimeBlock)
  {
    int index = timeBlocks.indexOf(aTimeBlock);
    return index;
  }

  public static int minimumNumberOfTimeBlocks()
  {
    return 0;
  }

  public boolean addTimeBlock(TimeBlock aTimeBlock)
  {
    boolean wasAdded = false;
    if (timeBlocks.contains(aTimeBlock)) { return false; }
    timeBlocks.add(aTimeBlock);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeBlock(TimeBlock aTimeBlock)
  {
    boolean wasRemoved = false;
    if (timeBlocks.contains(aTimeBlock))
    {
      timeBlocks.remove(aTimeBlock);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTimeBlockAt(TimeBlock aTimeBlock, int index)
  {  
    boolean wasAdded = false;
    if(addTimeBlock(aTimeBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeBlocks()) { index = numberOfTimeBlocks() - 1; }
      timeBlocks.remove(aTimeBlock);
      timeBlocks.add(index, aTimeBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeBlockAt(TimeBlock aTimeBlock, int index)
  {
    boolean wasAdded = false;
    if(timeBlocks.contains(aTimeBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeBlocks()) { index = numberOfTimeBlocks() - 1; }
      timeBlocks.remove(aTimeBlock);
      timeBlocks.add(index, aTimeBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeBlockAt(aTimeBlock, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    timeBlocks.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "role" + ":" + getRole()+ "]"
     + outputString;
  }
}