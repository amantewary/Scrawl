<?php 
  class Notes {
    private $con;
    private $table = 'notes';

    public function __construct($db) {
      $this->con = $db;
    }
    public $id;
    public $label_id;
    public $label_name;
    public $title;
    public $body;
    public $author_id;
    public $created_at;
    public function create() {

      $query = 'INSERT INTO ' . 
          $this->table . '
        SET
          title = :title,
          body = :body,
          author_id = :author_id,
          label_id = :label_id';

      $stmt = $this->con->prepare($query);
      $this->title = htmlspecialchars(strip_tags($this->title));
      $this->body = htmlspecialchars(strip_tags($this->body));
      $this->author_id = htmlspecialchars(strip_tags($this->author_id));
      $this->label_id = htmlspecialchars(strip_tags($this->label_id)); 
      $stmt->bindParam(':title', $this->title);
      $stmt->bindParam(':body', $this->body);
      $stmt->bindParam(':author_id', $this->author_id);
      $stmt->bindParam(':label_id', $this->label_id);
      if($stmt->execute()) {
        return true;
      }   
      printf("Error: %s.\n", $stmt->error);
      return false;
    }
    public function read() {
      $query = 'SELECT l.name as label_name, n.id, n.label_id, n.title, n.body, n.author_id, n.created_at FROM ' . $this->table . ' n LEFT JOIN labels l ON n.label_id = l.id ORDER BY n.created_at DESC';
      $stmt = $this->con->prepare($query);
      $stmt->execute();
      return $stmt;
    }
    public function read_single() {
       $query = 'SELECT l.name as label_name, n.id, n.label_id, n.title, n.body, n.author_id, n.created_at FROM ' . $this->table . ' p LEFT JOIN labels l ON n.label_id = l.id WHERE n.id = ? LIMIT 0,1';
      $stmt = $this->con->prepare($query);
      $stmt->bindParam(1, $this->id);
      $stmt->execute();
      $row = $stmt->fetch(PDO::FETCH_ASSOC);
      $this->title = $row['title'];
      $this->body = $row['body'];
      $this->author_id = $row['author_id'];
      $this->label_id = $row['label_id'];
      $this->label_name = $row['label_name'];
    }
    public function update() {
      $query = 'UPDATE ' . 
          $this->table . '
        SET
          title = :title,
          body = :body,
          author_id = :author_id,
          label_id = :label_id
        WHERE
          id = :id';
      $stmt = $this->con->prepare($query);
      $this->title = htmlspecialchars(strip_tags($this->title));
      $this->body = htmlspecialchars(strip_tags($this->body));
      $this->author_id = htmlspecialchars(strip_tags($this->author_id));
      $this->label_id = htmlspecialchars(strip_tags($this->label_id));
      $this->id = htmlspecialchars(strip_tags($this->id));
      $stmt->bindParam(':title', $this->title);
      $stmt->bindParam(':body', $this->body);
      $stmt->bindParam(':author_id', $this->author_id);
      $stmt->bindParam(':label_id', $this->label_id);
      $stmt->bindParam(':id', $this->id);
      if($stmt->execute()) {
        return true;
      }
      printf("Error: %s.\n", $stmt->error);
      return false;
    }

    public function delete() {

      $query = 'DELETE FROM ' . $this->table . ' WHERE id = :id';
      $stmt = $this->con->prepare($query);
      $this->id = htmlspecialchars(strip_tags($this->id));
      $stmt->bindParam(':id', $this->id);
      if($stmt->execute()) {
        return true;
      }
      printf("Error: %s.\n", $stmt->error);
      return false;
    }
    
  }