<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $data = json_decode(file_get_contents("php://input"));
  $note->id = $data->id;
  $note->title = $data->title;
  $note->body = $data->body;
  $note->url = $data->url;
  $note->author_id = $data->author_id;
  $note->label_name = $data->label_name;
  if($note->update()) {
    echo json_encode(
      array('message' => 'Note Updated')
    );
  } else {
    echo json_encode(
      array('message' => 'Note Not Updated')
    );
  }

