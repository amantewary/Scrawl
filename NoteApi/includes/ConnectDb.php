<?php 
	

	class ConnectDb
	{
		private $con;
		function __construct()
		{
	 
		}
		function connect()
		{
		
			$this->con = null;

			try{

				include_once dirname(__FILE__) . '/Constants.php';
				$this->con = new PDO('mysql:host='.DB_HOST . ';dbname='. DB_NAME, DB_USER, DB_PASS);
				$this->con->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

			} catch(PDOException $e){
				echo 'Connection Error:' . $e->getMessage();
			}
			
			return $this->con;
		}
	 
	}