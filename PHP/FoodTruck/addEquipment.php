<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorEquipmentName"] = "";
$_SESSION["errorEquipmentQuantity"] = "";

$c = new Controller();
try {
	$name = $_POST['equipment_name'];
	$quantity = $_POST['equipment_quantity'];
	$c->createEquipment($name,$quantity);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage()); 
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorEquipmentName"] = substr($error, 1);
		} 
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorEquipmentQuantity"] = substr($error, 1);
		}
	}}
?>
<!DOCTYPE html> 
<html>
	<head>
			<meta http-equiv= "refresh" content="0; url=/FoodTruck/EquipmentSupplyPage.php" />
	</head>
</html>
