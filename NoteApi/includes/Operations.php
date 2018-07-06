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
	function createNote($notetitle, $notelabel, $notebody, $author_id){
		$stmt = $this->con->prepare("INSERT INTO notes (notetitle, notelabel, notebody, author_id) VALUES (?, ?, ?, ?)");
		$stmt->bind_param("sssi", $notetitle, $notelabel, $notebody, $author_id);
		if($stmt->execute())
			return true; 
		return false; 
	}

	/*
	* The read operation
	* When this method is called it is returning all the existing record of the database
	*/
	function getNotes(){
		$stmt = $this->con->prepare("SELECT id, notetitle, notelabel, notebody, author_id FROM notes");
		$stmt->execute();
		$stmt->bind_result($id, $notetitle, $notelabel, $notebody, $author_id);
		
		$notes = array(); 
		
		while($stmt->fetch()){
			$note  = array();
			$note['id'] = $id; 
			$note['notetitle'] = $notetitle; 
			$note['notelabel'] = $notelabel; 
			$note['notebody'] = $notebody;
			$note['author_id'] = $author_id;
			
			array_push($notes, $note); 
		}
		
		return $notes; 
	}
	
	/*
	* The update operation
	* When this method is called the record with the given id is updated with the new given values
	*/
	function updateNote($id, $notetitle, $notelabel, $notebody, $author_id){
		$stmt = $this->con->prepare("UPDATE notes SET notetitle = ?, notelabel = ?, notebody = ?, author_id = ? WHERE id = ?");
		$stmt->bind_param("sssii", $notetitle, $notelabel, $notebody, $author_id, $id);
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