<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Food Truck Management</title>
		<style>
			.error {color: #FF0000;}
		</style>
	</head>
	<body>
		<?php 
			require_once "model/Equipment.php";
			require_once "model/Staff.php";
				
			session_start();
		?>
		<form action="addEquipment.php" method="post">
			<p>Equipment name? <input type ="text" name ="equipment_name" />
				<span class="error">
				<?php 
				if (isset($_SESSION['errorEquipmentName']) && !empty($_SESSION['errorEquipmentName'])){
					echo "*" . $_SESSION["errorEquipmentName"];
				}
				?>
			</span></p>
			<p>Equipment quantity? <input type ="text" name ="equipment_quantity" />
				<span class="error">
				<?php 
				if (isset($_SESSION['errorEquipmentQuantity']) && !empty($_SESSION['errorEquipmentQuantity'])){
					echo "*" . $_SESSION["errorEquipmentQuantity"];
				}
				?>
			</span></p>
			<p><input type="submit" value="Add Equipment"/></p>
		</form>
		<form action="addSupply.php" method="post">
			<p>Supply name? <input type ="text" name ="supply_name" />
				<span class="error">
				<?php 
				if (isset($_SESSION['errorSupplyName']) && !empty($_SESSION['errorSupplyName'])){
					echo "*" . $_SESSION["errorSupplyName"];
				}
				?>
			</span></p>
			<p>Supply quantity? <input type ="text" name ="supply_quantity" />
				<span class="error">
				<?php 
				if (isset($_SESSION['errorSupplyQuantity']) && !empty($_SESSION['errorSupplyQuantity'])){
					echo "*" . $_SESSION["errorSupplyQuantity"];
				}
				?>
			</span></p>
			<p><input type="submit" value="Add Supply"/></p>
		</form>
		<p><input name="Back" type="button" value="Back" onclick="window.open('ManagerPage.php','_self')"/> </p>
	</body>
</html>