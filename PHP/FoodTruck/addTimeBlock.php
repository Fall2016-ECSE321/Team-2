<?php
require_once 'controller/Controller.php';

session_start( );

$_SESSION["errorDay"] = "";
$_SESSION["errorStarttimeFormat"] = "";
$_SESSION["errorEndtimeFormatOrWrongOrder"] = "";
$_SESSION["errorTimeBlockStaff"] = "";

$c = new Controller();
try {
	$day = NULL;
	$staff = NULL;
	if(isset($_POST['dayspinner']) && isset($_POST['staffspinner'])){
		$day = $_POST['dayspinner'];
		$staff = $_POST['staffspinner'];
	}
	
	$start = $_POST['starttime'];
	$end = $_POST['endtime'];
	
	if(strtotime($start) && strtotime($end)){
		$c->createTimeBlock(date('H:i', strtotime($start)),date('H:i', strtotime($end)),$day, $staff);
	}
} catch (Exception $e) {
	$errors = explode("@", $e->getMessage()); 
	foreach ($errors as $error) {
		if (substr($error, 0, 1) == "1") {
			$_SESSION["errorDay"] = substr($error, 1);
		} if (substr($error, 0, 1) == "2") {
			$_SESSION["errorStarttimeFormat"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "3") {
			$_SESSION["errorEndtimeFormatOrWrongOrder"] = substr($error, 1);
		}
		if (substr($error, 0, 1) == "4") {
			$_SESSION["errorTimeBlockStaff"] = substr($error, 1);
		}
	}}
?>
<!DOCTYPE html> 
<html>
	<head>
		<meta http-equiv= "refresh" content="0; url=/FoodTruck/StaffCreationPage.php"/>
	</head>
</html>

