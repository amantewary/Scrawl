<?php 
  class ConnectDb {
    private $host = 'db-5308.cs.dal.ca';
    private $db_name = 'CSCI5308_19_DEVINT';
    private $username = 'CSCI5308_19_DEVINT_USER';
    private $password = 'CSCI5308_19_DEVINT_19248';
    private $conn;
    
    public function connect() {
      $this->conn = null;
      $dsn = 'mysql:host=' . $this->host . ';dbname=' . $this->db_name;
      $user = $this->username;
      $pass = $this->password;
      //PDO Connection Testing
  try { 
        $this->conn = new PDO($dsn, $user, $pass);
        $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      } catch(PDOException $e) {
        echo 'Connection Error: ' . $e->getMessage();
      }
      return $this->conn;
    }
  }