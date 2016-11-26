<?php
require_once 'controller/Controller.php';

session_start( );

$_SESSION["errorSupplyName"] = "";
$_SESSION["errorSupplyQuantity"] = "";

$c = new Controller();
try {
	$name = $_POST['supply_name'];
	$quantity = $_POST['supply_quantity'];
	$c->createSupply($name,$quantity);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage()); 
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorSupplyName"] = substr($error, 1);
		} 
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorSupplyQuantity"] = substr($error, 1);
		}
	}}
?>
<!DOCTYPE html> 
<html>
	<head>
			<meta http-equiv= "refresh" content="0; url=/FoodTruck/EquipmentSupplyPage.php" />
	</head>
</html>
