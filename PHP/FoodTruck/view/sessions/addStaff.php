<?php
require_once '../../controller/Controller.php';

session_start( );

$_SESSION["errorStaffName"] = "";
$_SESSION["errorStaffRole"] = "";

$c = new Controller();
try {
	$name = $_POST['staff_name'];
	$role = $_POST['staff_role'];
	$c->createStaff($name,$role);
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage());
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorStaffName"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "2") {
			$_SESSION["errorStaffRole"] = substr($error, 1);
		}
	}}
	?>
<!DOCTYPE html> 
<html>
	<head>
			<meta http-equiv= "refresh" content="0; url=/FoodTruck/view/StaffCreationPage.php" />
	</head>
</html>
