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

      $query = 'SELECT id, name, created_at FROM ' . $this->table . ' ORDER BY created_at DESC';
      $stmt = $this->con->prepare($query);
      $stmt->execute();
      return $stmt;
    }
  }