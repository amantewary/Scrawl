<?php 
  header('Access-Control-Allow-Origin: *');
  header('Content-Type: application/json');
  header('Access-Control-Allow-Methods: DELETE');
  header('Access-Control-Allow-Headers: Access-Control-Allow-Headers,Content-Type,Access-Control-Allow-Methods, Authorization, X-Requested-With');
  include_once '../../includes/ConnectDb.php';
  include_once '../../includes/Labels.php';
  include_once '../../includes/HttpLogger.php';
  $database = new ConnectDb();
  $db = $database->connect();
  $label = new Labels($db);
  $data = json_decode(file_get_contents("php://input"));
  $label->name = $data->name;
  error_log('Request Access To Delete Label With Name: ' . $label->name);
  if($label->delete()) {
    echo json_encode(
      array('message' => 'Label Deleted')
    );
      $database->disconnect($db);
  } else {
    echo json_encode(
      array('message' => 'Label Not Deleted')
    );
      $database->disconnect($db);
  }

