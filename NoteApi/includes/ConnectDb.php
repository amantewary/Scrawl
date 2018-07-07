<?php 
  class ConnectDb {
    private $host = 'db-5308.cs.dal.ca';
    private $db_name = 'CSCI5308_19_DEVINT';
    private $username = 'CSCI5308_19_DEVINT_USER';
    private $password = 'CSCI5308_19_DEVINT_19248';
    private $conn;
    
    public function connect() {
      $this->conn = null;
      try { 
        $this->conn = new PDO('mysql:host=' . $this->host . ';dbname=' . $this->db_name, $this->username, $this->password);
        $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      } catch(PDOException $e) {
        echo 'Connection Error: ' . $e->getMessage();
      }
      return $this->conn;
    }
  }