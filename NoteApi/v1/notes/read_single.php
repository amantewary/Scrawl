<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $note->id = isset($_GET['id']) ? $_GET['id'] : die();
  $note->read_single();
  $note_arr = array(
    'id' => $note->id,
    'title' => $note->title,
    'body' => $note->body,
    'author' => $note->author_id,
    'label_id' => $note->label_id,
    'label_name' => $note->label_name
  );
  print_r(json_encode($note_arr));