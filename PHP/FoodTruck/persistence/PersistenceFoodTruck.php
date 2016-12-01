<?php
class PersistenceFoodTruck {
	private $filename;
	function __construct($filename = 'data.txt') {
		//create a file for data storing
		$this->filename = $filename;
	}

	function loadDataFromStore() {
		//load data from data.txt file, through FTMS model
		if (file_exists($this->filename)) {
			$str = file_get_contents($this->filename);
			$rm = unserialize($str);
		}
		else {
			$rm = FTMS::getInstance();
		}

		return $rm;
	}

	function writeDataToStore($rm) {
		//write data to data.txt file, through FTMS model
		$str = serialize($rm);
		file_put_contents($this->filename, $str);
	}
}
?>