<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  include_once '../../includes/HttpLogger.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $note->id = isset($_GET['id']) ? $_GET['id'] : die();
  error_log('Request to Access Note ID: ' . $note->id);
  $note->read_single();
  $note_arr = array();
  $note_item = array(
    'id' => $note->id,
    'title' => $note->title,
    'body' => $note->body,
    'url' => $note->url,
    'user_id' => $note->user_id,
    'label_name' => $note->label_name
  );
  array_push($note_arr, $note_item);
  print_r(json_encode($note_arr));
$database->disconnect($db);