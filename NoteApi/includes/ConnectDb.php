<?php 
	

	class ConnectDb
	{
		private $con;
		function __construct()
		{
	 
		}
		function connect()
		{
			
		try{
				include_once dirname(__FILE__) . '/Constants.php';
				$this->con = new PDO(DB_HOST, DB_USER, DB_PASS, DB_NAME);
				$this->con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		} catch(PDOException $e) {
			echo 'Connection Error: ' . $e->getMessage();
		}
			return $this->con;
		}
	 
	}