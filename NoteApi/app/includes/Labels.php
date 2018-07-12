<?php

require 'Logger.php';
require  'HttpLogger.php';
  class Labels {
  
    private $con;
    private $table = 'labels';
    public $id;
    public $name;
    public $created_at;
    public function __construct($db) {
      $this->con = $db;
    }
    public function read() {
      error_log('Invoked read() Method');
        try{
            $query = 'SELECT id, name, created_at FROM ' . $this->table . ' ORDER BY created_at DESC';
            $stmt = $this->con->prepare($query);
            if($stmt->execute()) {
                error_log('Retrieved Labels List');
                return $stmt;
            }else{
                throw new PDOException();
            }
        }catch(\PDOException $e) {
            error_log('Error while retrieving labels: ' . $e->getMessage());
        return $e;
        }
    }
  }