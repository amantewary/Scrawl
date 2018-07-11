<?php
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
      $stmt->execute();
      error_log('Retrieved Labels List');
      return $stmt;
    }catch(\Exception $e) {
      error_log('Error while retrieving labels: ' . $e->getMessage());
    }
    }
  }