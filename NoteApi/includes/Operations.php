<?php
 
class Operations
{
    private $con;
    function __construct()
    {
        require_once dirname(__FILE__) . '/ConnectDb.php';
        $db = new ConnectDb();
        $this->con = $db->connect();
    }
	
	/*
	* The create operation
	* When this method is called a new record is created in the database
	*/
	function createNote($notetitle, $notelable, $notebody){
		$stmt = $this->con->prepare("INSERT INTO notes (notetitle, notelable, notebody) VALUES (?, ?, ?)");
		$stmt->bind_param("sss", $notetitle, $notelable, $notebody);
		if($stmt->execute())
			return true; 
		return false; 
	}

	/*
	* The read operation
	* When this method is called it is returning all the existing record of the database
	*/
	function getNotes(){
		$stmt = $this->con->prepare("SELECT id, notetitle, notelable, notebody FROM notes");
		$stmt->execute();
		$stmt->bind_result($id, $notetitle, $notelable, $notebody);
		
		$notes = array(); 
		
		while($stmt->fetch()){
			$note  = array();
			$note['id'] = $id; 
			$note['notetitle'] = $notetitle; 
			$note['notelable'] = $notelable; 
			$note['notebody'] = $notebody;
			
			array_push($notes, $note); 
		}
		
		return $notes; 
	}
	
	/*
	* The update operation
	* When this method is called the record with the given id is updated with the new given values
	*/
	function updateNote($id, $notetitle, $notelable, $notebody){
		$stmt = $this->con->prepare("UPDATE notes SET notetitle = ?, notelable = ?, body = ? WHERE id = ?");
		$stmt->bind_param("sssi", $notetitle, $notelable, $notebody, $id);
		if($stmt->execute())
			return true; 
		return false; 
	}
	
	
	/*
	* The delete operation
	* When this method is called record is deleted for the given id 
	*/
	function deleteNote($id){
		$stmt = $this->con->prepare("DELETE FROM notes WHERE id = ? ");
		$stmt->bind_param("i", $id);
		if($stmt->execute())
			return true; 
		
		return false; 
	}
}