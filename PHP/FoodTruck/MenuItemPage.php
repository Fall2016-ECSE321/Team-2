<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Food Truck Management</title>
		<style>
			table, th, td {
				border: 1px solid black;
				border-collapse: collapse;
			}
			#display{
				float: right;
				width: 50%;
			}
			#form{
				float: left;
			}
			.error {color: #FF0000;}
		</style>
	</head>
	<body>
		<?php 
			require_once "model/MenuItem.php";
			require_once "model/Supply.php";
			require_once "model/FTMS.php";
			require_once 'persistence/PersistenceFoodTruck.php';
				
			session_start();

			//Retrive data from model
			$pm = new PersistenceFoodTruck();
			$rm = $pm->loadDataFromStore();
			
			//show menu items in store in table format
			echo "<div id='display'><p><table style = 'width:100%'>
					<caption>Current Menu</caption>
					<tr>
						<th>Menu Item</th>
						<th>Ingredients</th>
					</tr>";
			$index = 1;
			foreach ($rm->getMenuItems() as $menuitem){
				echo "<tr><td>  #" . $index . "      " . $menuitem->getName() . "</td>";
				$supplies= $menuitem->getSupplies();
				$supplylist = "";
				$index ++;
				foreach ($supplies as $supply){
					$supplylist .= $supply->getName() . ", ";
				}
				$supplylist = rtrim($supplylist,', ');
				echo "<td>" . $supplylist . "</td></tr>";
			}
			echo"</table></p></div>";

			//ask for menu item name
			echo "<div id = 'form'><form action='addMenuItem.php' method='post'>";
			echo"<p>Menu Item name? <input type ='text' name ='menuitem_name' />
			<span class='error'>";
			if (isset($_SESSION['errorMenuItemName']) && !empty($_SESSION['errorMenuItemName'])){
				echo "*" . $_SESSION["errorMenuItemName"];
			}
			echo"</span></p>";
			
			//ask for menu item ingredients
			echo "<p>Ingredient needed? <select name = 'supplyspinner1'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getSupplies() as $supply){
				echo "<option>" . $supply->getName() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorMenuItemSupply']) && !empty($_SESSION['errorMenuItemSupply'])){
				echo "*" . $_SESSION["errorMenuItemSupply"];
			}
			echo "</span><p>";
			
			echo "<p>Ingredient needed? <select name = 'supplyspinner2'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getSupplies() as $supply){
				echo "<option>" . $supply->getName() . "</option>";
			}
			echo "</select><p>";
			echo "</span><p>";
			echo "<p>Ingredient needed? <select name = 'supplyspinner3'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getSupplies() as $supply){
				echo "<option>" . $supply->getName() . "</option>";
			}
			echo "</select><p>";
			echo "</span><p>";
			echo "<p>Ingredient needed? <select name = 'supplyspinner4'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			
			foreach ($rm->getSupplies() as $supply){
				echo "<option>" . $supply->getName() . "</option>";
			}
			echo "</select><p>";
			echo "</span><p>";
			echo "<p>Ingredient needed? <select name = 'supplyspinner5'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getSupplies() as $supply){
				echo "<option>" . $supply->getName() . "</option>";
			}
			echo "</select><p>";
				
			echo "<p><input type= 'submit' value='Add MenuItem' /></p>";
			echo "</form>";
			?>
		

		<p><input name="Back" type="button" value="Back" onclick="window.open('ManagerPage.php','_self')"/> </p>
	</div>
	</body>
</html>