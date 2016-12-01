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
			require_once "../model/Equipment.php";
			require_once "../model/Supply.php";
			require_once "../model/FTMS.php";
			require_once '../persistence/PersistenceFoodTruck.php';
				
			session_start();

			//Retrive data from model
			$pm = new PersistenceFoodTruck();
			$rm = $pm->loadDataFromStore();
			//Print equipments from store in table format
			echo "<div id='display'><p><table style = 'width:100%'>
					<caption>Current Equipments</caption>
					<tr>
						<th>Name</th>
						<th>Quantity</th>
					</tr>";
			foreach ($rm->getEquipment() as $equipment){
				echo "<tr><td>" . $equipment->getName() . "</td>";
				echo "<td>" . $equipment->getQuantity() . "</td></tr>";
			}
			echo"</table></p>";
			
			//Print supply from store in table format
			echo "<p><table style = 'width:100%'>
					<caption>Current Supplies</caption>
					<tr>
						<th>Name</th>
						<th>Quantity</th>
					</tr>";
			foreach ($rm->getSupplies() as $supply){
				echo "<tr><td>" . $supply->getName() . "</td>";
				echo "<td>" . $supply->getQuantity() . "</td></tr>";
			}
			echo"</tr></table></p></div>";
		?>
		<div id="form"><form action="sessions/addEquipment.php" method="post">
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
		<form action="sessions/addSupply.php" method="post">
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
	</div>
	</body>
</html>