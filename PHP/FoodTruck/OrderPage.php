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
			require_once "model/Order.php";
			require_once "model/MenuItem.php";
			require_once "model/Supply.php";
			require_once "model/FTMS.php";
			require_once 'persistence/PersistenceFoodTruck.php';
				
			session_start();

			//Retreive data from model
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
				echo "<tr><td>#" . $index . "      " . $menuitem->getName() . "</td>";
				$supplies= $menuitem->getSupplies();
				$supplylist = "";
				$index ++;
				foreach ($supplies as $supply){
					$supplylist .= $supply->getName() . ", ";
				}
				$supplylist = rtrim($supplylist,', ');
				
				echo "<td>" . $supplylist . "</td></tr>";
			}
			echo"</table></p>";
			
			//show orders in store in table format
			echo "<p><table style = 'width:100%'>
					<caption>Current Orders</caption>
					<tr>
						<th>Order ID</th>
						<th>Items Ordered</th>
					</tr>";
			$index = 1;
			foreach ($rm->getOrders() as $order){
				echo "<tr><td>  #" . $index . "</td>";
				$menuitems= $order->getMenuItems();
				$menuitemsList = "";
				$index++;
				foreach ($menuitems as $item){
					$menuitemsList .= $item->getName() . ", ";
				}
				rtrim($menuitemsList);
				$menuitemsList = rtrim($menuitemsList,', ');
				echo "<td>" . $menuitemsList . "</td></tr>";
			}
			echo"</table></p></div>";
			
			//ask for menu item ingredients
			echo "<div id = 'form'><form action='addOrder.php' method='post'>";
			echo "<p>Order #1 <select name = 'itemspinner1'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getMenuItems() as $item){
				echo "<option>" . $item->getName() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorOrder']) && !empty($_SESSION['errorOrder'])){
				echo "*" . $_SESSION["errorOrder"];
			}
			echo "</span><p>";
			
			echo "<p>Order #2 <select name = 'itemspinner2'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getMenuItems() as $item){
				echo "<option>" . $item->getName() . "</option>";
			}
			echo "</select><p>";
			echo "</span><p>";
			echo "<p>Order #3 <select name = 'itemspinner3'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getMenuItems() as $item){
				echo "<option>" . $item->getName() . "</option>";
			}
			echo "</select><p>";
			echo "</span><p>";
			echo "<p>Order #4 <select name = 'itemspinner4'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getMenuItems() as $item){
				echo "<option>" . $item->getName() . "</option>";
			}
			echo "</select><p>";
			echo "</span><p>";
			echo "<p>Order #5 <select name = 'itemspinner5'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getMenuItems() as $item){
				echo "<option>" . $item->getName() . "</option>";
			}
			echo "</select><p>";
				
			echo "<p><input type= 'submit' value='Place Order' /></p>";
			echo "</form>";
			?>
		

		<p><input name="Back" type="button" value="Back" onclick="window.open('StaffPage.php','_self')"/> </p>
	</div>
	</body>
</html>