package controller;

import java.util.ArrayList;

import controller.InvalidInputException;
import model.Equipment;
import model.FTMS;
import model.MenuItem;
import model.Staff;
import model.Supply;
import model.TimeBlock;
import persistence.PersistenceXStream;

public class Controller {

	public Controller(){

	}

	public void createEquipment(String name, String description, String nonValidatedQuantity) throws InvalidInputException{
		String error = "";
		int numZero = 0;
		boolean valid = true;
		String validatedQuantity = "";
		int quantity = 0;
		if(name == null || name.trim().length() == 0)
			error += "Equipment name cannot be empty! ";
		else 	if(name.length() > 50)
			error += "Equipment name cannot be too long! ";
		if(description == null)	// if user does not input anything for description, use default empty string
			description = "";
		else if(description.length() > 100)
			error += "Equipment description cannot be too long! ";
		if(nonValidatedQuantity == null || nonValidatedQuantity.trim().length() == 0)
			error += "Equipment quantity cannot be empty! ";
		else{
			for(int x = 0; x < nonValidatedQuantity.length(); x++){	// check if quantity is in the form of an integer
				if(nonValidatedQuantity.charAt(x) < '0' || nonValidatedQuantity.charAt(x) > '9'){
					error += "Equipment quantity must be an integer!";
					valid = false;
					break;
				}
			}
			if(valid){
				// cut all leading useless zeros
				while(numZero < nonValidatedQuantity.length()){
					if(nonValidatedQuantity.charAt(numZero) == '0')
						numZero++;
					else
						break;
				}
				if(numZero == nonValidatedQuantity.length())	// if input is all zeros then set it to zero
					nonValidatedQuantity = "0";

				for(int i =0; i < nonValidatedQuantity.length() - numZero; i++){
					validatedQuantity += nonValidatedQuantity.charAt(i + numZero);
				}
				if(validatedQuantity.length() < 9){	// prevent integer out of range error
					quantity = Integer.parseInt(validatedQuantity);
				}else{
					error += "Equipment quantity cannot be too large!";
					valid = false;
				}
			}
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		Equipment equipment = new Equipment(name, description, quantity);
		FTMS master = FTMS.getInstance();
		master.addEquipment(equipment);
		PersistenceXStream.saveToXMLwithXStream(master);
	}

	public void createSupply(String name, String description, String nonValidatedQuantity) throws InvalidInputException{
		String error = "";
		int numZero = 0;
		boolean valid = true;
		String validatedQuantity = "";
		int quantity = 0;
		if(name == null || name.trim().length() == 0)
			error += "Supply name cannot be empty! ";
		else 	if(name.length() > 50)
			error += "Supply name cannot be too long! ";
		if(description == null)	// if user does not input anything for description, use default empty string
			description = "";
		else if(description.length() > 100)
			error += "Supply description cannot be too long! ";
		if(nonValidatedQuantity == null || nonValidatedQuantity.trim().length() == 0)
			error += "Supply quantity cannot be empty! ";
		else{
			for(int x = 0; x < nonValidatedQuantity.length(); x++){	// check if quantity is in the form of an integer
				if(nonValidatedQuantity.charAt(x) < '0' || nonValidatedQuantity.charAt(x) > '9'){
					error += "Supply quantity must be an integer!";
					valid = false;
					break;
				}
			}
			if(valid){
				// cut all leading useless zeros
				while(numZero < nonValidatedQuantity.length()){
					if(nonValidatedQuantity.charAt(numZero) == '0')
						numZero++;
					else
						break;
				}
				if(numZero == nonValidatedQuantity.length())	// if input is all zeros then set it to zero
					nonValidatedQuantity = "0";

				for(int i =0; i < nonValidatedQuantity.length() - numZero; i++){
					validatedQuantity += nonValidatedQuantity.charAt(i + numZero);
				}
				if(validatedQuantity.length() < 9){	// prevent integer out of range error
					quantity = Integer.parseInt(validatedQuantity);
				}else{
					error += "Supply quantity cannot be too large!";
					valid = false;
				}
			}
		}
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		Supply supply = new Supply(name, description, quantity);
		FTMS master = FTMS.getInstance();
		master.addSupply(supply);
		PersistenceXStream.saveToXMLwithXStream(master);
	}
	
	public void createStaff(String name, String role) throws InvalidInputException{
		String error = "";
		if(name == null || name.trim().length() == 0)
			error += "Staff name cannot be empty! ";
		else 	if(name.length() > 50)
			error += "Staff name cannot be too long! ";
		if(role == null || role.trim().length() == 0)
			error += "Staff role cannot be empty!";
		else if(role.length() > 50)
			error += "Staff role cannot be too long! ";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		Staff staff = new Staff(name, role);
		FTMS master = FTMS.getInstance();
		master.addStaff(staff);
		PersistenceXStream.saveToXMLwithXStream(master);
	}
	
	public void createMenuItem(String name, ArrayList<Supply> ingredients) throws InvalidInputException{
		String error = "";
		if(name == null || name.trim().length() == 0)
			error += "Menu item name cannot be empty! ";
		else if(name.length() > 50)
			error += "Menu item name cannot be too long! ";
		if(ingredients.isEmpty())
			error += "Menu item ingredients cannot be empty!";
		else if(ingredients.size() > 50)
			error += "Menu item ingredients cannot exceed 50!";
		error = error.trim();
		if(error.length() > 0)
			throw new InvalidInputException(error);
		MenuItem menuItem = new MenuItem(name);
		for(int x = 0; x < ingredients.size(); x++)
			menuItem.addSupply(ingredients.get(x));
		FTMS master = FTMS.getInstance();
		master.addMenuItem(menuItem);
		PersistenceXStream.saveToXMLwithXStream(master);
	}
	//	
	//	public void createTimeBlock() throws InvalidInputException{
	//		TimeBlock timeBlock = new TimeBlock();
	//	}
}
