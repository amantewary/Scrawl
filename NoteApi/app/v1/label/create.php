<?php 
  // Headers
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: POST');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, userization, X-Requested-With');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Labels.php';
  include_once '../../includes/HttpLogger.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $label = new Labels($db);
  $data = json_decode(file_get_contents("php://input"));
  $label->name = $data->name;
  $label->user_id = $data->user_id;
  error_log('Request Access To Create Notes By User ID: ' . $label->user_id);
  if($label->create()) {
    echo json_encode(
      array('message' => 'New Label Created')
    );
    $database->disconnect($db);
  } else {
    echo json_encode(
      array('message' => 'Label Not Created')
    );
      $database->disconnect($db);
  }