<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $data = json_decode(file_get_contents("php://input"));
  $note->title = $data->title;
  $note->body = $data->body;
  $note->url = $data->url;
  $note->author_id = $data->author_id;
  $note->label_name = $data->label_name;
  if($note->create()) {
    echo json_encode(
      array('message' => 'New Note Created')
    );
  } else {
    echo json_encode(
      array('message' => 'Note Not Created')
    );
  }

