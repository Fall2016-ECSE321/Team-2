<?php
require_once 'controller/Controller.php';

session_start( );

$_SESSION["errorStaffName"] = "";
$_SESSION["errorStaffQuantity"] = "";

$c = new Controller();
try {
	$name = $_POST['staff_name'];
	$quantity = $_POST['staff_quantity'];
	$c->createStaff($name,$quantity);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorStaffName"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorStaffQuantity"] = substr($error, 1);
		}
	}}
	?>
<!DOCTYPE html> 
<html>
	<head>
			<meta http-equiv= "refresh" content="0; url=/FoodTruck/StaffCreationPage/" />
	</head>
</html>
