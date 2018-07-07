<?php 
  class Notes {
    private $con;
    private $table = 'notes';

    public function __construct($db) {
      $this->con = $db;
    }
    public $id;
    public $label_name;
    public $title;
    public $body;
    public $url;
    public $author_id;
    public $created_at;
    public function create() {

      $query = 'INSERT INTO ' . 
          $this->table . '
        SET
          title = :title,
          body = :body,
          url = :url,
          author_id = :author_id,
          label_name = :label_name';

      $stmt = $this->con->prepare($query);
      $this->title = htmlspecialchars(strip_tags($this->title));
      $this->body = htmlspecialchars(strip_tags($this->body));
      $this->url = htmlspecialchars(strip_tags($this->url));
      $this->author_id = htmlspecialchars(strip_tags($this->author_id));
      $this->label_name = htmlspecialchars(strip_tags($this->label_name)); 
      $stmt->bindParam(':title', $this->title);
      $stmt->bindParam(':body', $this->body);
      $stmt->bindParam(':url', $this->url);
      $stmt->bindParam(':author_id', $this->author_id);
      $stmt->bindParam(':label_name', $this->label_name);
      if($stmt->execute()) {
        return true;
      }   
      printf("Error: %s.\n", $stmt->error);
      return false;
    }
    public function read() {
      $query = 'SELECT n.id, n.label_name, n.title, n.body, n.url, n.author_id, n.created_at FROM ' . $this->table . ' n ORDER BY n.created_at DESC';
      $stmt = $this->con->prepare($query);
      $stmt->execute();
      return $stmt;
    }
    public function read_single() {
       $query = 'SELECT n.id, n.label_name, n.title, n.body, n.author_id, n.created_at FROM ' . $this->table . ' n  WHERE n.id = ? LIMIT 0,1';
      $stmt = $this->con->prepare($query);
      $stmt->bindParam(1, $this->id);
      $stmt->execute();
      $row = $stmt->fetch(PDO::FETCH_ASSOC);
      $this->title = $row['title'];
      $this->body = $row['body'];
      $this->url = $row['url'];
      $this->author_id = $row['author_id'];
      $this->label_name = $row['label_name'];
    }
    public function update() {
      $query = 'UPDATE ' . 
          $this->table . '
        SET
          title = :title,
          body = :body,
          url = :url,
          author_id = :author_id,
          label_name = :label_name
        WHERE
          id = :id';
      $stmt = $this->con->prepare($query);
      $this->title = htmlspecialchars(strip_tags($this->title));
      $this->body = htmlspecialchars(strip_tags($this->body));
      $this->url = htmlspecialchars(strip_tags($this->url));
      $this->author_id = htmlspecialchars(strip_tags($this->author_id));
      $this->label_name = htmlspecialchars(strip_tags($this->label_name));
      $this->id = htmlspecialchars(strip_tags($this->id));
      $stmt->bindParam(':title', $this->title);
      $stmt->bindParam(':body', $this->body);
      $stmt->bindParam(':url', $this->url);
      $stmt->bindParam(':author_id', $this->author_id);
      $stmt->bindParam(':label_name', $this->label_name);
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