<?php

require 'Logger.php';
require 'HttpLogger.php';
class Notes
{
    private $con;
    private $table = 'notes';

    public function __construct($db)
    {
        $this->con = $db;
    }

    public $id;
    public $label_name;
    public $title;
    public $body;
    public $url;
    public $user_id;
    public $created_at;
    public $status;
    public $date;

    public function create()
    {
        error_log('Invoked create() Method Inside Notes Class');
        $query = 'CALL spCreateNote(:label_name, :title, :body, :url, :user_id, :status, :date)';

        $stmt = $this->con->prepare($query);
        $this->label_name = htmlspecialchars(strip_tags($this->label_name));
        $this->title = htmlspecialchars(strip_tags($this->title));
        $this->body = htmlspecialchars(strip_tags($this->body));
        $this->url = htmlspecialchars(strip_tags($this->url));
        $this->user_id = htmlspecialchars(strip_tags($this->user_id));
        $this->status = htmlspecialchars(strip_tags($this->status));
        $this->date = htmlspecialchars(strip_tags($this->date));
        $stmt->bindParam(':label_name', $this->label_name);
        $stmt->bindParam(':title', $this->title);
        $stmt->bindParam(':body', $this->body);
        $stmt->bindParam(':url', $this->url);
        $stmt->bindParam(':user_id', $this->user_id);
        $stmt->bindParam(':status', $this->status);
        $stmt->bindParam(':date', $this->date);
        try {
            if ($stmt->execute()) {
                error_log('Note Created by User: ' . $this->user_id);
                return $stmt;
            }
        }catch (\PDOException $e) {
            error_log("[Error] While Creating Notes: " . $e->getMessage() . "By User: " . $this->user_id);
            return $e;
        }
    }

    public function read()
    {
        error_log('Invoked read() Method');
        try {
            $query = 'CALL spGetNotes()';
            $stmt = $this->con->prepare($query);
            if($stmt->execute()) {
                error_log('Retrieved Notes List');
                return $stmt;
            }
        } catch (\PDOException $e) {
            error_log('Error while retrieving notes: ' . $e->getMessage());
            return $e;
        }
    }

    public function read_single()
    {
        error_log('Invoked read_single() Method');
        try {
            $query = 'CALL spGetNoteById(?)';
            $stmt = $this->con->prepare($query);
            $stmt->bindParam(1, $this->id);
            if($stmt->execute()) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                $this->title = $row['title'];
                $this->body = $row['body'];
                $this->url = $row['url'];
                $this->user_id = $row['user_id'];
                $this->label_name = $row['label_name'];
                $this->status = $row['status'];
                $this->date = $row['date'];
                error_log('Retrieved Note');
                return $stmt;
            }
        } catch (\PDOException $e) {
            print_r($e);
            error_log('Note Not Available: ' . $e->getMessage());
            return $e;
        }
    }

    public function readByLabel()
    {
        error_log('Invoked readByLabel Method');
        try {
            $query = 'CALL spGetNoteByLabel(:label_name, :user_id)';
            $stmt = $this->con->prepare($query);
            $this->label_name = htmlspecialchars(strip_tags($this->label_name));
            $this->user_id = htmlspecialchars(strip_tags($this->user_id));
            $stmt->bindParam(':label_name', $this->label_name);
            $stmt->bindParam(':user_id', $this->user_id);
            if($stmt->execute()) {
                error_log('Retrieved Note');
                return $stmt;
            }
        } catch (\PDOException $e) {
            print_r($e);
            error_log('Note Not Available: ' . $e->getMessage());
            return $e;
        }
    }

    public function readByUser()
    {
        error_log('Invoked readByUser() Method Inside Notes Class');
        try {
            $query = 'CALL spGetNoteByUser(:user_id)';
            $stmt = $this->con->prepare($query);
            $this->user_id = htmlspecialchars(strip_tags($this->user_id));
            $stmt->bindParam(':user_id', $this->user_id);
            if($stmt->execute()) {
                error_log('Retrieved Note By User ID: ' . $this->user_id);
                return $stmt;
            }
        } catch (\PDOException $e) {
            print_r($e);
            error_log('Note Not Available: ' . $e->getMessage());
            return $e;
        }
    }

    public function update()
    {
        $query = 'CALL spUpdateNote(:label_name, :title, :body, :url, :user_id, :id, :status, :date)';
        $stmt = $this->con->prepare($query);
        $this->title = htmlspecialchars(strip_tags($this->title));
        $this->body = htmlspecialchars(strip_tags($this->body));
        $this->url = htmlspecialchars(strip_tags($this->url));
        $this->user_id = htmlspecialchars(strip_tags($this->user_id));
        $this->label_name = htmlspecialchars(strip_tags($this->label_name));
        $this->id = htmlspecialchars(strip_tags($this->id));
        $this->status = htmlspecialchars(strip_tags($this->status));
        $this->date = htmlspecialchars(strip_tags($this->date));
        $stmt->bindParam(':label_name', $this->label_name);
        $stmt->bindParam(':title', $this->title);
        $stmt->bindParam(':body', $this->body);
        $stmt->bindParam(':url', $this->url);
        $stmt->bindParam(':user_id', $this->user_id);
        $stmt->bindParam(':id', $this->id);
        $stmt->bindParam(':status', $this->status);
        $stmt->bindParam(':date', $this->date);
        try {
            if ($stmt->execute()) {
                error_log('Note Updated');
                return $stmt;
            }
        }catch(\PDOException $e) {
            error_log("[Error] " .  $e->getMessage());
            return $e;
        }
    }

    public function delete()
    {

        $query = 'CALL spDeleteNote(:id)';
        $stmt = $this->con->prepare($query);
        $this->id = htmlspecialchars(strip_tags($this->id));
        $stmt->bindParam(':id', $this->id);
        try {
            $stmt->execute();
            if ($stmt->rowCount()) {
                error_log('Note Deleted with ID: ' . $this->id);
                return $stmt;
            }
        }catch(\PDOException $e) {
            error_log("[Error] Note Deletion Failed: " . $e->getMessage() . "With Note ID: " . $this->id);
        }
    }


    public function readAllNotesByUser($share_to, $userid)
    {
        error_log('Invoked readAllNotes Method');
        try {
            $query = 'CALL spGetAllNotesByUser(:share_to, :userid)';
            $stmt = $this->con->prepare($query);
            $stmt->bindParam(':share_to', $share_to);
            $stmt->bindParam(':userid', $userid);

            if($stmt->execute()) {
                error_log('Successfully retrieved all shared and owned notes');
                return $stmt;
            }
        } catch (\PDOException $e) {
            print_r($e);
            error_log('Note Not Available: ' . $e->getMessage());
            return $e;
        }
    }

}