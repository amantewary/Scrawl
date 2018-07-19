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
            $query = 'CALL spGetLabels(:user_id)';
            $stmt = $this->con->prepare($query);
            $this->user_id = htmlspecialchars(strip_tags($this->user_id));
            $stmt->bindParam(':user_id', $this->user_id);
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

    public function create()
    {
        error_log('Invoked create() Method Inside Labels');
        $query = 'CALL spCreateLabel(:name, :user_id)';

        $stmt = $this->con->prepare($query);
        $this->name = htmlspecialchars(strip_tags($this->name));
        $this->user_id = htmlspecialchars(strip_tags($this->user_id));
        $stmt->bindParam(':name', $this->name);
        $stmt->bindParam(':user_id', $this->user_id);
        try {
            if ($stmt->execute()) {
                error_log('Label Created');
                return true;
            }else {
                throw new PDOException();
            }
        }catch (\PDOException $e) {
            error_log("Error: " . $e->getMessage());
            return $e;
        }
    }

    public function delete()
    {

        $query = 'CALL spDeleteLabel(:name)';
        $stmt = $this->con->prepare($query);
        $this->name = htmlspecialchars(strip_tags($this->name));
        $stmt->bindParam(':name', $this->name);
        try {
            $stmt->execute();
            if ($stmt->rowCount()) {
                error_log('Label Deleted');
                return true;
            }else{
                error_log("Deletion Failed");
                return false;
            }
        }catch(\PDOException $e) {
            error_log("Error: " . $e->getMessage());
        }
    }
  }