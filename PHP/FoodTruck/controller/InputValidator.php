<?php class InputValidator {
	
	public static function  validate_input($data) {
		//make sure to remove any weird symbols from user input
		$data = trim($data); 
		$data = stripslashes($data); 
		$data = htmlspecialchars($data); 
		return $data;
	}
}
?>