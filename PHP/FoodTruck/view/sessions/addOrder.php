<?php
require_once '../../controller/Controller.php';

session_start();

$_SESSION["errorOrder"] = "";
$c = new Controller();
try {
	$menuitems = array();
	if (isset($_POST['itemspinner1'])) {
		$item = $_POST['itemspinner1'];
		$menuitems[] = $item;
	}
	if (isset($_POST['itemspinner2'])) {
		$item = $_POST['itemspinner2'];
		$menuitems[] = $item;
	}
	if (isset($_POST['itemspinner3'])) {
		$item = $_POST['itemspinner3'];
		$menuitems[] = $item;
	}
	if (isset($_POST['itemspinner4'])) {
		$item = $_POST['itemspinner4'];
		$menuitems[] = $item;
	}
	if (isset($_POST['itemspinner5'])) {
		$item = $_POST['itemspinner5'];
		$menuitems[] = $item;
	}
	$c->createOrder($menuitems);
} catch (Exception $e) {
	$_SESSION["errorOrder"] = $e->getMessage();
	
} ?>

<!DOCTYPE html> <html>
	<head>
			<meta http-equiv= "refresh" content="0; url=/FoodTruck/view/OrderPage.php" />
	</head>
</html> 	 