<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: PUT');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, userization, X-Requested-With');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Notes.php';
  include_once '../../includes/HttpLogger.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $note = new Notes($db);
  $data = json_decode(file_get_contents("php://input"));
  $note->id = $data->id;
  $note->title = $data->title;
  $note->body = $data->body;
  $note->url = $data->url;
  $note->user_id = $data->user_id;
  $note->label_name = $data->label_name;
  $note->status = $data->status;
  $note->date = $data->date;
  error_log('Request Access To Update Notes By User ID: ' . $note->user_id);
  if($note->update()) {
    echo json_encode(
      array('message' => 'Note Updated')
    );
      $database->disconnect($db);
  } else {
    echo json_encode(
      array('message' => 'Note Not Updated')
    );
      $database->disconnect($db);
  }

