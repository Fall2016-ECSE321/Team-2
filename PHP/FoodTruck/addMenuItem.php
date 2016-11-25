<?php
require_once 'controller/Controller.php';

session_start();

$_SESSION["errorMenuItemName"] = "";
$_SESSION["errorMenuItemSupply"] = "";
$c = new Controller();
try {
	$name = $_POST['menuitem_name'];
	$supplies = array();
 	if (isset($_POST['supplyspinner1'])) {
		$supply = $_POST['supplyspinner1'];
		$supplies[] = $supply;
	}
	if (isset($_POST['supplyspinner2'])) {
		$supply = $_POST['supplyspinner2'];
		$supplies[] = $supply;
	}
	$supplies[] = $supply;
	if (isset($_POST['supplyspinner3'])) {
		$supply = $_POST['supplyspinner3'];
		$supplies[] = $supply;
	}
	if (isset($_POST['supplyspinner4'])) {
		$supply = $_POST['supplyspinner4'];
		$supplies[] = $supply;
	}
	if (isset($_POST['supplyspinner5'])) {
		$supply = $_POST['supplyspinner5'];
		$supplies[] = $supply;
	}
	$c->createMenuItem($name, $supplies);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorMenuItemName"] = substr($error, 1);
		} if (substr($error, 0, 1) == "2") {
			$_SESSION["errorMenuItemSupply"] = substr($error, 1);
		}
	}
} ?>

<!DOCTYPE html> <html>
	<head>
			<meta http-equiv= "refresh" content="0; url=/FoodTruck/MenuItemPage.php" />
	</head>
</html> 	 