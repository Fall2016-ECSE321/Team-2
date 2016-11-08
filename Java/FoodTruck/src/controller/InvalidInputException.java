package controller;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = 5835914541865586300L;

	public InvalidInputException(String errorMessage){
		super(errorMessage);
	}
}
