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
			require_once "model/Staff.php";
			require_once "model/TimeBlock.php";
			require_once "model/FTMS.php";
			require_once 'persistence/PersistenceFoodTruck.php';
				
			session_start();

			//Retrive data from model
			$pm = new PersistenceFoodTruck();
			$rm = $pm->loadDataFromStore();
			//Print staffs from store in table format
			echo "<div id='display'><p><table style = 'width:100%'>
					<caption>Current Staffs and Schedules</caption>
					<tr>
						<th>Name</th>
						<th>Role</th>
						<th>Monday</th>
						<th>Tuesday</th>
						<th>Wednesday</th>
						<th>Thursday</th>
						<th>Friday</th>
						<th>Saturday</th>
						<th>Sunday</th>
					</tr>";
			//to sort the schedules
			$dayOfWeek = array("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
			foreach ($rm->getStaffs() as $staff){
				echo "<tr><td>" . $staff->getName() . "</td>";
				echo "<td>" . $staff->getRole() . "</td>";
				$schedule = $staff->getTimeBlocks();
				$totalSched = array(array(),array(),array(),array(),array(),array(),array());
				foreach ($schedule as $timeblock){
					$index = 0;
						foreach($totalSched as $key => $dayofweekArray){
							if(strcmp($timeblock->getDayOfWeek(), $dayOfWeek[$index])==0){
								$totalSched[$key][$timeblock->getStartTime()] = $timeblock;
							}
							$index++;
						}
				}
				
				foreach($totalSched as $dayArray){
						ksort($dayArray,SORT_NUMERIC);
						echo "<td>";
						foreach($dayArray as $sortedtime){
							echo $sortedtime->getStartTime() . " - " . $sortedtime->getEndTime() . "<br/>";				
						}
						echo "</td>";
				}
				echo"</tr>";
			}
			echo"</table></p></div>";
		?>
		<div id="form"><form action="addStaff.php" method="post">
			<p>Staff name? <input type ="text" name ="staff_name" />
				<span class="error">
				<?php 
				if (isset($_SESSION['errorStaffName']) && !empty($_SESSION['errorStaffName'])){
					echo "*" . $_SESSION["errorStaffName"];
				}
				?>
			</span></p>
			<p>Staff role? <input type ="text" name ="staff_role" />
				<span class="error">
				<?php 
				if (isset($_SESSION['errorStaffRole']) && !empty($_SESSION['errorStaffRole'])){
					echo "*" . $_SESSION["errorStaffRole"];
				}
				?>
			</span></p>
			<p><input type="submit" value="Register Staff"/></p>
		</form>
		
		<form action="addTimeBlock.php" method="post">
		<?php 
			//ask for day of the week of time block
			echo "<p>Which Day? <select name = 'dayspinner'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($dayOfWeek as $day){
				echo "<option>" . $day . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorDay']) && !empty($_SESSION['errorDay'])){
				echo "*" . $_SESSION["errorDay"];
			}
			echo "</span><p>";
			?>
			<p>Start Time? <input type="time" name="starttime" value="<?php echo date('H:i'); ?>" />
			<span class="error">
				<?php 
				if (isset($_SESSION['errorStarttimeFormat']) && !empty($_SESSION['errorStarttimeFormat'])){
					echo "*" . $_SESSION["errorStarttimeFormat"];
				}
				?>
			</span></p>
			<p>End Time? <input type="time" name="endtime" value="<?php echo date('H:i'); ?>" />
			<span class="error">
				<?php 
				if (isset($_SESSION['errorEndtimeFormatOrWrongOrder']) && !empty($_SESSION['errorEndtimeFormatOrWrongOrder'])){
					echo "*" . $_SESSION["errorEndtimeFormatOrWrongOrder"];
				}
				?>
			</span></p>
			<?php 
			//ask for corresponding staff
			echo "<p>Which Staff? <select name = 'staffspinner'>";
			echo" <option disabled selected value> -- select an option -- </option>";
			foreach ($rm->getStaffs() as $staff){
				echo "<option>" . $staff->getName() . "</option>";
			}
			echo "</select><span class='error'>";
			if (isset($_SESSION['errorTimeBlockStaff']) && !empty($_SESSION['errorTimeBlockStaff'])){
				echo "*" . $_SESSION["errorTimeBlockStaff"];
			}
			echo "</span><p>";
			?>
			<p><input type="submit" value="Add Time Block to Staff"/></p>
		</form>
		<p><input name="Back" type="button" value="Back" onclick="window.open('ManagerPage.php','_self')"/> </p>
	</div>
	</body>
</html>