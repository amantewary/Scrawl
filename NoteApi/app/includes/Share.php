<?php

require 'Logger.php';
require 'HttpLogger.php';
class Share
{
    private $con;
    private $table = 'shares';

    public function __construct($db)
    {
        $this->con = $db;
    }

    public $id;
    public $share_from;
    public $share_to;
    public $note_id;
    public $shared_at;
    public $user_id;

    public function create()
    {
        error_log('Invoked create() Method');
        $query = 'INSERT INTO ' .
            $this->table . '
            SET
            share_from = :share_from,
            share_to = :share_to,
            note_id = :note_id';

        $stmt = $this->con->prepare($query);
        $this->share_from = htmlspecialchars(strip_tags($this->share_from));
        $this->share_to = htmlspecialchars(strip_tags($this->share_to));
        $this->note_id = htmlspecialchars(strip_tags($this->note_id));

        $stmt->bindParam(':share_from', $this->share_from);
        $stmt->bindParam(':share_to', $this->share_to);
        $stmt->bindParam(':note_id', $this->note_id);

        if ($stmt->execute()) {
            error_log('Share record Created');
            return true;
        }
        error_log("Error: %s.\n", $stmt->error);
        return false;
    }

    public function readNoteIdByUserId()
    {
        error_log('Invoked readNoteIdByUserId() Method');
        try{
            $query = 'SELECT n.id, n.note_id FROM ' . $this->table . ' n WHERE n.share_to = :user_id';
            $stmt = $this->con->prepare($query);
            if($stmt->execute()) {
                error_log('Retrieved Share records that are shared to the current user');
                return $stmt;
            }else{
                throw new PDOException();
            }
        }catch (\PDOException $e) {
            error_log('Error while retrieving share records: ' . $e->getMessage());
            return $e;
        }
    }

    public function delete()
    {
        error_log('Invoked delete() Method');
        $query = 'DELETE FROM ' . $this->table . ' WHERE id = :id';
        $stmt = $this->con->prepare($query);
        $this->id = htmlspecialchars(strip_tags($this->id));
        $stmt->bindParam(':id', $this->id);
        if ($stmt->execute()) {
            error_log('Share record Deleted');
            return true;
        }
        error_log("Error: %s.\n", $stmt->error);
        return false;
    }

}