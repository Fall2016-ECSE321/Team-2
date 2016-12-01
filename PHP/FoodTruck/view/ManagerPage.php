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
		<h1>Would you like to manage:</h1>
		<input name="EquipmentSupply" type="button" value="Equipments & Supplies" onclick="window.open('EquipmentSupplyPage.php','_self')"/>
		<input name="Staff" type="button" value="Staffs" onclick="window.open('StaffCreationPage.php','_self')"/>
		<input name="MenuItem" type="button" value="Menu Items" onclick="window.open('MenuItemPage.php','_self')"/>
		
		<p><input name="Back" type="button" value="Back" onclick="window.open('../index.php','_self')"/> </p>
	</body>	
</html>