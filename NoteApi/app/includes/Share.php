<?php

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

        $query = 'INSERT INTO ' .
            $this->table . '
            SET
            share_from = :share_from,
            share_to = :share_to,
            note_id = :note_id,
            shared_at = :shared_at';

        $stmt = $this->con->prepare($query);
        $this->share_from = htmlspecialchars(strip_tags($this->share_from));
        $this->share_to = htmlspecialchars(strip_tags($this->share_to));
        $this->note_id = htmlspecialchars(strip_tags($this->note_id));
        $this->shared_at = htmlspecialchars(strip_tags($this->shared_at));

        $stmt->bindParam(':share_from', $this->share_from);
        $stmt->bindParam(':share_to', $this->share_to);
        $stmt->bindParam(':note_id', $this->note_id);
        $stmt->bindParam(':shared_at', $this->shared_at);

        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

    public function readNoteIdByUserId()
    {
        $query = 'SELECT n.id, n.note_id FROM ' . $this->table . ' n WHERE n.share_to = :user_id';
        $stmt = $this->con->prepare($query);
        $stmt->execute();
        return $stmt;
    }

    public function delete()
    {
        $query = 'DELETE FROM ' . $this->table . ' WHERE id = :id';
        $stmt = $this->con->prepare($query);
        $this->id = htmlspecialchars(strip_tags($this->id));
        $stmt->bindParam(':id', $this->id);
        if ($stmt->execute()) {
            return true;
        }
        printf("Error: %s.\n", $stmt->error);
        return false;
    }

}